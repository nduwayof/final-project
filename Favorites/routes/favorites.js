const express = require('express');
const { Kafka } = require('kafkajs');

const Favorites = require('../models/favorites');

const route = express.Router();
const kafka = new Kafka({
    clientId: 'favorites',
    brokers: ['my-kafka:9092']
});
const producer = kafka.producer();

route.get('/', async (req, res, next) => {
    try {
        const favs = await Favorites.find({});
        res.json(favs);
    } catch (err) {
        next(err);
    }
});

route.post('/', async (req, res) => {
    await producer.connect();
    const u = req.body;
    await producer.send({
        topic: 'user',
        messages: [{
            value: JSON.stringify(u)
        }],
    });
    res.json('Message sent to kafka');
});

route.post('/rating', async (req, res) => {
    await producer.connect();
    const u = req.body;
    await producer.send({
        topic: 'rank',
        messages: [{
            value: JSON.stringify(u)
        }],
    });
    res.json('Message sent to kafka');
});

route.get('/:id', async (req, res) => {
    const id = req.params.id;
    const fav = await Favorites.findOne({ user: id }).exec();
});

route.delete('/:id', async (req, res) => {
    const id = req.params.id;
    const fav = await Favorites.deleteOne({ user: id }).exec();
    res.json(fav);
});

module.exports = route;