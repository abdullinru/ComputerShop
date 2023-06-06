package com.example.computershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ComputerShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerShopApplication.class, args);
    }

}
