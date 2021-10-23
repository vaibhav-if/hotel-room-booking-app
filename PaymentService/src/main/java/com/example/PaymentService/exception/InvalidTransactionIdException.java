package com.example.PaymentService.exception;

public class InvalidTransactionIdException extends RuntimeException{
    public InvalidTransactionIdException() {
        super("Invalid Transaction Id");
    }
}
