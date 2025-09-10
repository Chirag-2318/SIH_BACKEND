package com.tourism.safety.tourist_safety_system.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tourist_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TouristProfile {

    @Id
    private String touristId;   // blockchain hash as primary key

    private String digitalIdToken;

    @Embedded
    private PersonalInfo personalInfo;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private TripDetails tripDetails;

    private int safetyScore;
    private String riskProfile;

    private LocalDateTime createdAt;
    private LocalDateTime validUntil;

    // Relations
    @OneToMany(mappedBy = "tourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocationData> locations;

    @OneToMany(mappedBy = "tourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alert_Incident> alerts;

    // ---- Nested Embeddables ----
    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PersonalInfo {
        private String name;
        private String nationality;
        private String passportNumber;
        private String aadhaarNumber;
        private LocalDate dateOfBirth;
        private String gender;
        private String photo;
    }

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ContactInfo {
        private String primaryPhone;
        private String email;
        @ElementCollection
        private List<EmergencyContact> emergencyContacts;
    }

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class EmergencyContact {
        private String name;
        private String relation;
        private String phone;
    }

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class TripDetails {
        private LocalDateTime arrivalDate;
        private LocalDateTime departureDate;
        private String entryPoint;
        @ElementCollection
        private List<String> plannedItinerary;
        @ElementCollection
        private List<String> accommodations;
        private String tourOperator;
    }
}
