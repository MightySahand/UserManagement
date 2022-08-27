package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.Annotation.LoginRequired;
import com.example.UserManagementProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard/TransferMoney")
public class TransferMoneyController implements ControllerInterface {
    final UserService userService;

    @Autowired
    public TransferMoneyController(UserService userService) {
        this.userService = userService;
    }

    @LoginRequired
    @RequestMapping("")
    public String TransferMoney(@CookieValue(name = "JWTToken", defaultValue = "") String token, @RequestParam String destUserName, double amount) {
        userService.transferMoney(token, destUserName, amount);
        return "Transfering Money";
    }
}
