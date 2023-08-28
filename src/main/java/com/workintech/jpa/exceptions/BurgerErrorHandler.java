package com.workintech.jpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@ControllerAdvice
public class BurgerErrorHandler {
    @ExceptionHandler
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){
        List errorList = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> {
                    System.out.println("Field Error: " + fieldError.getField());
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(Exception exception) {
        BurgerErrorResponse response = new BurgerErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
