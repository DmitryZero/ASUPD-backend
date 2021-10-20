package com.bolsheviks.APMS.health;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthController {

    @GetMapping("/about")
    public String about() {
        return "Автоматизированная система управления проектами";
    }

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public void ping() { }
}
