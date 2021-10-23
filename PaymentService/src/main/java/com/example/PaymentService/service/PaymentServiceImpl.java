package com.example.PaymentService.service;

import com.example.PaymentService.dao.TransactionDetailsDao;
import com.example.PaymentService.dto.TransactionDetailsDto;
import com.example.PaymentService.entity.TransactionDetailsEntity;
import com.example.PaymentService.exception.InvalidTransactionIdException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private TransactionDetailsDao transactionDetailsDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int acceptTransactionDetails(TransactionDetailsDto transactionDetailsDto) {

        TransactionDetailsEntity transactionDetailsEntity = modelMapper
                .map(transactionDetailsDto, TransactionDetailsEntity.class);

        TransactionDetailsEntity response = transactionDetailsDao.save(transactionDetailsEntity);

        return response.getId();
    }

    @Override
    public TransactionDetailsDto getTransactionDetails(int transactionId) {

        TransactionDetailsEntity transactionDetailsEntity = transactionDetailsDao.findById(transactionId)
                .orElseThrow(() -> new InvalidTransactionIdException());

        TransactionDetailsDto transactionDetailsDto = modelMapper
                .map(transactionDetailsEntity, TransactionDetailsDto.class);

        return transactionDetailsDto;
    }
}
