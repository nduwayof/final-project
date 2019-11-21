const mongoose = require("mongoose");

var user = mongoose.model("user", {
  name: String,
  email: { type: String, unique: true },
  password: String,
  phone: String,
  address: [
    {
      country: String,
      street1: String,
      street2: String,
      zip: String,
      city: String
    }
  ],
  payment: [String],
  roles: [String]
});

module.exports = user;
