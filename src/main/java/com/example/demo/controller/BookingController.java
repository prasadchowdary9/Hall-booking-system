package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.ConfirmedBookingDTO;
import com.example.demo.mapper.BookingMapper;
import com.example.demo.model.Booking;
import com.example.demo.model.User;
import com.example.demo.model.Venue;
import com.example.demo.service.BookingService;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import com.example.demo.service.VenueService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private EmailService emailService;
    
    
    
    
    
    

    @Autowired
    private UserService userService;

    @Autowired
    private VenueService venueService;

    // Get all bookings (Admin Use)
    @GetMapping("/allbookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // Get bookings for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ConfirmedBookingDTO>> getBookingsByUserId(@PathVariable Long userId) {
        List<ConfirmedBookingDTO> userBookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(userBookings);
    }

    // Create a booking for a specific user
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createBookingForUser(@PathVariable Long userId, 
                                                  @RequestBody BookingDTO bookingDTO,
                                                  @RequestParam Long venueId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        System.out.println("booking method called");

        Venue venue = venueService.getVenueById(venueId);
        if (venue == null) {
            return ResponseEntity.badRequest().body("Venue not found");
        }
//        User userById = userService.getUserById(userId);

        Booking booking = BookingMapper.dtoToEntity(bookingDTO);
 String emailBody = bookingService.generateBookingEmailBody(booking);
        System.out.println("mail is sending"); 
        emailService.sendHtmlEmail(user.getEmail(),"Booking Confirmation Status", emailBody);
        System.out.println("mail sent ");
        booking.setUser(user);
        booking.setVenue(venue);
        
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok("Booking successfull"+createdBooking);
//        return ResponseEntity.ok("Booking successful! Your booking ID is: " + createdBooking.getId());
    }
}
