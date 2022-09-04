package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.DTO.UserDTO;
import com.example.UserManagementProject.Service.JWTToken;
import com.example.UserManagementProject.Service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@CrossOrigin
public class SignUpController implements ControllerInterface {
    final UserServiceImpl userServiceImpl;

    @Autowired
    public SignUpController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    // Creating new Account
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String signUp(@RequestBody UserDTO userDTO,
                         HttpServletResponse response,
                         @CookieValue(name = "JWTToken", defaultValue = "") String token) {
        if (token.equals("")) {
            if (userServiceImpl.readUser(userDTO) == null) {
                userServiceImpl.createUser(userDTO);
                token = JWTToken.getToken(userDTO);
                Cookie c = new Cookie("JWTToken", JWTToken.getToken(userDTO));
                c.setMaxAge(60 * 1);
                response.addCookie(c);
                log.info("Going to extract data from token!");
                return "Welcome, " + JWTToken.ExtractInfoFromToken(token, "username");
            } else {
                return "You have signed up, login into your existing Account!";
            }
        } else {
            log.info("The User is logged In and you cant make a new account!");
        }
        return "Didn't Signed Up!";
    }
}
