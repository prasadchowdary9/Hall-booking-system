package com.example.demo.dto;

public class SlotDTO {
  private Long id;
  private String time;
  private boolean available;
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getTime() { return time; }
  public void setTime(String time) { this.time = time; }
  public boolean isAvailable() { return available; }
  public void setAvailable(boolean available) { this.available = available; }
}
