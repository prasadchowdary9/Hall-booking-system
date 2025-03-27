package com.example.demo.service;

import com.example.demo.model.Venue;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VenueService {

  @Autowired
  private VenueRepository venueRepository;
  
  public Venue createVenue(Venue venue) {
    return venueRepository.save(venue);
  }
  
  public Venue getVenueById(Long id) {
    return venueRepository.findById(id).orElse(null);
  }
  
  public List<Venue> getAllVenues() {
    return venueRepository.findAll();
  }
  
  public Venue updateVenue(Long id, Venue updatedVenue) {
    Venue venue = getVenueById(id);
    if (venue != null) {
      venue.setName(updatedVenue.getName());
      venue.setLocation(updatedVenue.getLocation());
      venue.setCity(updatedVenue.getCity());
      venue.setDescription(updatedVenue.getDescription());
      venue.setImage(updatedVenue.getImage());
      venue.setImages(updatedVenue.getImages());
      venue.setPrice(updatedVenue.getPrice());
      venue.setCapacity(updatedVenue.getCapacity());
      venue.setAmenities(updatedVenue.getAmenities());
      venue.setRating(updatedVenue.getRating());
      venue.setReviewCount(updatedVenue.getReviewCount());
      venue.setFeatured(updatedVenue.isFeatured());
      venue.setAvailability(updatedVenue.getAvailability());
      return venueRepository.save(venue);
    }
    return null;
  }
  
  public boolean deleteVenue(Long id) {
    Venue venue = getVenueById(id);
    if (venue != null) {
      venueRepository.delete(venue);
      return true;
    }
    return false;
  }
}
