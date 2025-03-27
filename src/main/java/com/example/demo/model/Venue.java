package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "venues")
public class Venue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotBlank(message = "Venue name is required")
  private String name;
  
  @NotBlank(message = "Location is required")
  private String location;
  
  private String city;
  
  @Column(length = 2000)
  private String description;
  
  private String image;
  
  @ElementCollection
  @CollectionTable(name = "venue_images", joinColumns = @JoinColumn(name = "venue_id"))
  @Column(name = "image_url")
  private List<String> images = new ArrayList<String>();
  
  private double price;
  private int capacity;
  
  @ElementCollection
  @CollectionTable(name = "venue_amenities", joinColumns = @JoinColumn(name = "venue_id"))
  @Column(name = "amenity")
  private List<String> amenities = new ArrayList<String>();
  
  private double rating;
  private int reviewCount;
  private boolean featured;
  
  @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Availability> availability = new ArrayList<Availability>();
  
  // Getters and Setters (manually written)
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
  public boolean isFeatured() { return featured; }
  public void setFeatured(boolean featured) { this.featured = featured; }
  public List<Availability> getAvailability() { return availability; }
  public void setAvailability(List<Availability> availability) { this.availability = availability; }
}
