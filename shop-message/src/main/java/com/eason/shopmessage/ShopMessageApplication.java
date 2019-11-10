package com.eason.shopmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShopMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopMessageApplication.class, args);
    }

}
