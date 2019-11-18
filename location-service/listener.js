const Restaurant = require("./model/restaurant");
const { Kafka } = require("kafkajs");

const kafka = new Kafka({
    clientId: "kafka_test",
    brokers: ["my-kafka:9092"]
  });
  const consumer = kafka.consumer({ groupId: "test-group" });
  const run = async () => {
    await consumer.connect();
    await consumer.subscribe({ topic: "restaurant", fromBeginning: false });
    await consumer.run({
      eachMessage: async ({ topic, partition, message }) => {
        const restaurant_kafka = JSON.parse(message.value);
        try {
          const newRestaurant = new Restaurant({
            id: restaurant_kafka.id,
            name: restaurant_kafka.name,
            address: restaurant_kafka.address,
            menu: restaurant_kafka.menu
          });
          const r = await newRestaurant.save();
        } catch (err) {
          throw err;
        }
      }
    });
  };
  
 module.exports = run;