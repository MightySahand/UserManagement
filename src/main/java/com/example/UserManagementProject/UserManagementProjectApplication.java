package com.example.UserManagementProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class UserManagementProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementProjectApplication.class, args);
    }

}
