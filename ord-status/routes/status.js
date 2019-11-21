const express = require('express');

const Order = require('../models/order');

const route = express.Router();

route.get('/', async (req, res, next) => {
    const list = await Order.find({}).exec();
    res.json(list);
});

route.get('/user/:id', async (req, res, next) => {
    const list = await Order.find({ user: req.params.id }).exec();
    res.json(list);
});

route.get('/restaurant/:id', async (req, res, next) => {
    const list = await Order.find({ restaurants: req.params.id }).exec();
    res.json(list);
});

route.get('/:id', async (req, res, next) => {
    const order = await Order.findOne({ ref: req.params.id }).exec();
    res.json(order);
});

module.exports = route;