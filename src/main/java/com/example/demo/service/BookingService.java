package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {

  @Autowired
  private BookingRepository bookingRepository;
  
  public Booking createBooking(Booking booking) {
    return bookingRepository.save(booking);
  }
  
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }
  
  public Booking getBookingById(Long id) {
    return bookingRepository.findById(id).orElse(null);
  }
  
  public boolean deleteBooking(Long id) {
    Booking booking = getBookingById(id);
    if (booking != null) {
      bookingRepository.delete(booking);
      return true;
    }
    return false;
  }
}
