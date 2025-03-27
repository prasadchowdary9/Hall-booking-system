package com.example.demo.controller;

import com.example.demo.dto.BookingDTO;
import com.example.demo.mapper.BookingMapper;
import com.example.demo.model.Booking;
import com.example.demo.model.User;
import com.example.demo.model.Venue;
import com.example.demo.service.BookingService;
import com.example.demo.service.UserService;
import com.example.demo.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

  @Autowired
  private BookingService bookingService;
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private VenueService venueService;
  
  @PostMapping
  public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO,
                                           @RequestParam Long venueId,
                                           @AuthenticationPrincipal UserDetails userDetails) {
    // Get the logged-in user
    User user = userService.findByEmail(userDetails.getUsername());
    if (user == null) {
      return ResponseEntity.badRequest().body("User not found");
    }
    // Get the venue
    Venue venue = venueService.getVenueById(venueId);
    if (venue == null) {
      return ResponseEntity.badRequest().body("Venue not found");
    }
    Booking booking = BookingMapper.dtoToEntity(bookingDTO);
    booking.setUser(user);
    booking.setVenue(venue);
    Booking createdBooking = bookingService.createBooking(booking);
    return ResponseEntity.ok("Booking successful! Your booking id is: " + createdBooking.getId());
  }
}
