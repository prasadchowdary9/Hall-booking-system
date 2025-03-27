package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "slots")
public class Slot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String time;
  private boolean available;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "availability_id")
  private Availability availability;
  
  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getTime() { return time; }
  public void setTime(String time) { this.time = time; }
  public boolean isAvailable() { return available; }
  public void setAvailable(boolean available) { this.available = available; }
  public Availability getAvailability() { return availability; }
  public void setAvailability(Availability availability) { this.availability = availability; }
}
