package com.example.UserManagementProject.Service;

import com.example.UserManagementProject.Exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class, RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {SahandException.class})
    protected ResponseEntity<Object> HandleSahandException(SahandException ex, WebRequest request) {
        String bodyOfResponse = "Sahand Exception has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {UserNotFoundException.class})
    protected ResponseEntity<Object> HandleUSER_NOT_FOUNDException(UserNotFoundException ex, WebRequest request) {
        String bodyOfResponse = "UserNotFoundException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {PasswordDoesNotMatchException.class})
    protected ResponseEntity<Object> HandlePASSWORD_DOES_NOT_MATCHException(PasswordDoesNotMatchException ex, WebRequest request) {
        String bodyOfResponse = "PasswordDoesNotMatchException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {UserNameWasUsedBeforeException.class})
    protected ResponseEntity<Object> HandleUSERNAME_WAS_USED_BEFOREException(UserNameWasUsedBeforeException ex, WebRequest request) {
        String bodyOfResponse = "UserNameWasUsedBeforeException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {UserNameIsEmptyException.class})
    protected ResponseEntity<Object> HandleUSERNAME_IS_EMPTYException(UserNameIsEmptyException ex, WebRequest request) {
        String bodyOfResponse = "UserNameIsEmptyException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {InsufficientAmountOfMoneyException.class})
    protected ResponseEntity<Object> HandleINSUFFICIENT_AMOUNT_OF_MONEYException(InsufficientAmountOfMoneyException ex, WebRequest request) {
        String bodyOfResponse = "InsufficientAmountOfMoneyException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {IsNotLoggedInException.class})
    protected ResponseEntity<Object> HandleIS_NOT_LOGGED_INException(IsNotLoggedInException ex, WebRequest request) {
        String bodyOfResponse = "IsNotLoggedInException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }


}