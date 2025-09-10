package com.tourism.safety.tourist_safety_system.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "locations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LocationData {

    @Id
    private String locationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourist_id")
    private TouristProfile tourist;

    @Embedded
    private Coordinates coordinates;

    private LocalDateTime timestamp;
    private double accuracy;
    private String locationName;

    @Enumerated(EnumType.STRING)
    private ZoneType zoneType;

    @Enumerated(EnumType.STRING)
    private ConnectionType connectionType;

    // --- Embeddable Coordinates ---
    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Coordinates {
        private double latitude;
        private double longitude;
        private double altitude;
    }

    public enum ZoneType { SAFE, RESTRICTED, HIGH_RISK }
    public enum ConnectionType { GPS, NETWORK, IOT }
}
