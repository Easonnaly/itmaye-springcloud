package com.eason.api_member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class ApiMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMemberApplication.class, args);
    }

}
