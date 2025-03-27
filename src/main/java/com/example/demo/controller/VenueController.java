package com.example.demo.controller;

import com.example.demo.dto.VenueDTO;
import com.example.demo.mapper.VenueMapper;
import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/venues")
@CrossOrigin(origins = "*")
public class VenueController {

  @Autowired
  private VenueService venueService;
  
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Venue> createVenue(@RequestBody VenueDTO venueDTO) {
    Venue venue = VenueMapper.dtoToEntity(venueDTO);
    Venue createdVenue = venueService.createVenue(venue);
    return ResponseEntity.ok(createdVenue);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Venue> getVenueById(@PathVariable Long id) {
    Venue venue = venueService.getVenueById(id);
    return venue != null ? ResponseEntity.ok(venue) : ResponseEntity.notFound().build();
  }
  
  @GetMapping
  public ResponseEntity<List<Venue>> getAllVenues() {
    return ResponseEntity.ok(venueService.getAllVenues());
  }
  
  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Venue> updateVenue(@PathVariable Long id, @RequestBody VenueDTO venueDTO) {
    Venue updatedVenue = VenueMapper.dtoToEntity(venueDTO);
    Venue venue = venueService.updateVenue(id, updatedVenue);
    return venue != null ? ResponseEntity.ok(venue) : ResponseEntity.notFound().build();
  }
  
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> deleteVenue(@PathVariable Long id) {
    return venueService.deleteVenue(id)
        ? ResponseEntity.ok("Venue deleted successfully")
        : ResponseEntity.notFound().build();
  }
}
