package com.example.scservicehi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ScServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication.run( ScServiceHiApplication.class, args );
    }

    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "zhangsan") String name, HttpServletResponse request) {
        System.out.println("============================");
        System.out.println(request.toString());
        return "hi " + name + " ,i am from port:" + port;
    }


}
