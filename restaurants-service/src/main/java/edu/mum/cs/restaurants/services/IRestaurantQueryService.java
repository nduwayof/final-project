package edu.mum.cs.restaurants.services;

import edu.mum.cs.restaurants.models.Restaurant;

import java.util.List;
import java.util.UUID;

/**
 * The interface Restaurant query service.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
public interface IRestaurantQueryService {

    /**
     * Find restaurant by id restaurant.
     *
     * @param id the id
     *
     * @return the restaurant
     */
    Restaurant findRestaurantById(final UUID id);

    /**
     * Find restaurant by phone number restaurant.
     *
     * @param phoneNumber the phone number
     *
     * @return the restaurant
     */
    Restaurant findRestaurantByPhoneNumber(final String phoneNumber);

    /**
     * Find all restaurants list.
     *
     * @return the list
     */
    List<Restaurant> findAllRestaurants();
}
