const { Kafka } = require('kafkajs');

const Order = require('../models/order');

const kafka = new Kafka({
    clientId: 'order-status',
    brokers: ['my-kafka:9092']
});


const run = async _ => {
    const consumer = kafka.consumer({ groupId: 'notifications' });
    const producer = kafka.producer();
    // Consuming
    await consumer.connect();
    await consumer.subscribe({ topic: 'order' });
    await consumer.subscribe({ topic: 'payment' });

    await consumer.run({
        eachMessage: async ({ topic, partition, message }) => {
            console.log(topic + ' - ' + message.value);
            if (topic === 'order') {
                // const user = JSON.parse(message.value);
                // try {
                //     const fav = await Favorites.findOne({ user: user._id });
                //     if (fav === null) {
                //         const newFav = new Favorites({
                //             user: user._id
                //         });
                //         const f = await newFav.save();
                //     }
                // } catch (err) {
                //     throw err;
                // }
                const order = JSON.parse(message.value);

                try {
                    const orderDB = await Order.findOne({ ref: order._id });
                    if (orderDB === null) {
                        const newOrder = new Order({
                            ref: order._id,
                            restaurant: order.restaurant,
                            user: order.user,
                            items: order.items,
                            total: order.total,
                            deliveryTime: order.deliveryTime
                        });
                        await newOrder.save();
                        producer.send({
                            topic: 'notification',
                            messages: [{
                                value: JSON.stringify({ status: 'The order placed successfully', userId: order.user })
                            }],
                        });
                    }
                } catch (err) {
                    throw err;
                }
            } else if (topic === 'payment') {
                // const rest = JSON.parse(message.value);
                // console.dir(rest);
                // const fav = await Favorites.findOne({ user: rest.userid, 'restaurants.ref': rest.rest_id });
                // if (fav === null) {
                //     const newFav = await Favorites.findOneAndUpdate({ user: rest.userid }, { $push: { restaurants: { ref: rest.rest_id, rating: rest.vote } } });
                // } else {
                //     const upFav = await Favorites.findOneAndUpdate({ user: rest.userid, 'restaurants.ref': rest.rest_id }, { $set: { 'restaurants.$.rating': rest.vote, 'restaurants.$.ref': rest.rest_id } }, { new: true, upsert: true }).exec();
                // }
                const payment = JSON.parse(message.value);
                try {
                    const order = await Order.findOne({ ref: payment.orderId }).exec();
                    console.dir(order);
                    if (order === null) throw "Order not found";
                    const updated = await Order.findOneAndUpdate({ ref: payment.orderId },
                        {
                            $set: {
                                payment: {
                                    creditCardInfo: payment.creditCardInfo,
                                    paymentDate: payment.paymentDate,
                                    creditCardInfo: {
                                        cvc: payment.creditCardInfo.cvc,
                                        month: payment.creditCardInfo.month,
                                        year: payment.creditCardInfo.year
                                    }
                                }
                            }
                        });
                    producer.send({
                        topic: 'notification',
                        messages: [{
                            value: JSON.stringify({ status: 'The order was paid successfully', userId: order.user })
                        }],
                    });
                } catch (err) {
                    throw err;
                }
            }
        }
    });
}
module.exports = run;