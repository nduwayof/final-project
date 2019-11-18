const express = require('express');
const axios = require('axios');

const route = express.Router();

route.use('/location', async (req, res, next) => {
    await redirect(req, res, next, '/location', process.env.locationServ);
});
route.use('/notification', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/notification', process.env.notificationServ);
});
route.use('/users', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/users', process.env.userServ);
});
route.use('/order/status', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/order/status', process.env.orderStatuServ);
});
route.use('/order', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/order', process.env.orderServ);
});
route.use('/restaurants', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/restaurants', process.env.restaurantServ);
});
route.use('/ranking', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/ranking', process.env.rankingServ);
});
route.use('/payment', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/payment', process.env.paymentServ);
});
route.use('/deals', checkJWT, async (req, res, next) => {
    await redirect(req, res, next, '/deals', process.env.dealsServ);
});
route.use('/favorites', async (req, res, next) => {
    await redirect(req, res, next, '/favorites', process.env.favoriteServ);
});

async function redirect(req, res, next, name, envUrl) {
    //const path = req.baseUrl.split(name)[1];
    if (req.method === 'GET') {
        try {
            const ret = await axios.get('http://' + envUrl + ':3000' + req.baseUrl, req.params);
            res.json(ret.data, ret.status);
        } catch (err) {
            return next(err);
        }
    } else if (req.method === 'POST') {
        try {
            const ret = await axios.post('http://' + envUrl + ':3000' + req.baseUrl, req.body);
            res.json(ret.data, ret.status);
        } catch (err) {
            console.log(err);
            return next(err);
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
            if (err) return next(err);
            next();
        });
    } catch (ex) {
        next(ex);
    }
}

module.exports = route;