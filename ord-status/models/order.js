const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const Order = new Schema({
    restaurant: String,
    ref: String,
    user: { type: String, index: true },
    date: Date,
    items: [{
        name: String,
        value: Number,
        preparationTime: Number
    }],
    deliveryTime: Number,
    total: Number,
    payment: {
        creditCardInfo: String,
        paymentDate: Date,
        creditCardInfo: {
            cvc: String,
            month: Number,
            year: Number
        }
    }
});
module.exports = mongoose.model('order', Order); 