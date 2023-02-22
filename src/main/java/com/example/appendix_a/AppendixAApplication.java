package com.example.appendix_a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppendixAApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppendixAApplication.class, args);

        }
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
