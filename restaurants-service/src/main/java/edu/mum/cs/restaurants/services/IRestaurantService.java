package edu.mum.cs.restaurants.services;

import edu.mum.cs.restaurants.models.Restaurant;
import edu.mum.cs.restaurants.models.RestaurantAddress;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * The interface Restaurant service.
 *
 * @version 1.o
 */
public interface IRestaurantService {
    /**
     * Save restaurant restaurant.
     *
     * @param restaurant the restaurant
     *
     * @return the restaurant
     */
    Restaurant saveRestaurant(@NotNull final Restaurant  restaurant);

    /**
     * Create restaurant restaurant.
     *
     * @param restaurant the restaurant
     *
     * @return the restaurant
     */
    Restaurant createRestaurant(@NotNull final Restaurant  restaurant);

    /**
     * Delete restaurant restaurant.
     *
     * @param id the id
     *
     * @return the restaurant
     */
    Restaurant deleteRestaurant(UUID id);
}
