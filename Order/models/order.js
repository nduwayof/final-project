const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const Orders = new Schema({
    restaurant: String,
    user: { type: String, index: true },
    date: Date,
    items: [{
        name: String,
        value: Number,
        preparationTime: Number
    }],
    deliveryTime: Number,
    total: Number
});

module.exports = mongoose.model('order', Orders); 