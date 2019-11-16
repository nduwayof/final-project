package edu.mum.cs.restaurants.repositories;

import edu.mum.cs.restaurants.models.RestaurantMenu;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * The interface Restaurant menu repository.
 */
@Repository
public interface IRestaurantMenuRepository extends CrudRepository<RestaurantMenu, UUID> {
    @AllowFiltering
    List<RestaurantMenu> findByRestaurantId(final UUID restaurantId);
}
