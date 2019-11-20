package edu.mum.cs.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The type Restaurants service application.
 */
@EnableCaching
@SpringBootApplication
public class RestaurantsServiceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RestaurantsServiceApplication.class, args);
    }

}
