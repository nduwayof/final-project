package edu.mum.cs.restaurants.controllers;

import edu.mum.cs.restaurants.models.Restaurant;
import edu.mum.cs.restaurants.services.IRestaurantQueryService;
import edu.mum.cs.restaurants.services.IRestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/restaurant/api")
public class RestaurantController {

    private IRestaurantService restaurantService;

    private IRestaurantQueryService restaurantQueryService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurants(){
        return new ResponseEntity<>(this.restaurantQueryService.findAllRestaurants(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> postRestaurant(@RequestBody Restaurant restaurant){
        return new ResponseEntity<>(this.restaurantService.saveRestaurant(restaurant), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") UUID id){
        return new ResponseEntity<>(this.restaurantQueryService.findRestaurantById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/phone/{phoneNumber}")
    public ResponseEntity<Restaurant> getRestaurantByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        return new ResponseEntity<>(this.restaurantQueryService.findRestaurantByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable("id") UUID id){
        return new ResponseEntity<>(this.restaurantService.deleteRestaurant(id), HttpStatus.OK);
    }

}
