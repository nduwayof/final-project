package edu.mum.cs.restaurants.repositories;

import edu.mum.cs.restaurants.models.Restaurant;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * The interface Restaurant repository.
 */
@Repository
public interface IRestaurantRepository extends CrudRepository<Restaurant, UUID> {


    @AllowFiltering
    Optional<Restaurant> findById(final UUID id);

    /**
     * Find by phone number restaurant.
     *
     * @param phoneNumber the phone number
     *
     * @return the restaurant
     */
    @AllowFiltering
    Restaurant findByPhoneNumber(final String phoneNumber);

}
