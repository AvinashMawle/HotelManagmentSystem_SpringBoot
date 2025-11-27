package com.airBnb.airBnbProject.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String city;

    // Store arrays as JSON because MySQL supports JSON (recommended)
    @Column(columnDefinition = "JSON")
    private String[] photos;

    @Column(columnDefinition = "JSON")
    private String[] amenities;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    private HotelContactInfo contactInfo;

    @Column(nullable = false)
    private Boolean active;

    // --- Getters & Setters (Required by JPA) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String[] getPhotos() { return photos; }
    public void setPhotos(String[] photos) { this.photos = photos; }

    public String[] getAmenities() { return amenities; }
    public void setAmenities(String[] amenities) { this.amenities = amenities; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public HotelContactInfo getContactInfo() { return contactInfo; }
    public void setContactInfo(HotelContactInfo contactInfo) { this.contactInfo = contactInfo; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
