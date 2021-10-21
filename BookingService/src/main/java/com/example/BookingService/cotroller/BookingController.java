package com.example.BookingService.cotroller;

import com.example.BookingService.dto.BookingInfoDto;
import com.example.BookingService.entity.BookingInfoEntity;
import com.example.BookingService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hotel")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity acceptBookingDetails(@RequestBody BookingInfoDto bookingInfoDto) {
        BookingInfoEntity bookingInfoEntity = bookingService.acceptBookingDetails(bookingInfoDto);
        return new ResponseEntity(bookingInfoEntity, HttpStatus.CREATED);
    }
}
