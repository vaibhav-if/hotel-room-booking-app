package com.example.BookingService.service;

import com.example.BookingService.dto.BookingInfoDto;
import com.example.BookingService.entity.BookingInfoEntity;

public interface BookingService {

    public BookingInfoEntity acceptBookingDetails(BookingInfoDto bookingInfoDto);

    public BookingInfoEntity acceptPaymentDetails(BookingInfoDto bookingInfoDto);
}
