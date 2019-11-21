package edu.mum.cs.restaurants;

import edu.mum.cs.restaurants.models.Restaurant;
import edu.mum.cs.restaurants.repositories.IRestaurantRepository;
import edu.mum.cs.restaurants.services.IRestaurantQueryService;
import edu.mum.cs.restaurants.services.IRestaurantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The type Restaurants service application tests.
 * @author nduwayofabrice
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RestaurantsServiceApplicationTests {

    @MockBean
    private IRestaurantRepository restaurantRepository;

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private IRestaurantQueryService restaurantQueryService;


    @Test
    public void getRestaurantsTest(){
        RestaurantTestData data = new RestaurantTestData();
        when(restaurantRepository.findAll()).thenReturn(Stream.of(
                new Restaurant("Every Body", "3124688754",data.restaurantAddresses(),data.restaurantMenus(), data.restaurantSchedules()))
                .collect(Collectors.toList()));
        assertEquals(1, restaurantQueryService.findAllRestaurants().size());
    }

    @Test
    public void poRestaurant(){
        RestaurantTestData data = new RestaurantTestData();
        Restaurant restaurant = new Restaurant("Indian Cafe", "6417018763", data.restaurantAddresses(), data.restaurantMenus(), data.restaurantSchedules());
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        assertEquals(restaurant, restaurantService.saveRestaurant(restaurant));
    }

    @Test
    public void deleteRestaurant(){
        RestaurantTestData data = new RestaurantTestData();
        Restaurant restaurant = new Restaurant("Indian Cafe", "6417018763", data.restaurantAddresses(), data.restaurantMenus(), data.restaurantSchedules());
        restaurantService.deleteRestaurant(restaurant);
        verify(restaurantRepository, times(1)).delete(restaurant);
    }

}
