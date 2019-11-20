const Restaurant = require("./model/restaurant");
const { Kafka } = require("kafkajs");

const kafka = new Kafka({
  clientId: "location-client",
  brokers: ["my-kafka:9092"]
});
const consumer = kafka.consumer({ groupId: "test-group" });
const run = async () => {
  await consumer.connect();
  await consumer.subscribe({ topic: "restaurants" });
  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      const restaurant_kafka = JSON.parse(message.value);
      console.dir(restaurant_kafka);
      try {
        const newRestaurant = new Restaurant({
          id: restaurant_kafka.id,
          name: restaurant_kafka.name,
          address: restaurant_kafka.addresses,
          menu: restaurant_kafka.menus
        });
        await newRestaurant.save();
      } catch (err) {
        throw err;
      }
    }
  });
};
  
 module.exports = run;