package com.smbms.my;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.smbms.my.dao")
public class MySmbmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySmbmsApplication.class, args);
    }

}
