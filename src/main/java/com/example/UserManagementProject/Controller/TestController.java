package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.Exceptions.SahandException;
import com.example.UserManagementProject.Service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController implements ControllerInterface {
    final UserServiceImpl userServiceImpl;

    @Autowired
    public TestController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping("/test")
    public String index() {
        throw new SahandException("Sahand Exception!");
    }
}
