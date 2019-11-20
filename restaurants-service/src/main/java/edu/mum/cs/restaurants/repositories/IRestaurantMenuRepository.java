package edu.mum.cs.restaurants.repositories;

import edu.mum.cs.restaurants.models.RestaurantMenu;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Restaurant menu repository.
 * @author nduwayofabrice
 * @version 1.0
 */
@Repository
public interface IRestaurantMenuRepository extends CrudRepository<RestaurantMenu, UUID> {

    @AllowFiltering
    Optional<RestaurantMenu> findById(final UUID id);

    /**
     * Find by restaurant id list.
     *
     * @param restaurantId the restaurant id
     *
     * @return the list
     */
    @AllowFiltering
    List<RestaurantMenu> findByRestaurantId(final UUID restaurantId);
}
