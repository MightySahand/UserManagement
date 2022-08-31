package com.example.UserManagementProject.Service;

import com.example.UserManagementProject.Exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
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
            = {USER_NOT_FOUNDException.class})
    protected ResponseEntity<Object> HandleUSER_NOT_FOUNDException(USER_NOT_FOUNDException ex, WebRequest request) {
        String bodyOfResponse = "USER_NOT_FOUNDException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {PASSWORD_DOES_NOT_MATCHException.class})
    protected ResponseEntity<Object> HandlePASSWORD_DOES_NOT_MATCHException(PASSWORD_DOES_NOT_MATCHException ex, WebRequest request) {
        String bodyOfResponse = "PASSWORD_DOES_NOT_MATCHException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {USERNAME_WAS_USED_BEFOREException.class})
    protected ResponseEntity<Object> HandleUSERNAME_WAS_USED_BEFOREException(USERNAME_WAS_USED_BEFOREException ex, WebRequest request) {
        String bodyOfResponse = "USERNAME_WAS_USED_BEFOREException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {USERNAME_IS_EMPTYException.class})
    protected ResponseEntity<Object> HandleUSERNAME_IS_EMPTYException(USERNAME_IS_EMPTYException ex, WebRequest request) {
        String bodyOfResponse = "USERNAME_IS_EMPTYException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {INSUFFICIENT_AMOUNT_OF_MONEYException.class})
    protected ResponseEntity<Object> HandleINSUFFICIENT_AMOUNT_OF_MONEYException(INSUFFICIENT_AMOUNT_OF_MONEYException ex, WebRequest request) {
        String bodyOfResponse = "INSUFFICIENT_AMOUNT_OF_MONEYException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }

    @ExceptionHandler(value
            = {IS_NOT_LOGGED_INException.class})
    protected ResponseEntity<Object> HandleIS_NOT_LOGGED_INException(IS_NOT_LOGGED_INException ex, WebRequest request) {
        String bodyOfResponse = "IS_NOT_LOGGED_INException has Occurred";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }


}