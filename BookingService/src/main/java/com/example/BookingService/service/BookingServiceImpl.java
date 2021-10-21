package com.example.BookingService.service;

import com.example.BookingService.dto.BookingInfoDto;
import com.example.BookingService.entity.BookingInfoEntity;
import com.example.BookingService.dao.BookingInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;


@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingInfoDao bookingRepository;

    @Override
    public BookingInfoEntity acceptBookingDetails(BookingInfoDto bookingInfoDto) {

        BookingInfoEntity bookingInfoEntity = new BookingInfoEntity();

        String roomNumbers = getRandomNumbers(bookingInfoDto.getNumOfRooms());

        //get days between fromDate and toDate
        int days = (int) ChronoUnit.DAYS.between(bookingInfoDto.getFromDate().toLocalDateTime()
                , bookingInfoDto.getToDate().toLocalDateTime());

        //todo 1: fix this using exception
        //if toDate is <= fromDate, then calculate roomPrice for 1 day
        days = (days <= 0) ?  1 :  days;

        int roomPrice = bookingInfoEntity.getRoomPrice() * days * bookingInfoDto.getNumOfRooms();

        //Convert bookingInfoDto to bookingInfoEntity
        bookingInfoEntity.setFromDate(bookingInfoDto.getFromDate().toLocalDateTime());
        bookingInfoEntity.setToDate(bookingInfoDto.getToDate().toLocalDateTime());
        bookingInfoEntity.setAadharNumber(bookingInfoDto.getAadharNumber());
        bookingInfoEntity.setNumOfRooms(bookingInfoDto.getNumOfRooms());
        bookingInfoEntity.setRoomNumbers(roomNumbers);
        bookingInfoEntity.setRoomPrice(roomPrice);
        bookingInfoEntity.setBookedOn(LocalDateTime.now());

        return bookingRepository.save(bookingInfoEntity);
    }

    //Helper function for generating random room numbers between 1 and 100
    private String getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<String>();

        for (int i = 0; i < count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        String listString = String.join(", ", numberList);
        return listString;
    }

    @Override
    public BookingInfoEntity acceptPaymentDetails(BookingInfoDto bookingInfoDto) {
        return null;
    }
}
