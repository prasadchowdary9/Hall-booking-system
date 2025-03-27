package com.example.demo.mapper;

import com.example.demo.dto.AvailabilityDTO;
import com.example.demo.model.Availability;
import com.example.demo.model.Venue;
import java.util.List;
import java.util.stream.Collectors;

public class AvailabilityMapper {

    public static Availability dtoToEntity(AvailabilityDTO dto, Venue venue) {
        Availability availability = new Availability();
        availability.setId(dto.getId());
        availability.setDate(dto.getDate());
        availability.setVenue(venue);
        if (dto.getSlots() != null) {
            availability.setSlots(dto.getSlots().stream()
                    .map(SlotMapper::dtoToEntity)
                    .collect(Collectors.toList()));
        }
        return availability;
    }
}
