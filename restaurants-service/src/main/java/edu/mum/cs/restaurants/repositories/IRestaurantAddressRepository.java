package edu.mum.cs.restaurants.repositories;

import edu.mum.cs.restaurants.models.RestaurantAddress;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * The interface Restaurant address repository.
 */
@Repository
public interface IRestaurantAddressRepository extends CrudRepository<RestaurantAddress, UUID> {

    @AllowFiltering
    List<RestaurantAddress> findByRestaurantId(final UUID restaurantId);
}
