package com.example.PaymentService.service;

import com.example.PaymentService.dto.TransactionDetailsDto;

public interface PaymentService {

    public int acceptTransactionDetails(TransactionDetailsDto transactionDetailsDto);
    public TransactionDetailsDto getTransactionDetails(int transactionId);
}
