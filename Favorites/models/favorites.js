const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const Favorites = new Schema({
    user: { type: String, index: true, required: true, unique: true },
    restaurants: [{
        ref: { type: String },
        rating: Number
    }]
});
Favorites.index({ 'restaurants.ref': 1 }, { sparse: true, unique: true });
module.exports = mongoose.model('favorite', Favorites); 