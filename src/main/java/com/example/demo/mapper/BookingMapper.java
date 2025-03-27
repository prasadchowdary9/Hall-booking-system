package com.example.demo.mapper;

import com.example.demo.dto.BookingDTO;
import com.example.demo.model.Booking;
import java.time.LocalDate;

public class BookingMapper {

  public static Booking dtoToEntity(BookingDTO dto) {
    Booking booking = new Booking();
    booking.setDate(dto.getDate());
    booking.setTimeSlot(dto.getTimeSlot());
    booking.setGuestCount(dto.getGuestCount());
    booking.setName(dto.getName());
    booking.setEmail(dto.getEmail());
    booking.setPhone(dto.getPhone());
    booking.setEventType(dto.getEventType());
    booking.setSpecialRequests(dto.getSpecialRequests());
    booking.setPaymentMethod(dto.getPaymentMethod());
    // The status and bookingTime are set by default
    return booking;
  }
}
