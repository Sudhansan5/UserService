package com.sudhan.userservice.Advice;

import com.sudhan.userservice.DTOs.ExceptionDTO;
import com.sudhan.userservice.Exceptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionDTO> handleInvalidTokenException(){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage("Unauthorized access, please try again with correct credentials");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.UNAUTHORIZED);
    }
}
