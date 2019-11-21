const mongoose = require('mongoose');
const dbString = `mongodb://mongodb:27017/restaurant`;
mongoose.connect(dbString, (err) => {
    if (!err) {
        console.log('Database Connected Successfully');
    } else {
        console.log('Database Failed to connect');
    }
});

module.exports = mongoose;