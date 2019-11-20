##Restaurant API:

API-Gateway : `http://35.238.91.208/${Entity}`

VERB | Entity                                                        | Description   |
:----|:------------------------------------------------------------- |-------------:| 
GET  | users                                                         | Get all users 
GET  | users/:id                                                     | Get user by Id  
POST | users                                                         | Register a user  
POST | auth/login                                                    | Login |
GET  | restaurants/api/v1                                            | Get all restaurants |
GET  | orders                                                        | Get all orders
POST | orders                                                        | Add an order  
GET  | orders/restaurant/:restaurantId                               | Get all orders by Restaurant
GET  | orders/:userId                                                | Get all order by users
GET  | locations/nearest?longitude=${longitude}&latitude=${latitude} | Get all nearest restaurants
