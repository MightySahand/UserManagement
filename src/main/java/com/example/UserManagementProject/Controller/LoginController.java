package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.Annotation.NotLoginRequired;
import com.example.UserManagementProject.DTO.UserDTO;
import com.example.UserManagementProject.Service.JWTToken;
import com.example.UserManagementProject.Service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class LoginController implements ControllerInterface {
    final UserServiceImpl userServiceImpl;

    @Autowired
    public LoginController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @NotLoginRequired
    @RequestMapping("/login")
    public String Login(@CookieValue(name = "JWTToken", defaultValue = "") String token,
                        @RequestBody UserDTO userDTO,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        Cookie c = new Cookie("JWTToken", userServiceImpl.logIn(userDTO));
        c.setMaxAge(60 * 1);
        response.addCookie(c);
        return "Logged In";

    }
}