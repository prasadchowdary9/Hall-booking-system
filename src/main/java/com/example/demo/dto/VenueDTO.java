package com.example.demo.dto;

import java.util.List;

public class VenueDTO {
    private String id;
    private String name;
    private String location;
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
    private String city;

    public VenueDTO() {}

	
	public VenueDTO(String id, String name, String location, String description, String image, List<String> images,
			double price, int capacity, List<String> amenities, double rating, int reviewCount,
			List<AvailabilityDTO> availability, boolean featured, String city) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.description = description;
		this.image = image;
		this.images = images;
		this.price = price;
		this.capacity = capacity;
		this.amenities = amenities;
		this.rating = rating;
		this.reviewCount = reviewCount;
		this.availability = availability;
		this.featured = featured;
		this.city = city;
	}


//	public VenueDTO(String id2, String name2, String location2, String description2, String image2,
//			List<String> images2, double price2, int capacity2, List<String> amenities2, double rating2,
//			int reviewCount2, List<AvailabilityDTO> availabilityDTOs, boolean featured2, String city2) {
//		// TODO Auto-generated constructor stub
//		
//	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public List<AvailabilityDTO> getAvailability() {
		return availability;
	}

	public void setAvailability(List<AvailabilityDTO> availability) {
		this.availability = availability;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

    
}
