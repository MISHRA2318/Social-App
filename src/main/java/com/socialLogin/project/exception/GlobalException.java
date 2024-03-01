package com.socialLogin.project.exception;

import com.socialLogin.project.entity.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice   
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Errors> otherResponse(Exception ue, WebRequest req){
        Errors errors=new Errors(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
