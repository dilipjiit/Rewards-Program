


//CustomExceptionHandler.java
package com.example.rewardprogram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class CustomExceptionHandler {

 @ExceptionHandler(ResponseStatusException.class)
 public String handleResponseStatusException(ResponseStatusException ex) {
     return ex.getReason();
 }

 @ExceptionHandler(Exception.class)
 public String handleException(Exception ex) {
     return "An error occurred: " + ex.getMessage();
 }
}
