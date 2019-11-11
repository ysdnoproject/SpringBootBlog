package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(nameGenerator = FQCNBeanNameGenerator.class)
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.run(args);
    }
}
