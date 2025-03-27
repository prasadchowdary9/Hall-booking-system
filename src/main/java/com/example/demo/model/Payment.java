package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private Double amount;
  private String paymentMethod;
  private String paymentStatus;
  private LocalDateTime paymentDate;
  
  @OneToOne
  @JoinColumn(name = "booking_id")
  private Booking booking;
  
  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Double getAmount() { return amount; }
  public void setAmount(Double amount) { this.amount = amount; }
  public String getPaymentMethod() { return paymentMethod; }
  public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
  public String getPaymentStatus() { return paymentStatus; }
  public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
  public LocalDateTime getPaymentDate() { return paymentDate; }
  public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
  public Booking getBooking() { return booking; }
  public void setBooking(Booking booking) { this.booking = booking; }
}

