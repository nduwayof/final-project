package edu.mum.cs.restaurants.services;

import edu.mum.cs.restaurants.models.Restaurant;
import edu.mum.cs.restaurants.repositories.IRestaurantAddressRepository;
import edu.mum.cs.restaurants.repositories.IRestaurantMenuRepository;
import edu.mum.cs.restaurants.repositories.IRestaurantRepository;
import edu.mum.cs.restaurants.repositories.IRestaurantScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type Restaurant query service.
 * @author nduwayofabrice
 * @version 1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class RestaurantQueryService implements IRestaurantQueryService{

    private IRestaurantRepository restaurantRepository;
    private IRestaurantMenuRepository restaurantMenuRepository;
    private IRestaurantAddressRepository restaurantAddressRepository;
    private IRestaurantScheduleRepository restaurantScheduleRepository;


    @Override
    public Restaurant findRestaurantById(@NotNull final UUID id) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(id);
        Restaurant restaurantObj = restaurant.orElse(null);
        if(restaurantObj != null){
            fetchRestaurant(restaurantObj);
        }
        return restaurantObj;
    }

    @Override
    public Restaurant findRestaurantByPhoneNumber(String phoneNumber) {
        Restaurant restaurantObj = this.restaurantRepository.findByPhoneNumber(phoneNumber);
       fetchRestaurant(restaurantObj);
        return restaurantObj;
    }

    @Override
    public List<Restaurant> findAllRestaurants() {
        List<Restaurant> restaurants =(List<Restaurant>) restaurantRepository.findAll();
        for(Restaurant restaurantObj : restaurants){
            fetchRestaurant(restaurantObj);
        }
        return restaurants;
    }

    private void fetchRestaurant(@NotNull final Restaurant restaurantObj){
        restaurantObj.setAddresses(this
                .restaurantAddressRepository
                .findByRestaurantId(restaurantObj.getId()));
        restaurantObj.setMenus(this
                .restaurantMenuRepository
                .findByRestaurantId(restaurantObj.getId()));
        restaurantObj.setSchedules(this
                .restaurantScheduleRepository
                .findByRestaurantId(restaurantObj.getId()));
    }
}
