package com.MicroserviceEC.ecomerce.Excptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExptionHandler {

    @ExceptionHandler(CustomerNotFoundExption.class)
    public ResponseEntity<String> handleException(CustomerNotFoundExption e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespone> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var errors = new HashMap<String, String>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldname =((FieldError)error).getField();
            var message = error.getDefaultMessage();
            errors.put(fieldname,message);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorRespone(errors));
    }

}
