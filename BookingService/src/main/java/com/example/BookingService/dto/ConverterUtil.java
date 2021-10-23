package com.example.BookingService.dto;

import com.example.BookingService.entity.BookingInfoEntity;

public class ConverterUtil {

    private BookingInfoEntity dtoToEntity(BookingInfoResponseDto bookingInfoResponseDto) {
        return null;
    }

    private BookingInfoResponseDto entityToDto(BookingInfoEntity bookingInfoEntity) {
        BookingInfoResponseDto bookingInfoResponseDto = new BookingInfoResponseDto();
        bookingInfoResponseDto.setId(bookingInfoEntity.getid());
        bookingInfoResponseDto.setFromDate(bookingInfoEntity.getFromDate());
        return null;
    }
}
