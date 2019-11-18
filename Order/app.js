const createError = require('http-errors');
const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const { Kafka } = require('kafkajs');
const mongoose = require('mongoose');

const orderRoute = require('./routes/order');

const app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
mongoose.set('useCreateIndex', true);
mongoose.set('useNewUrlParser', true);
mongoose.set('useUnifiedTopology', true);
mongoose.set('useFindAndModify', false);

if (process.env.NODE_ENV === "test") {
  var Mockgoose = require('mockgoose').Mockgoose;
  var mockgoose = new Mockgoose(mongoose);
  mockgoose.prepareStorage().then(function () {
    mongoose.connect('mongodb://localhost/orders', function (err) {
    });
  });
}
else {
  mongoose.connect('mongodb+srv://favsys:SWKn6983lCHZKGFo@fav-soyyk.mongodb.net/orders?retryWrites=true&w=majority', err => {
    if (!err) {
      console.log('connected !');
    } else {
      console.log(err);
    }
  });
}
app.use('/orders', orderRoute);

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
  res.json(err);
});

module.exports = app;
