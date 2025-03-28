package com.example.demo.mapper;

import com.example.demo.dto.BookingDTO;
import com.example.demo.model.Booking;

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

    public static BookingDTO entityToDto(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setDate(booking.getDate());
        dto.setTimeSlot(booking.getTimeSlot());
        dto.setGuestCount(booking.getGuestCount());
        dto.setName(booking.getName());
        dto.setEmail(booking.getEmail());
        dto.setPhone(booking.getPhone());
        dto.setEventType(booking.getEventType());
        dto.setSpecialRequests(booking.getSpecialRequests());
        dto.setPaymentMethod(booking.getPaymentMethod());
        return dto;
    }
}
