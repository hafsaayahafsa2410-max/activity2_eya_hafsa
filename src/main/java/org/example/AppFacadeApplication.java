package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
@SpringBootApplication @EnableJms
public class AppFacadeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppFacadeApplication.class, args); }
}