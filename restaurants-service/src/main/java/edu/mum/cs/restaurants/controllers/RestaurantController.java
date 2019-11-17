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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/restaurant/api/v1")
@Api(value = "Restaurants Service", description = "Restaurants micro service REST API")
public class RestaurantController {

    private IRestaurantService restaurantService;

    private IRestaurantQueryService restaurantQueryService;

    /**
     * Get restaurants response entity.
     *
     * @return the response entity
     */
    @GetMapping
    @ApiOperation(value = "View a list of available restaurants", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        return new ResponseEntity<>(this.restaurantQueryService.findAllRestaurants(), HttpStatus.OK);
    }

    /**
     * Post restaurant response entity.
     *
     * @param restaurant the restaurant
     *
     * @return the response entity
     */
    @PostMapping
    @ApiOperation(value = "Post a new restaurant", response = Restaurant.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully posted a new restaurant"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<Restaurant> postRestaurant(@RequestBody Restaurant restaurant) {
        return new ResponseEntity<>(this.restaurantService.saveRestaurant(restaurant), HttpStatus.OK);
    }

    /**
     * Gets restaurant by id.
     *
     * @param id the id
     *
     * @return the restaurant by id
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retrieve a restaurant by id", response = Restaurant.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved a restaurant by id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(this.restaurantQueryService.findRestaurantById(id), HttpStatus.OK);
    }

    /**
     * Gets restaurant by phone number.
     *
     * @param phoneNumber the phone number
     *
     * @return the restaurant by phone number
     */
    @GetMapping(value = "/phone/{phoneNumber}")
    @ApiOperation(value = "Get restaurant by phone number", response = Restaurant.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved a restaurant by phone number"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<Restaurant> getRestaurantByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return new ResponseEntity<>(this.restaurantQueryService.findRestaurantByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    /**
     * Delete restaurant response entity.
     *
     * @param id the id
     *
     * @return the response entity
     */
    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "Delete an existing restaurant", response = Restaurant.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully deleted a restaurant by id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(this.restaurantService.deleteRestaurant(id), HttpStatus.OK);
    }

    /**
     * Gets restaurant menus.
     *
     * @param id the id
     *
     * @return the restaurant menus
     */
    @GetMapping(value = "/menu/{restaurantId}")
    @ApiOperation(value = "Retrieve menu by restaurant id", response = Restaurant.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved menu by restaurant id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<List<RestaurantMenu>> getRestaurantMenus(@PathVariable("restaurantId") UUID id) {
        return new ResponseEntity<>(this.restaurantQueryService
                .findRestaurantMenusByRestaurantId(id), HttpStatus.OK);
    }

    /**
     * Gets restaurant menu.
     *
     * @param id the id
     *
     * @return the restaurant menu
     */
    @GetMapping(value = "/menu/item/{restaurantMenuId}")
    @ApiOperation(value = "Retrieve a particular item restaurant on menu by id", response = Restaurant.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved a menu item by id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<RestaurantMenu> getRestaurantMenu(@PathVariable("restaurantMenuId") UUID id) {
        return new ResponseEntity<>(this.restaurantQueryService
                .findRestaurantMenuById(id), HttpStatus.OK);
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
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved a list of addresses for a restaurant"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<List<RestaurantAddress>> getRestaurantAddresses(@PathVariable("restaurantId") UUID id) {
        return new ResponseEntity<>(this.restaurantQueryService
                .findRestaurantAddressesByRestaurantId(id), HttpStatus.OK);
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
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved restaurant schedule"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<List<RestaurantSchedule>> getRestaurantSchedules(@PathVariable("restaurantId") UUID id) {
        return new ResponseEntity<>(this.restaurantQueryService
                .findRestaurantScheduleByRestaurantId(id), HttpStatus.OK);
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
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved a daily schedule by restaurant and day"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<RestaurantSchedule> getRestaurantSchedule(@PathVariable("restaurantId") UUID id, @PathVariable("day") EWeekDay day) {
        return new ResponseEntity<>(this.restaurantQueryService
                .findRestaurantScheduleByRestaurantIdAndDay(id, day), HttpStatus.OK);
    }
}
