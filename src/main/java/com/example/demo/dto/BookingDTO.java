package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.model.Venue;

public class BookingDTO {
  private LocalDate date;
  private String timeSlot;
  private int guestCount;
  private String name;
  private String email;
  private String phone;
  private String eventType;
  private String specialRequests;
  private boolean agreeToTerms;
  private String paymentMethod;
  private Venue venue;
  
  public Venue getVenue() {
	return venue;
}
public void setVenue(Venue venue) {
	this.venue = venue;
}
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
  public boolean isAgreeToTerms() { return agreeToTerms; }
  public void setAgreeToTerms(boolean agreeToTerms) { this.agreeToTerms = agreeToTerms; }
  public String getPaymentMethod() { return paymentMethod; }
  public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
