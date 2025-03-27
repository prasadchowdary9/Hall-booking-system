package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private LocalDate date;
  private String timeSlot;
  private int guestCount;
  private String name;
  private String email;
  private String phone;
  private String eventType;
  
  @Column(length = 1000)
  private String specialRequests;
  
  private String paymentMethod;
  private String status = "PENDING";
  private LocalDateTime bookingTime = LocalDateTime.now();
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "venue_id")
  private Venue venue;
  
  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public LocalDate getDate() { return date; }
  public void setDate(LocalDate date) { this.date = date; }
  public String getTimeSlot() { return timeSlot; }
  public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
  public int getGuestCount() { return guestCount; }
  public void setGuestCount(int guestCount) { this.guestCount = guestCount; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
  public String getEventType() { return eventType; }
  public void setEventType(String eventType) { this.eventType = eventType; }
  public String getSpecialRequests() { return specialRequests; }
  public void setSpecialRequests(String specialRequests) { this.specialRequests = specialRequests; }
  public String getPaymentMethod() { return paymentMethod; }
  public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public LocalDateTime getBookingTime() { return bookingTime; }
  public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }
  public Venue getVenue() { return venue; }
  public void setVenue(Venue venue) { this.venue = venue; }
}
