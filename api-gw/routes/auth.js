const express = require('express');

const route = express.Router();

route.post('/login', async (req, res, next) => {
    if (req.body.email === undefined || req.body.email.length < 5 || req.body.pass === undefined || req.body.pass.length < 6) {
        return next('Invalid email or password.');
    }
    const user = await User.findOne({ email: req.body.email, password: req.body.pass }).select('email,roles').exec();
    if (user === null || user.email === undefined) {
        next('User and/or password does not match.');
    }
    const secret = process.env.jwtSecret;
    const encrpt = await jwt.sign(user, secret);
    res.headers.authorization = encrpt;
    res.json({ msg: 'Sucessful log in', token: encrpt });
});

route.post('/signin', async (req, res, next) => {
    const url = process.env.userServ;
    try {
        const ret = await axios.post(url + '/user', req.body);
        res.json(ret);
    } catch (err) {
        next(err);
    }
});

module.exports = route;