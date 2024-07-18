package com.zaultavangar.ecommerce.handler;

import com.zaultavangar.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ProductPurchaseException.class)
  public ResponseEntity<String> handle(ProductPurchaseException exception){
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMsg());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handle(EntityNotFoundException exception){
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception){
    var errors = new HashMap<String, String>();
    exception.getBindingResult()
        .getAllErrors()
        .forEach(error -> {
          var fieldName = ((FieldError) error).getField();
          var errorMessage = error.getDefaultMessage();
          errors.put(fieldName, errorMessage);
        });

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(errors));
  }
}
