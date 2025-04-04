package com.example.demo.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.ConfirmedBookingDTO;
import com.example.demo.mapper.BookingMapper;
import com.example.demo.mapper.ConfirmedBookingMapper;
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

    public List<ConfirmedBookingDTO> getBookingsByUserId(Long userId) {
        List<Booking> userBookings = bookingRepository.findByUserId(userId);
        List<ConfirmedBookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : userBookings) {
            bookingDTOs.add(ConfirmedBookingMapper.toDto(booking));
        }
        return bookingDTOs;
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    
    public String generateBookingEmailBody(Booking booking) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear ").append(booking.getName()).append(",\n\n");
        sb.append("✅ Your booking has been successfully confirmed!\n\n");
        sb.append("📌 Booking Details:\n");
        sb.append("• Booking ID: ").append(booking.getId()).append("\n");
        sb.append("• Venue: ").append(booking.getVenue()).append("\n");
        sb.append("• Start Date: ").append(booking.getDate()).append("\n");
        sb.append("• End Date: ").append(booking.getDate()).append("\n");
        sb.append("• Status: ").append("SUCCESS").append("\n\n");
        sb.append("Thank you for choosing us!\n");
        sb.append("— The VENUE HUB Team");
        return sb.toString();
    }
    
}
