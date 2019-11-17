const createError = require('http-errors');
const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');

const mongoose = require('mongoose');

const favoritesRouter = require('./routes/favorites');
const listener = require('./listener/listener');

const app = express();



app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

mongoose.connect('mongodb+srv://favsys:SWKn6983lCHZKGFo@fav-soyyk.mongodb.net/favorites?retryWrites=true&w=majority', {
  useUnifiedTopology: true,
  useCreateIndex: true,
  useNewUrlParser: true,
  useFindAndModify: false
}, err => {
  if (!err) {
    console.log('connected !');
  } else {
    console.log(err);
  }
});

app.use('/favorites', favoritesRouter);

listener().catch(console.error);

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
