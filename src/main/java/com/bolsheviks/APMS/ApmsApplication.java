package com.bolsheviks.APMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApmsApplication.class, args);
    }
}
