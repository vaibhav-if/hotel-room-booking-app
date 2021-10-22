package com.example.PaymentService.dao;

import com.example.PaymentService.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsDao extends JpaRepository<TransactionDetailsEntity, Integer> {
}
