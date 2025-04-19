package com.example.demo.mapper;



import com.example.demo.dto.ConfirmedBookingDTO;
import com.example.demo.model.Booking;
import com.example.demo.model.Venue;

public class ConfirmedBookingMapper {

    // Convert Booking entity to DTO
    public static ConfirmedBookingDTO toDto(Booking booking) {
        ConfirmedBookingDTO dto = new ConfirmedBookingDTO();
        dto.setId(booking.getId());
        dto.setDate(booking.getDate());
        dto.setTime(booking.getTimeSlot());
        dto.setGuestCount(booking.getGuestCount());
        dto.setStatus(booking.getStatus());
        dto.setTotalPrice(
            booking.getTotalPrice()
        );
        dto.setVenueId(booking.getVenueId()+"");
        dto.setVenueName(booking.getVenue() );
        return dto;
    }

    // Convert DTO to Booking entity
    public static Booking toEntity(ConfirmedBookingDTO dto, Venue venue) {
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setDate(dto.getDate());
        booking.setTimeSlot(dto.getTime());
        booking.setGuestCount(dto.getGuestCount());
        booking.setStatus(dto.getStatus());
        booking.setVenue(venue.getName());
        return booking;
    }
}
