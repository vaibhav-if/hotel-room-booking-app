package com.example.BookingService.dao;

import com.example.BookingService.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingInfoDao extends JpaRepository<BookingInfoEntity, Integer> {
}
