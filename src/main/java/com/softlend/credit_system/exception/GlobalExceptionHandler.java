package com.softlend.credit_system.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.softlend.credit_system.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleCustomerNotFound(
            CustomerNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        ex.getMessage(),
                        "CUSTOMER_NOT_FOUND"));
    }

    @ExceptionHandler(OfferLockedException.class)
    public ResponseEntity<ErrorResponse>
    handleOfferLocked(
            OfferLockedException ex) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse(
                        ex.getMessage(),
                        "OFFER_LOCKED"));
    }
    
    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleOfferNotFound(
            OfferNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        ex.getMessage(),
                        "OFFER_NOT_FOUND"));
    }
}