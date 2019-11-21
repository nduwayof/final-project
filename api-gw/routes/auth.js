const express = require('express');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');
const axios = require('axios');

const User = require('../models/user');

const route = express.Router();

route.post('/login', async (req, res, next) => {
    if (req.body.email === undefined || req.body.email.length < 5 || req.body.pass === undefined) {
        return next('Invalid email or password.');
    }
    const user = await User.findOne({ email: req.body.email }).exec();
    if (user === null || user.email === undefined) {
        next('User and/or password does not match.');
    }
    bcrypt.compare(req.body.pass, user.password, (err, same) => {
        if (err) return next(err);
        if (same) {
            const secret = process.env.jwtSecret;
            const encrpt = jwt.sign({
                email: user.email,
                roles: user.roles,
                name: user.name
            }, secret);
            //res.headers.authorization = encrpt;
            res.json({ msg: 'Sucessful log in', token: encrpt });
        } else {
            next("Invalid password");
        }
    });

});

route.post('/signin', async (req, res, next) => {
    const url = process.env.userServ;
    try {
        const ret = await axios.post('http://' + url + ':' + process.env.userServPort + '/users', req.body);
        res.json(ret.data, ret.status);
        res.json(ret);
    } catch (err) {
        next(err);
    }
});

module.exports = route;