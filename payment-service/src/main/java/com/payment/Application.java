package com.payment;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({ "com.devcom" })
public class Application {
	
    public static void main(String[] args) {
        run(Application.class, args);
    }
}