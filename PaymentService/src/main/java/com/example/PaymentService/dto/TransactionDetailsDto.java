package com.example.PaymentService.dto;


public class TransactionDetailsDto {

    //represents transactionId from entity
    private int id;

    private String paymentMode;

    private int bookingId;

    private String upiId;

    private String CardNumber;

    public TransactionDetailsDto() {}

    public TransactionDetailsDto(int id, String paymentMode, int bookingId, String upiId, String cardNumber) {
        this.id = id;
        this.paymentMode = paymentMode;
        this.bookingId = bookingId;
        this.upiId = upiId;
        CardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "TransactionDetailsDto{" +
                "id=" + id +
                ", paymentMode='" + paymentMode + '\'' +
                ", bookingId=" + bookingId +
                ", upiId='" + upiId + '\'' +
                ", CardNumber='" + CardNumber + '\'' +
                '}';
    }
}
