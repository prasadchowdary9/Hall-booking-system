package com.example.demo.dto;

import java.util.List;

public class VenueDTO {
  private Long id;
  private String name;
  private String location;
  private String city;
  private String description;
  private String image;
  private List<String> images;
  private double price;
  private int capacity;
  private List<String> amenities;
  private double rating;
  private int reviewCount;
  private List<AvailabilityDTO> availability;
  private boolean featured;
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getLocation() { return location; }
  public void setLocation(String location) { this.location = location; }
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public String getImage() { return image; }
  public void setImage(String image) { this.image = image; }
  public List<String> getImages() { return images; }
  public void setImages(List<String> images) { this.images = images; }
  public double getPrice() { return price; }
  public void setPrice(double price) { this.price = price; }
  public int getCapacity() { return capacity; }
  public void setCapacity(int capacity) { this.capacity = capacity; }
  public List<String> getAmenities() { return amenities; }
  public void setAmenities(List<String> amenities) { this.amenities = amenities; }
  public double getRating() { return rating; }
  public void setRating(double rating) { this.rating = rating; }
  public int getReviewCount() { return reviewCount; }
  public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }
  public List<AvailabilityDTO> getAvailability() { return availability; }
  public void setAvailability(List<AvailabilityDTO> availability) { this.availability = availability; }
  public boolean isFeatured() { return featured; }
  public void setFeatured(boolean featured) { this.featured = featured; }
}
