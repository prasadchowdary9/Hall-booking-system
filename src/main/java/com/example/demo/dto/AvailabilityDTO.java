package com.example.demo.dto;

import java.util.List;

public class AvailabilityDTO {
  private Long id;
  private String date;
  private List<SlotDTO> slots;
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getDate() { return date; }
  public void setDate(String date) { this.date = date; }
  public List<SlotDTO> getSlots() { return slots; }
  public void setSlots(List<SlotDTO> slots) { this.slots = slots; }
}
