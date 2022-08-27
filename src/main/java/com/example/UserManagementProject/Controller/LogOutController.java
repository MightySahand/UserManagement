package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.Annotation.LoginRequired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/dashboard/logout")
public class LogOutController {

    @LoginRequired
    @RequestMapping("")
    public String logout(@CookieValue(name = "JWTToken", value = "", required = false) String token,
                         HttpServletResponse response) {
        Cookie c = new Cookie("JWTToken", "");
        c.setMaxAge(0);
        response.addCookie(c);
        return "You are logged out!";
    }
}
