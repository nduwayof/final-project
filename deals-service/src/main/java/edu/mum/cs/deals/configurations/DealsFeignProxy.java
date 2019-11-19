package edu.mum.cs.deals.configurations;

import edu.mum.cs.deals.utilities.beans.RestaurantMenuBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "restaurant-service", url = "http://restaurants-service:8091/restaurant/api/v1")
public interface DealsFeignProxy {

    @GetMapping(value = "/menu/item/{restaurantMenuId}")
    RestaurantMenuBean getRestaurantMenu(@PathVariable("restaurantMenuId") UUID restaurantMenuId);

}
