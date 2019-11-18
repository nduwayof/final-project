package edu.mum.cs.deals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The type Deals service application.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
@EnableCaching
@SpringBootApplication
@EnableFeignClients("edu.mum.cs.deals")
public class DealsServiceApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DealsServiceApplication.class, args);
    }

}
