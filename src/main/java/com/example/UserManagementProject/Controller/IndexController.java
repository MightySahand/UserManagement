package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.Annotation.LoginRequired;
import com.example.UserManagementProject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin
public class IndexController implements ControllerInterface {
    final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @LoginRequired
    @RequestMapping(value = {"", "/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void ShowHome(@CookieValue(name = "JWTToken", defaultValue = "") String token,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        response.sendRedirect("/dashboard");
    }
}
