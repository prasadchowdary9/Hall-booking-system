package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "availability")
public class Availability {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String date;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "venue_id")
  private Venue venue;
  
  @OneToMany(mappedBy = "availability", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Slot> slots = new ArrayList<Slot>();
  
  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getDate() { return date; }
  public void setDate(String date) { this.date = date; }
  public Venue getVenue() { return venue; }
  public void setVenue(Venue venue) { this.venue = venue; }
  public List<Slot> getSlots() { return slots; }
  public void setSlots(List<Slot> slots) { this.slots = slots; }
}
