package edu.mum.cs.restaurants;

import edu.mum.cs.restaurants.models.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Restaurant test data.
 */
class RestaurantTestData {

    private UUID restaurantId;

    RestaurantTestData() {
        this.restaurantId = UUID.randomUUID();
    }

    /**
     * Restaurant addresses list.
     *
     * @return the list
     */
    List<RestaurantAddress> restaurantAddresses(){
        List<RestaurantAddress> restaurantAddresses = new ArrayList<>();

        restaurantAddresses.add(new RestaurantAddress("1000 North 4 Street","Iowa City","IOWA",
                52556,"USA", restaurantId, -91.024161, 41.65282, new double[]{-91.024161,41.65282}));
        return restaurantAddresses;
    }

    List<RestaurantMenu> restaurantMenus(){
        List<RestaurantMenu> restaurantMenus = new ArrayList<>();
        List<String> menuTags = new ArrayList<>();
        menuTags.add("Meat");
        menuTags.add("Veggie");
        restaurantMenus.add(new RestaurantMenu("Happy Hour", "Menu description",
                BigDecimal.TEN, menuTags, 45, restaurantId));
        return restaurantMenus;
    }

    List<RestaurantSchedule> restaurantSchedules(){
        List<RestaurantSchedule> restaurantSchedules = new ArrayList<>();
        restaurantSchedules.add(new RestaurantSchedule(EWeekDay.MONDAY, LocalTime.NOON, LocalTime.MIDNIGHT, restaurantId));
        return restaurantSchedules;
    }

}
