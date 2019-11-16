package edu.mum.cs.restaurants.services;

import edu.mum.cs.restaurants.models.Restaurant;
import edu.mum.cs.restaurants.models.RestaurantAddress;
import edu.mum.cs.restaurants.models.RestaurantMenu;
import edu.mum.cs.restaurants.models.RestaurantSchedule;
import edu.mum.cs.restaurants.repositories.IRestaurantAddressRepository;
import edu.mum.cs.restaurants.repositories.IRestaurantMenuRepository;
import edu.mum.cs.restaurants.repositories.IRestaurantRepository;
import edu.mum.cs.restaurants.repositories.IRestaurantScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * The type Restaurant service.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class RestaurantService implements IRestaurantService {

    private IRestaurantRepository restaurantRepository;
    private IRestaurantMenuRepository restaurantMenuRepository;
    private IRestaurantAddressRepository restaurantAddressRepository;
    private IRestaurantScheduleRepository restaurantScheduleRepository;

    @Override
    @Transactional
    public Restaurant saveRestaurant(@NotNull final Restaurant restaurant) {
        Restaurant restaurantObj = null;
        if(restaurantPhoneValidation(restaurant.getPhoneNumber())) {
            restaurantObj = this.restaurantRepository.save(restaurant);
        }else {
            log.error("Phone number already exist");
            throw new UnsupportedOperationException();
        }
        List<RestaurantAddress> restaurantAddresses = restaurant.getAddresses();
        if (restaurantAddresses != null && !restaurantAddresses.isEmpty()) {
            for (RestaurantAddress restaurantAddress : restaurantAddresses) {
                restaurantAddress.setRestaurantId(restaurantObj.getId());
                this.restaurantAddressRepository.save(restaurantAddress);
            }
        }
        List<RestaurantSchedule> restaurantSchedules = restaurant.getSchedules();
        if (restaurantSchedules != null && !restaurantSchedules.isEmpty()) {
            for (RestaurantSchedule restaurantSchedule : restaurantSchedules) {
                restaurantSchedule.setRestaurantId(restaurantObj.getId());
                if(scheduleValidation(restaurantSchedule))
                    this.restaurantScheduleRepository.save(restaurantSchedule);
            }
        }
        List<RestaurantMenu> restaurantMenus = restaurant.getMenus();
        if (restaurantMenus != null && !restaurantMenus.isEmpty()) {
            for (RestaurantMenu restaurantMenu : restaurantMenus) {
                restaurantMenu.setRestaurantId(restaurantObj.getId());
                this.restaurantMenuRepository.save(restaurantMenu);
            }
        }
        return restaurantObj;
    }

    @Override
    public Restaurant createRestaurant(@NotNull final Restaurant restaurant) {
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant deleteRestaurant(@NotNull final UUID id) {
        Restaurant restaurant = this.restaurantRepository.findById(id).orElse(null);
        if(restaurant != null) this.restaurantRepository.delete(restaurant);
        return restaurant;
    }

    private boolean scheduleValidation(@NotNull final RestaurantSchedule restaurantSchedule){
        return this.restaurantScheduleRepository
                .findByDayAndAndRestaurantId(restaurantSchedule
                .getDay(),restaurantSchedule.getRestaurantId()) == null ? Boolean.TRUE : Boolean.FALSE;
    }

    private boolean restaurantPhoneValidation(@NotNull final String phoneNumber){
        return this.restaurantRepository
                .findByPhoneNumber(phoneNumber) == null ? Boolean.TRUE : Boolean.FALSE;
    }
}
