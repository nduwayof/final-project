const { Kafka } = require('kafkajs');

const User = require('../models/user');

const kafka = new Kafka({
    clientId: 'api-gw',
    brokers: ['my-kafka:9092']
});

const run = async _ => {
    const consumer = kafka.consumer({ groupId: 'ea-eats' });

    // Consuming
    await consumer.connect()
    await consumer.subscribe({ topic: 'user' });

    await consumer.run({
        eachMessage: async ({ topic, partition, message }) => {
            const user = JSON.parse(message.value);
            try {
                const use = await User.findOne({ ref: user.id });
                if (use === null) {
                    const newUser = new User({
                        ref: user.id,
                        email: user.email,
                        password: user.password,
                        roles: user.roles
                    });
                    const f = await newFav.save();
                } else {
                    const upUser = await User.updateOne({ ref: user.id }, { password: user.password, roles: user.roles });
                }
            } catch (err) {
                throw err;
            }
        }
    });
}
module.exports = run;