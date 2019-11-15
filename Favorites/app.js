const createError = require('http-errors');
const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const { Kafka } = require('kafkajs');
const mongoose = require('mongoose');

const favoritesRouter = require('./routes/favorites');
const Favorites = require('./models/favorites');

const app = express();

const kafka = new Kafka({
  clientId: 'favorites',
  brokers: ['my-kafka:9092']
});


const producer = kafka.producer();
const consumer = kafka.consumer({ groupId: 'ea-eats' });


app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

mongoose.set('useCreateIndex', true);
mongoose.set('useNewUrlParser', true);
mongoose.set('useUnifiedTopology', true);
mongoose.set('useFindAndModify', false);

mongoose.connect('mongodb://mongodb:27017/favorites', err => {
  if (!err) {
    console.log('connected !');
  } else {
    console.log(err);
  }
});

const run = async () => {

  // Consuming
  await consumer.connect()
  await consumer.subscribe({ topic: 'user' });
  await consumer.subscribe({ topic: 'rank' });

  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {

      if (topic === 'user') {
        const user = JSON.parse(message.value);
        try {
          const newFav = new Favorites({
            user: user.id
          });
          const f = await newFav.save();
        } catch (err) {
          throw err;
        }

      } else if (topic === 'rank') {
        const rest = JSON.parse(message.value);
        const rank = rest.votes[rest.votes.length - 1];
        const fav = await Favorites.findOne({ user: rank.user, 'restaurants.ref': rest.restaurant });
        if (fav === null) {
          const newFav = await Favorites.findOneAndUpdate({ user: rank.user }, { $push: { restaurants: { ref: rest.restaurant, rating: rank.stars } } });
        } else {
          const upFav = await Favorites.findOneAndUpdate({ user: rank.user, 'restaurants.ref': rest.restaurant }, { $set: { 'restaurants.$.rating': rank.stars, 'restaurants.$.ref': rest.restaurant } }, { new: true, upsert: true }).exec();
        }
      }
    }
  });
}

run().catch(console.error);

app.use('/favorites', favoritesRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.json({ err });
});

module.exports = app;
