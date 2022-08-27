package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.Annotation.LoginRequired;
import com.example.UserManagementProject.DTO.UserDTO;
import com.example.UserManagementProject.Service.JWTToken;
import com.example.UserManagementProject.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard/getbalance")
public class GetBalanceController implements ControllerInterface {

    final UserServiceImpl userServiceImpl;

    @Autowired
    public GetBalanceController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    // get balance method which only runs when you are a member
    @LoginRequired
    @RequestMapping(value = "")
    public Object getBalanceStatus(@RequestBody UserDTO userDTO,
                                   @CookieValue(name = "JWTToken", defaultValue = "") String token) {
        if (!token.equals("") && JWTToken.ValidateToken(token)) {
            return new String(String.valueOf(userServiceImpl.getBalanceStatus(token)));
        }
        return new String("Access Denied!");
    }
}
