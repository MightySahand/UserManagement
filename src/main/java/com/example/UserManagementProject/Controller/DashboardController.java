package com.example.UserManagementProject.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/dashboard")
public class DashboardController implements ControllerInterface {

    @GetMapping(value = {"", "/"})
    public Object showDashboard(HttpServletResponse response) throws IOException {
        log.info("In DashBoard!");
        response.sendRedirect("login");
        return "Dashboard!";
    }

    @PostMapping(value = "getbalance")
    public void dashboardGetBalance(HttpServletResponse response) throws IOException {
        response.sendRedirect("getbalance");
    }

    @PostMapping(value = "logout")
    public Object dashboardLogOut(HttpServletResponse response) throws IOException {
        response.sendRedirect("logout");
        return "";
    }

    @PostMapping(value = "showgoogle")
    public void showGoogle(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://www.google.com/");
    }

}
