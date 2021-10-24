package com.example.BookingService.service;

import com.example.BookingService.dto.BookingInfoRequestDto;
import com.example.BookingService.dto.BookingInfoResponseDto;
import com.example.BookingService.dto.PaymentDetailsDto;
import com.example.BookingService.entity.BookingInfoEntity;
import com.example.BookingService.dao.BookingInfoDao;
import com.example.BookingService.exception.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;


@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingInfoDao bookingInfoDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    private String roomNumbers;

    private int roomPrice;

    private int transactionId;

    private String confirmationMessage;

    @Value("${transactionApp.url}")
    private String transactionUrl;

    @Override
    public BookingInfoResponseDto acceptBookingDetails(BookingInfoRequestDto bookingInfoRequestDto) {

        BookingInfoEntity bookingInfoEntity = new BookingInfoEntity();

        roomNumbers = getRandomNumbers(bookingInfoRequestDto.getNumOfRooms());

        roomPrice = getRoomPrice(bookingInfoRequestDto);

        //Convert bookingInfoDto to bookingInfoEntity
        bookingInfoEntity.setFromDate(bookingInfoRequestDto.getFromDate().toLocalDateTime());
        bookingInfoEntity.setToDate(bookingInfoRequestDto.getToDate().toLocalDateTime());
        bookingInfoEntity.setAadharNumber(bookingInfoRequestDto.getAadharNumber());
        bookingInfoEntity.setNumOfRooms(bookingInfoRequestDto.getNumOfRooms());

        //Add additional info to entity
        bookingInfoEntity.setRoomNumbers(roomNumbers);
        bookingInfoEntity.setRoomPrice(roomPrice);
        bookingInfoEntity.setBookedOn(LocalDateTime.now());

        BookingInfoEntity response =  bookingInfoDao.save(bookingInfoEntity);

        BookingInfoResponseDto bookingInfoResponseDto = modelMapper.map(
                response, BookingInfoResponseDto.class
        );

        return bookingInfoResponseDto;
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

    //Helper function to calculate room price
    private int getRoomPrice(BookingInfoRequestDto bookingInfoRequestDto) {

        BookingInfoEntity bookingInfoEntity = new BookingInfoEntity();

        //get days between fromDate and toDate
        int days = (int) ChronoUnit.DAYS.between(bookingInfoRequestDto.getFromDate().toLocalDateTime()
                , bookingInfoRequestDto.getToDate().toLocalDateTime());

        //if fromDate and toDate are same, then calculate roomPrice for 1 day
        days = (days == 0) ?  1 :  days;

        //if fromDate > toDate, throw exception
        if(days < 0) {
            throw new DateIncorrectException();
        }

        //gets default price for single room from entity (currently 1000) to calculate total room price
        int roomPrice = bookingInfoEntity.getRoomPrice() * days * bookingInfoRequestDto.getNumOfRooms();

        return roomPrice;
    }

    @Override
    public BookingInfoResponseDto acceptPaymentDetails(int bookingId, PaymentDetailsDto paymentDetailsDto) {

        //--------- Exception Handling -----------
        //Booking id not found
        BookingInfoEntity bookingInfoEntity = bookingInfoDao.findById(bookingId)
                .orElseThrow(() -> new InvalidBookingIdException());


        String paymentMode = paymentDetailsDto.getPaymentMode().toUpperCase();

        //Invalid payment mode entered
        if(!(paymentMode.equals("CARD") || paymentMode.equals("UPI"))) {
            throw new InvalidPaymentModeException();
        }

        //To handle any mismatch in bookingId path variable and paymentDto bookingId request body
        if(paymentDetailsDto.getBookingId() != bookingId) {
            throw new BookingIdMismatchException();
        }

        //Card details or upi id not found respectively
        if(paymentMode.equals("CARD") && paymentDetailsDto.getCardNumber().equals("")) {
            throw new CardDetailsNotFoundException();
        } else if (paymentMode.equals("UPI") && paymentDetailsDto.getUpiId().equals("")) {
            throw new UpiIdNotFoundException();
        }
        //--------- Exception Handling ends here -----------

//        String url = "http://localhost:9191/payment/transaction";

        transactionId = restTemplate.postForObject(transactionUrl, paymentDetailsDto, Integer.class);

        bookingInfoEntity.setTransactionId(transactionId);

        //update transaction id in database
        BookingInfoEntity updatedBookingInfoEntity = bookingInfoDao.save(bookingInfoEntity);

        confirmationMessage = "Booking confirmed for user with aadhaar number: "
                + updatedBookingInfoEntity.getAadharNumber()
                +    "    |    "
                + "Here are the booking details:    " + updatedBookingInfoEntity.toString();

        System.out.println(confirmationMessage);

        BookingInfoResponseDto bookingInfoResponseDto = modelMapper.map(
                updatedBookingInfoEntity, BookingInfoResponseDto.class
        );

        return bookingInfoResponseDto;
    }
}
