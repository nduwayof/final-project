const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const Orders = new Schema({
    restaurant: String,
    user: { type: String, index: true },
    date: Date,
    items: [{
        name: String,
        value: mongoose.Types.Decimal128,
        preparationTime: Number
    }],
    deliveryTime: Number,
    total: mongoose.Types.Decimal128
});

module.exports = mongoose.model('order', Orders); 