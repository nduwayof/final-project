package edu.mum.cs.restaurants.repositories;

import edu.mum.cs.restaurants.models.RestaurantAddress;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * The interface Restaurant address repository.
 * @author nduwayofabrice
 * @version 1.0
 */
@Repository
public interface IRestaurantAddressRepository extends CrudRepository<RestaurantAddress, UUID> {

    /**
     * Find by restaurant id list.
     *
     * @param restaurantId the restaurant id
     *
     * @return the list
     */
    @AllowFiltering
    List<RestaurantAddress> findByRestaurantId(final UUID restaurantId);
}
