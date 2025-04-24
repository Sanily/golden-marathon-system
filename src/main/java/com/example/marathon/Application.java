package com.example.marathon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@EnableAsync
@MapperScan("com.example.*.mapper")
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.example")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }





}
