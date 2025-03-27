package com.example.demo.mapper;

import com.example.demo.dto.AvailabilityDTO;
import com.example.demo.dto.SlotDTO;
import com.example.demo.dto.VenueDTO;
import com.example.demo.model.Availability;
import com.example.demo.model.Slot;
import com.example.demo.model.Venue;
import java.util.ArrayList;
import java.util.List;

public class VenueMapper {

    public static Venue dtoToEntity(VenueDTO dto) {
        if (dto == null) {
            return null;
        }
        Venue venue = new Venue();
        venue.setId(dto.getId());
        venue.setName(dto.getName());
        venue.setLocation(dto.getLocation());
        venue.setCity(dto.getCity());
        venue.setDescription(dto.getDescription());
        venue.setImage(dto.getImage());
        venue.setImages(dto.getImages());
        venue.setPrice(dto.getPrice());
        venue.setCapacity(dto.getCapacity());
        venue.setAmenities(dto.getAmenities());
        venue.setRating(dto.getRating());
        venue.setReviewCount(dto.getReviewCount());
        venue.setFeatured(dto.isFeatured());

        // Map availability if provided
        if (dto.getAvailability() != null) {
            List<Availability> availabilityList = new ArrayList<Availability>();
            for (AvailabilityDTO availabilityDTO : dto.getAvailability()) {
                Availability availability = new Availability();
                availability.setId(availabilityDTO.getId());
                availability.setDate(availabilityDTO.getDate());
                // Set the parent venue reference
                availability.setVenue(venue);

                // Map slots for each availability
                if (availabilityDTO.getSlots() != null) {
                    List<Slot> slotList = new ArrayList<Slot>();
                    for (SlotDTO slotDTO : availabilityDTO.getSlots()) {
                        Slot slot = new Slot();
                        slot.setId(slotDTO.getId());
                        slot.setTime(slotDTO.getTime());
                        slot.setAvailable(slotDTO.isAvailable());
                        // Set the parent availability reference
                        slot.setAvailability(availability);
                        slotList.add(slot);
                    }
                    availability.setSlots(slotList);
                }
                availabilityList.add(availability);
            }
            venue.setAvailability(availabilityList);
        }
        return venue;
    }
}
