package com.example.BookingService.service;

import com.example.BookingService.dto.BookingInfoRequestDto;
import com.example.BookingService.dto.BookingInfoResponseDto;
import com.example.BookingService.dto.PaymentDetailsDto;

public interface BookingService {

    public BookingInfoResponseDto acceptBookingDetails(BookingInfoRequestDto bookingInfoRequestDto);

    public BookingInfoResponseDto acceptPaymentDetails(int bookingId, PaymentDetailsDto paymentDetailsDto);
}
