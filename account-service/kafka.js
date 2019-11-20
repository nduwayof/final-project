const { Kafka } = require('kafkajs');
const express = require('express');

const app = express();
const router = express.Router();

const kafka = new Kafka({
    clientId:'kafka_test',
    brokers:['my-kafka:9092']
  });
  
  const producer = kafka.producer();
  const consumer = kafka.consumer({groupId:'test-group'});
  
  const run = async () => {
    // Consuming
    await consumer.connect()
    await consumer.subscribe({ topic: 'test1', fromBeginning: true })
   
    await consumer.run({
      eachMessage: async ({ topic, partition, message }) => {
        console.log({
          partition,
          offset: message.offset,
          value: message.value.toString(),
        })
      },
    })
  }
  
  run().catch();

  router.post('/',async(req,res)=>{
      const message = req.body.message
      await producer.connect()
      await producer.send({
        topic: 'test1',
        messages: [
          { value: message },
        ],
      })
  });

  module.exports = router