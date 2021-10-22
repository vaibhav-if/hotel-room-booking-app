package com.example.PaymentService.controller;

import com.example.PaymentService.dto.TransactionDetailsDto;
import com.example.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity acceptTransactionDetails(
            @RequestBody TransactionDetailsDto transactionDetailsDto) {

        int transactionId = paymentService.acceptTransactionDetails(transactionDetailsDto);

        return new ResponseEntity(transactionId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/transaction/{transactionId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTransactionDetails(@PathVariable int transactionId) {

        TransactionDetailsDto transactionDetailsDto = paymentService.
                getTransactionDetails(transactionId);

        return new ResponseEntity(transactionDetailsDto, HttpStatus.OK);
    }
}