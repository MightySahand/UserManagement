package com.example.UserManagementProject.Aspect;


import com.example.UserManagementProject.Service.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
@Configuration
@Order(1)
public class LoginRequiredAspect {
    // Aspect of Checking the login status
    @Pointcut(value = "@annotation(com.example.UserManagementProject.Annotation.LoginRequired)" +
            " && args(token,..)")
    public void LoginRequired(String token) {
    }

    @Before(value = "LoginRequired(token)", argNames = "joinPoint,token")
    public void Login(JoinPoint joinPoint, String token) throws Throwable {
        log.info("In LoginRequired Aspect");
        // Checks if the user is logged in or not
        // Checking if the Token Cookie is present
        if (!token.equals("")) {
            // The User has A JWT Token
            // Checks if the user's token is valid or not
            log.warn(token);
            if (JWTToken.ValidateToken(token)) {
                log.warn("The Token is valid!");
                // The Token is valid
                // It means the user is logged in
                return;
            }
            throw new RuntimeException("Login Token is not valid!");
        }
        throw new RuntimeException("You are not login");
    }
}
