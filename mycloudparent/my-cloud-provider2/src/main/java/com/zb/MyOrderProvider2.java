package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyOrderProvider2 {
    public static void main(String[] args) {
        SpringApplication.run(MyOrderProvider2.class,args);
    }
}
