const { Kafka } = require('kafkajs');

const Favorites = require('../models/favorites');

const kafka = new Kafka({
    clientId: 'favorites',
    brokers: ['my-kafka:9092']
});

const run = async _ => {
    const consumer = kafka.consumer({ groupId: 'ea-eats' });

    // Consuming
    await consumer.connect()
    await consumer.subscribe({ topic: 'user' });
    await consumer.subscribe({ topic: 'rank' });

    await consumer.run({
        eachMessage: async ({ topic, partition, message }) => {

            if (topic === 'user') {
                const user = JSON.parse(message.value);
                try {
                    const fav = await Favorites.findOne({ user: user.id });
                    if (fav === null) {
                        const newFav = new Favorites({
                            user: user.id
                        });
                        const f = await newFav.save();
                    }
                } catch (err) {
                    throw err;
                }

            } else if (topic === 'rank') {
                const rest = JSON.parse(message.value);
                const fav = await Favorites.findOne({ user: rank.userId, 'restaurants.ref': rest.rest_id });
                if (fav === null) {
                    const newFav = await Favorites.findOneAndUpdate({ user: rest.userId }, { $push: { restaurants: { ref: rest.rest_id, rating: rest.vote } } });
                } else {
                    const upFav = await Favorites.findOneAndUpdate({ user: rest.userId, 'restaurants.ref': rest.rest_id }, { $set: { 'restaurants.$.rating': rest.vote, 'restaurants.$.ref': rest.restaurant } }, { new: true, upsert: true }).exec();
                }
            }
        }
    });
}
module.exports = run;