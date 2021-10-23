package com.example.BookingService.exception;

public class CardDetailsNotFoundException extends RuntimeException {
    public CardDetailsNotFoundException() {
        super("Card details not found");
    }
}
