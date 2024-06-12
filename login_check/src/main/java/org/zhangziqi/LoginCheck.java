package org.zhangziqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LoginCheck {
    public static void main(String[] args) {
        SpringApplication.run(LoginCheck.class, args);
    }
}