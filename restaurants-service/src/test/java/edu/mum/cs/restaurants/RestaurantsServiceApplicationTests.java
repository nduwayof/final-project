package edu.mum.cs.restaurants;

import edu.mum.cs.restaurants.models.Restaurant;
import edu.mum.cs.restaurants.models.RestaurantAddress;
import edu.mum.cs.restaurants.models.RestaurantMenu;
import edu.mum.cs.restaurants.models.RestaurantSchedule;
import edu.mum.cs.restaurants.repositories.IRestaurantRepository;
import edu.mum.cs.restaurants.services.IRestaurantQueryService;
import edu.mum.cs.restaurants.services.IRestaurantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

}
