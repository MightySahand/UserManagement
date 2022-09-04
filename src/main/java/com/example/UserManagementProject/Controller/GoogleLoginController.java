package com.example.UserManagementProject.Controller;

import com.example.UserManagementProject.Service.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class GoogleLoginController {

    final GoogleDriveService googleDriveService;

    @Autowired
    public GoogleLoginController(GoogleDriveService googleDriveService) {
        this.googleDriveService = googleDriveService;
    }

    @RequestMapping("googleLogin")
    public String showGoogleLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(googleDriveService.getAuthorizationURL());
        return "Google Login";
    }
}
