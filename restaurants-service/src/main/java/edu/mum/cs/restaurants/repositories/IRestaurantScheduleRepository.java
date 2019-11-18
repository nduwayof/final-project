package edu.mum.cs.restaurants.repositories;

import edu.mum.cs.restaurants.models.EWeekDay;
import edu.mum.cs.restaurants.models.RestaurantMenu;
import edu.mum.cs.restaurants.models.RestaurantSchedule;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * The interface Restaurant schedule repository.
 * @author nduwayofabrice
 * @version 1.0
 */
@Repository
public interface IRestaurantScheduleRepository extends CrudRepository<RestaurantSchedule, UUID> {

    @AllowFiltering
    RestaurantSchedule findByDayAndAndRestaurantId(@NotNull final EWeekDay weekDay, @NotNull final UUID restaurantId);

    @AllowFiltering
    List<RestaurantSchedule> findByRestaurantId(final UUID restaurantId);
}
