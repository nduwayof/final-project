const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const Favorites = new Schema({
    user: { type: String, index: true, required: true, unique: true },
    restaurants: [{
        ref: { type: String, unique: true },
        rating: Number
    }]
});

module.exports = mongoose.model('favorite', Favorites); 