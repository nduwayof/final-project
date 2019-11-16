package edu.mum.cs.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RestaurantsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsServiceApplication.class, args);
    }

}
