package com.example.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.VenueDTO;

@Service
public class ExternalVenueService {

	@Value("${venue.api.url}")
	private String venueApiUrl;

	private final RestTemplate restTemplate = new RestTemplate();

	public VenueDTO getVenueById(Long venueId) {
		String url = venueApiUrl + "/venues/" + venueId;
		ResponseEntity<VenueDTO> response = restTemplate.getForEntity(url, VenueDTO.class);
		return response.getBody();
	}
}
