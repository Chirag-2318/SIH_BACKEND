package com.tourism.safety.tourist_safety_system.Model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "alerts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Alert_Incident {

    @Id
    private String alertId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourist_id")
    private TouristProfile tourist;

    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationData location;

    private String description;

    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    @ElementCollection
    private List<Responder> responders;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

    // --- Enums ---
    public enum AlertType { PANIC, GEOFENCE, ANOMALY, MISSING, HEALTH }
    public enum Severity { LOW, MEDIUM, HIGH, CRITICAL }
    public enum AlertStatus { ACTIVE, ACKNOWLEDGED, RESOLVED, FALSE_ALARM }

    // --- Embeddable Responder ---
    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Responder {
        private String responderId;
        private String name;
        private String role;
        private String contact;
    }
}
