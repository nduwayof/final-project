package edu.mum.cs.restaurants.services;

import edu.mum.cs.restaurants.models.*;

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

    /**
     * Find restaurant menus by restaurant id list.
     *
     * @param restaurantId the restaurant id
     *
     * @return the list
     */
    List<RestaurantMenu> findRestaurantMenusByRestaurantId(final UUID restaurantId);

    /**
     * Find restaurant menu by id restaurant menu.
     *
     * @param restaurantMenuId the restaurant menu id
     *
     * @return the restaurant menu
     */
    RestaurantMenu findRestaurantMenuById(final UUID restaurantMenuId);

    /**
     * Find restaurant addresses by restaurant id list.
     *
     * @param restaurantId the restaurant id
     *
     * @return the list
     */
    List<RestaurantAddress> findRestaurantAddressesByRestaurantId(final UUID restaurantId);

    /**
     * Find restaurant schedule by restaurant id list.
     *
     * @param restaurantId the restaurant id
     *
     * @return the list
     */
    List<RestaurantSchedule> findRestaurantScheduleByRestaurantId(final UUID restaurantId);

    /**
     * Find restaurant schedule by restaurant id and day restaurant schedule.
     *
     * @param restaurantId the restaurant id
     * @param weekDay      the week day
     *
     * @return the restaurant schedule
     */
    RestaurantSchedule findRestaurantScheduleByRestaurantIdAndDay(final UUID restaurantId, final EWeekDay weekDay);
}
