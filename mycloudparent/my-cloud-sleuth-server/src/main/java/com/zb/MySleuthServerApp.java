package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableZipkinStreamServer
@EnableDiscoveryClient
public class MySleuthServerApp {
    public static void main(String[] args) {
        SpringApplication.run(MySleuthServerApp.class,args);
    }
}
