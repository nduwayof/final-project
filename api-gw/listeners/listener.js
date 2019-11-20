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
            console.dir(user);
            try {
                const use = await User.findOne({ ref: user._id });
                if (use === null) {
                    const newUser = new User({
                        ref: user._id,
                        email: user.email,
                        password: user.password,
                        roles: user.roles,
                        name: user.name
                    });
                    await newUser.save();
                } else {
                    await User.updateOne({ ref: user._id }, { password: user.password, roles: user.roles, name: user.name });
                }
            } catch (err) {
                throw err;
            }
        }
    });
}
module.exports = run;