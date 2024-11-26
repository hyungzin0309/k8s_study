package com.teamname.k8s_study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {

    private final LocalDateTime version = LocalDateTime.now();

    @GetMapping("/version")
    public String version(){
        return version.toString();
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
