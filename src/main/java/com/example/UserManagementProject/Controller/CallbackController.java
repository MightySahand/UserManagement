package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.Service.GoogleDriveService;
import com.example.UserManagementProject.Service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("/callback")
@CrossOrigin
public class CallbackController implements ControllerInterface {


    final GoogleDriveService googleDriveService;
    final UserServiceImpl userService;

    @Autowired
    public CallbackController(GoogleDriveService googleDriveService, UserServiceImpl userService) {
        this.googleDriveService = googleDriveService;
        this.userService = userService;
    }

    @RequestMapping("")
    public ModelAndView callBackIndexPage(HttpServletRequest req) {
//        log.info(access_token.getClass().getName());
//        googleDriveService.getCredentials(access_token);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index1.html");
        return modelAndView;
    }

    @RequestMapping(value = "/gettoken")
    public String getTokenFromJS(@RequestBody String token, HttpServletResponse response) {
        log.warn("Token is: " + token);
        userService.getService(token);
        try {
            response.sendRedirect("welcome");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Token was obtained successfully";
    }
}
