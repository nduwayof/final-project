package edu.mum.cs.restaurants.controllers;

import edu.mum.cs.restaurants.models.*;
import edu.mum.cs.restaurants.services.IRestaurantQueryService;
import edu.mum.cs.restaurants.services.IRestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The type Restaurant controller.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/restaurants/api/v1")
@Api(value = "Restaurants Service", description = "Restaurants micro service REST API")
public class RestaurantController {

        private static final String TOPIC = "restaurants";

        private IRestaurantService restaurantService;

        private IRestaurantQueryService restaurantQueryService;

        private KafkaTemplate<String, Restaurant> restaurantKafkaTemplate;

        /**
         * Get restaurants response entity.
         *
         * @return the response entity
         */
        @GetMapping
        @Cacheable(value = "restaurants")
        @ApiOperation(value = "View a list of available restaurants", response = List.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public List<Restaurant> getRestaurants() {
                return this.restaurantQueryService.findAllRestaurants();
        }

        /**
         * Post restaurant response entity.
         *
         * @param restaurant the restaurant
         *
         * @return the response entity
         */
        @PostMapping
        @CachePut(value = "restaurants", key = "#restaurant")
        @ApiOperation(value = "Post a new restaurant", response = Restaurant.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully posted a new restaurant"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public Restaurant postRestaurant(@RequestBody Restaurant restaurant) {
                Restaurant restaurantObj = this.restaurantService.saveRestaurant(restaurant);
                this.restaurantKafkaTemplate.send(TOPIC, restaurantObj);
                return restaurantObj;
        }

        /**
         * Gets restaurant by id.
         *
         * @param id the id
         *
         * @return the restaurant by id
         */
        @GetMapping(value = "/{id}")
        @Cacheable(value = "restaurants", key = "#id")
        @ApiOperation(value = "Retrieve a restaurant by id", response = Restaurant.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved a restaurant by id"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public Restaurant getRestaurantById(@PathVariable("id") UUID id) {
                return this.restaurantQueryService.findRestaurantById(id);
        }

        /**
         * Gets restaurant by phone number.
         *
         * @param phoneNumber the phone number
         *
         * @return the restaurant by phone number
         */
        @GetMapping(value = "/phone/{phoneNumber}")
        @Cacheable(value = "restaurants", key = "#phoneNumber")
        @ApiOperation(value = "Get restaurant by phone number", response = Restaurant.class)
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Successfully retrieved a restaurant by phone number"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public Restaurant getRestaurantByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
                return this.restaurantQueryService.findRestaurantByPhoneNumber(phoneNumber);
        }

        /**
         * Delete restaurant response entity.
         *
         * @param id the id
         *
         * @return the response entity
         */
        @DeleteMapping(value = "/delete/{id}")
        @Cacheable(value = "restaurants", key = "#id")
        @ApiOperation(value = "Delete an existing restaurant", response = Restaurant.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted a restaurant by id"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public Restaurant deleteRestaurant(@PathVariable("id") UUID id) {
                Restaurant restaurant = this.restaurantQueryService.findRestaurantById(id);
                return this.restaurantService.deleteRestaurant(restaurant);
        }

        /**
         * Gets restaurant menus.
         *
         * @param id the id
         *
         * @return the restaurant menus
         */
        @GetMapping(value = "/menu/{restaurantId}")
        @Cacheable(value = "restaurant_menus", key = "#id")
        @ApiOperation(value = "Retrieve menu by restaurant id", response = List.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved menu by restaurant id"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public List<RestaurantMenu> getRestaurantMenus(@PathVariable("restaurantId") UUID id) {
                return this.restaurantQueryService.findRestaurantMenusByRestaurantId(id);
        }

        /**
         * Gets restaurant menu.
         *
         * @param id the id
         *
         * @return the restaurant menu
         */
        @GetMapping(value = "/menu/item/{restaurantMenuId}")
        @Cacheable(value = "restaurant_menus", key = "#id")
        @ApiOperation(value = "Retrieve a particular item restaurant on menu by id", response = RestaurantMenu.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved a menu item by id"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public RestaurantMenu getRestaurantMenu(@PathVariable("restaurantMenuId") UUID id) {
                return this.restaurantQueryService.findRestaurantMenuById(id);
        }

        /**
         * Gets restaurant addresses.
         *
         * @param id the id
         *
         * @return the restaurant addresses
         */
        @GetMapping(value = "/address/{restaurantId}")
        @ApiOperation(value = "Retrieve a list of addresses for a particular restaurant by id", response = Restaurant.class)
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Successfully retrieved a list of addresses for a restaurant"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public List<RestaurantAddress> getRestaurantAddresses(@PathVariable("restaurantId") UUID id) {
                return this.restaurantQueryService.findRestaurantAddressesByRestaurantId(id);
        }

        /**
         * Gets restaurant schedules.
         *
         * @param id the id
         *
         * @return the restaurant schedules
         */
        @GetMapping(value = "/schedule/{restaurantId}")
        @ApiOperation(value = "Retrieve a restaurant schedule by a restaurant id", response = Restaurant.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved restaurant schedule"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public List<RestaurantSchedule> getRestaurantSchedules(@PathVariable("restaurantId") UUID id) {
                return this.restaurantQueryService.findRestaurantScheduleByRestaurantId(id);
        }

        /**
         * Gets restaurant schedule.
         *
         * @param id  the id
         * @param day the day
         *
         * @return the restaurant schedule
         */
        @GetMapping(value = "/schedule/{restaurantId}/{day}")
        @ApiOperation(value = "Retrieve a daily schedule", response = Restaurant.class)
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Successfully retrieved a daily schedule by restaurant and day"),
                        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
        public RestaurantSchedule getRestaurantSchedule(@PathVariable("restaurantId") UUID id,
                        @PathVariable("day") EWeekDay day) {
                return this.restaurantQueryService.findRestaurantScheduleByRestaurantIdAndDay(id, day);
        }
}
