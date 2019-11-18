const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const User = new Schema({
    email: { type: String, unique: true, required: true },
    password: { type: String, required: true },
    roles: [String],
    ref: { type: String, required: true, unique: true }
});

User.index({ email: 1, password: 1 });

module.exports = mongoose.model('user', User);