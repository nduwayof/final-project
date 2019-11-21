const express = require('express');
const axios = require('axios');
const jwt = require('jsonwebtoken');

const route = express.Router();

route.use('/locations', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/location', process.env.locationServ, process.env.locationServPort);
});
route.use('/users', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/users', process.env.userServ, process.env.userServPort);
});
route.use('/orders/status', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/order/status', process.env.orderStatuServ, process.env.orderStatuServPort);
});
route.use('/orders', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/order', process.env.orderServ, process.env.orderServPort);
});
route.use('/restaurants', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/restaurants', process.env.restaurantServ, process.env.restaurantServPort);
});
route.use('/rankings', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/ranking', process.env.rankingServ, process.env.rankingServPort);
});
route.use('/payments', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/payments', process.env.paymentServ, process.env.paymentServPort);
});
route.use('/deals', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/deals', process.env.dealsServ, process.env.dealsServPort);
});
route.use('/favorites', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/favorites', process.env.favoriteServ, process.env.favoriteServPort);
});

async function redirect(req, res, next, name, envUrl, envPort) {
    //const path = req.baseUrl.split(name)[1];
    if (req.method === 'GET') {
        try {
            const ret = await axios.get('http://' + envUrl + ':' + envPort + req.originalUrl, req.params);
            res.json(ret.data, ret.status);
        } catch (err) {
            console.log(err);
            res.json(err.response.data, err.response.status);
        }
    } else if (req.method === 'POST') {
        try {
            const ret = await axios.post('http://' + envUrl + ':' + envPort + req.originalUrl, req.body);
            res.json(ret.data, ret.status);
        } catch (err) {
            console.log(err);
            res.json(err.response.data, err.response.status);
        }
    } else {
        return next('Method not suported');
    }
}

function checkJWT(req, res, next) {
    try {
        const secret = process.env.jwtSecret;
        const token = req.headers.authorization;
        jwt.verify(token, secret, function (err, decoded) {
            console.log(err + ' - ' + decoded);
            if (err) return next(err);
            next();
        });
    } catch (ex) {
        next(ex);
    }
}

module.exports = route;