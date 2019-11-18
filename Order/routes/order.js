const express = require('express');
const { Kafka } = require('kafkajs');

const Order = require('../models/order');

const route = express.Router();
const kafka = new Kafka({
    clientId: 'orderSvc',
    brokers: ['my-kafka:9092']
});
const producer = kafka.producer();

route.post('/', async (req, res, next) => {
    const order = req.body;
    console.dir(order);
    let total = 0;
    let time = 0;
    for (const item of order.items) {
        total += item.value;
        if (item.preparationTime > time) {
            time = item.preparationTime;
        }
    }
    try {
        const newOrd = new Order({
            restaurant: order.restaurant,
            user: order.user,
            date: new Date(),
            items: order.items,
            deliveryTime: time,
            total: total
        });
        const ord = await newOrd.save();
        await producer.connect();
        await producer.send({
            topic: 'order',
            messages: [{
                value: JSON.stringify(ord)
            }],
        });
        res.json(ord);
    } catch (err) {
        console.log(err);
        next(err);
    }
});

route.delete('/:id', async (req, res, next) => {
    try {
        await Order.deleteOne({ _id: req.params.id });
        res.json({ msg: `Order ${req.params.id} deleted.` });
    } catch (err) {
        next(err);
    }
});

route.get('/', async (req, res) => {
    const orders = await Order.find({});
    res.json(orders);
});
route.get('/user/:id', async (req, res) => {
    const user = req.params.id;
    const orders = await Order.find({ user: user });
    res.json(orders);
});
route.get('/restaurant/:id', async (req, res) => {
    const restaurant = req.params.id;
    const orders = await Order.find({ restaurant: restaurant });
    res.json(orders);
});

module.exports = route;