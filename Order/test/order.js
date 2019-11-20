const request = require('supertest');

process.env.NODE_ENV = 'test';
const app = require('../app')

describe('GET /orders', function () {

    it("GET ALL orders", done => {
        request(app)
            .get('/orders')
            .expect(200)
            .expect('Content-Type', /json/)
            .then(resp => {
                console.dir(resp.body);
                done();
            });
    });
});

describe('Post new Order /orders', function () {

    let count = 0;
    let id = '';
    it("GET count Orders", done => {
        request(app)
            .get('/orders')
            .expect(200)
            .expect('Content-Type', /json/)
            .then(resp => {
                count = resp.body.length;
                done();
            });
    });
    it("POST new order", done => {
        request(app)
            .post('/orders')
            .send({
                restaurant: "123456",
                user: "12345",
                items: [{
                    name: "burguer",
                    value: 15.35,
                    preparationTime: 15
                }, {
                    name: "coke",
                    value: 5.35,
                    preparationTime: 0
                }, {
                    name: "fries",
                    value: 1.45,
                    preparationTime: 0
                }]
            })
            .expect(200)
            .expect('Content-Type', /json/)
            .end((err, resp) => {
                if (err) return done(err);
                id = resp.body._id;
                done();
            });
    });
    it("GET count Orders", done => {
        request(app)
            .get('/orders')
            .expect(200)
            .expect('Content-Type', /json/)
            .then(resp => {
                console.log(count + ' ' + resp.body.length);
                if (count < resp.body.length) {
                    done();
                } else {
                    done('Failed to post new order');
                }
            });
    });
});