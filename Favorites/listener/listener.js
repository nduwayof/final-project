const { Kafka } = require('kafkajs');

const Favorites = require('../models/favorites');

const kafka = new Kafka({
    clientId: 'favorites',
    brokers: ['my-kafka:9092']
});

const run = async _ => {
    const consumer = kafka.consumer({ groupId: 'kafka-test' });

    // Consuming
    await consumer.connect()
    await consumer.subscribe({ topic: 'user' });
    await consumer.subscribe({ topic: 'rank' });

    await consumer.run({
        eachMessage: async ({ topic, partition, message }) => {
            console.log(topic + ' - ' + message.value);
            if (topic === 'user') {
                const user = JSON.parse(message.value);
                try {
                    const fav = await Favorites.findOne({ user: user._id });
                    if (fav === null) {
                        const newFav = new Favorites({
                            user: user._id
                        });
                        const f = await newFav.save();
                    }
                } catch (err) {
                    throw err;
                }

            } else if (topic === 'rank') {
                const rest = JSON.parse(message.value);
                console.dir(rest);
                const fav = await Favorites.findOne({ user: rest.userid, 'restaurants.ref': rest.rest_id });
                if (fav === null) {
                    const newFav = await Favorites.findOneAndUpdate({ user: rest.userid }, { $push: { restaurants: { ref: rest.rest_id, rating: rest.vote } } });
                } else {
                    const upFav = await Favorites.findOneAndUpdate({ user: rest.userid, 'restaurants.ref': rest.rest_id }, { $set: { 'restaurants.$.rating': rest.vote, 'restaurants.$.ref': rest.rest_id } }, { new: true, upsert: true }).exec();
                }
            }
        }
    });
}
module.exports = run;