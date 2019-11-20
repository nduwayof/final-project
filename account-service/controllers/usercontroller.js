const router = require("express").Router();
const jwt = require("jsonwebtoken");
var ObjectId = require("mongoose").Types.ObjectId;
var User = require("../model/user");
const { Kafka } = require("kafkajs");
const bcrypt = require('bcrypt');

const kafka = new Kafka({
  clientId: 'favorites',
  brokers: ['my-kafka:9092']
});

const producer = kafka.producer();

router.get('/:id', async (req,res)=>{
  const user = await User.findById({_id:req.body.id});
  if(!user){
    res.json({message:`User with ${req.body.id} not found !`});
  }else{
    res.json(user);
  }
});

router.get("/", async (req, res) => {
  try {
    const users = await User.find();
    if (!users) {
      res.json({
        message: "No users found!"
      });
    } else {
      res.json(users);
    }
  } catch (err) {
    console.error(err);
  }
});

router.post("/", async (req, res) => {
  try {
    console.log('hey....');
    const user = new User({
      name: req.body.name,
      email: req.body.email,
      password: bcrypt.hashSync(req.body.password, 10),
      phone: req.body.phone,
      address: [
        {
          country: req.body.country,
          street1: req.body.street1,
          street2: req.body.street2,
          zip: req.body.zip,
          city: req.body.city
        }
      ],
      payment: req.body.payment,
      roles: req.body.roles
    });
    const savedUser = await user.save();
    if (!savedUser) {
      res.json({
        message: `User could not be saved`
      });
    } else {
      console.log('kafka.....');
      try {
        await producer.connect();
        await producer.send({
          topic: "user",
          messages: [{ value: JSON.stringify(savedUser) }]
        });
      } catch (err) {
        console.log(err);
      }
      res.json(savedUser);
    }
  } catch (err) {
    console.log(err);
  }
});

module.exports = router;
