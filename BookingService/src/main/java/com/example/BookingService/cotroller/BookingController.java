package com.example.BookingService.cotroller;

import com.example.BookingService.dto.BookingInfoRequestDto;
import com.example.BookingService.dto.BookingInfoResponseDto;
import com.example.BookingService.dto.PaymentDetailsDto;
import com.example.BookingService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hotel")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity acceptBookingDetails(@RequestBody BookingInfoRequestDto bookingInfoRequestDto) {

        BookingInfoResponseDto bookingInfoResponseDto = bookingService.acceptBookingDetails(bookingInfoRequestDto);

        return new ResponseEntity(bookingInfoResponseDto, HttpStatus.CREATED);
    }


    @PostMapping(value = "booking/{bookingId}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity acceptPaymentDetails(@PathVariable int bookingId, @RequestBody PaymentDetailsDto paymentDetailsDto) {

        BookingInfoResponseDto bookingInfoResponseDto = bookingService.acceptPaymentDetails(bookingId, paymentDetailsDto);

        return new ResponseEntity(bookingInfoResponseDto, HttpStatus.CREATED);
    }
}
