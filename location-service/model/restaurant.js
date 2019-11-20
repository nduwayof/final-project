const mongoose = require("mongoose");
var Schema = mongoose.Schema;

var RestaurantSchema = new Schema({
  name: String,
  address:
    [{
      country: String,
      street1: String,
      street2: String,
      zip: String,
      city: String,
      coord:[Number],
    }],
  menu:[{
      res_name: String,
      description:String,
      price:String,
      tags:[String],
      productiontime:String
  }],
  openhours:{String}
});
RestaurantSchema.index({ "address.coord": "2d" });
var restaurant = mongoose.model("restaurant_ea", RestaurantSchema);

module.exports = restaurant;
