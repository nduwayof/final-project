const router = require("express").Router();
const { Kafka } = require("kafkajs");
const Restaurant = require("../model/restaurant");

const kafka = new Kafka({
  clientId: "kafka_test",
  brokers: ["my-kafka:9092"]
});

const producer = kafka.producer();

//Get all the Locations
router.get("/", async (req, res) => {
  try {
    const restaurant_retrieved = await Restaurant.find();
    res.json(restaurant_retrieved);
  } catch (err) {
    throw err;
  }
});

//Delete all the Locations
router.delete("/delete", async (req, res) => {
  await Restaurant.deleteMany({});
});

//Geospatial API
router.get("/all", async (req, res) => {
  const r = await Restaurant.find({ "address.coord": { $near: [-91.96713, 41.02347] } });
  res.json(r);
});


//For Location service to push to Kafka
router.post("/", async (req, res) => {
  try {
    const restaurant = new Restaurant({
      name: req.body.name,
      address: [
        {
          country: req.body.country,
          street1: req.body.street1,
          street2: req.body.street2,
          zip: req.body.zip,
          city: req.body.city,
          coord:req.body.coord
        }
      ],
      menu: [
        {
          res_name: req.body.res_name,
          description: req.body.description,
          price: req.body.price,
          tags: req.body.tags,
          productiontime: req.body.productiontime
        }
      ],
      openhours: req.body.openhours
    });
    await producer.connect();
    await producer.send({
      topic: "restaurant",
      messages: [{ value: JSON.stringify(restaurant) }]
    });
  } catch (err) {
    throw err;
  }
});

module.exports = router;
