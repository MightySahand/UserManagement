package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.Exceptions.SahandException;
import com.example.UserManagementProject.Service.GoogleDriveService;
import com.example.UserManagementProject.Service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class TestController implements ControllerInterface {
    final UserServiceImpl userServiceImpl;
    final GoogleDriveService googleDriveService;

    @Autowired
    public TestController(UserServiceImpl userServiceImpl, GoogleDriveService googleDriveService) {
        this.userServiceImpl = userServiceImpl;
        this.googleDriveService = googleDriveService;
    }

    @RequestMapping(value = "/test")
    public String index( HttpServletRequest request) {
        googleDriveService.printFilesInformation(10);
        throw new SahandException("Sahand Exception!");
    }
}
