package com.example.demo.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookingDTO;
import com.example.demo.mapper.BookingMapper;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.UserRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    
    
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDTOs.add(BookingMapper.entityToDto(booking));
        }
        return bookingDTOs;
    }

    public List<BookingDTO> getBookingsByUserId(Long userId) {
        List<Booking> userBookings = bookingRepository.findByUserId(userId);
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : userBookings) {
            bookingDTOs.add(BookingMapper.entityToDto(booking));
        }
        return bookingDTOs;
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
