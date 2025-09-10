package com.tourism.safety.tourist_safety_system.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/digital-id")
public class DigitalIDManagementController {

    private final Map<String, Map<String, Object>> db = new HashMap<>(); // mock DB

    // Generate Digital ID
    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody Map<String, Object> request) {
        String digitalId = UUID.randomUUID().toString();
        String blockchainHash = UUID.randomUUID().toString().replace("-", "");
        String qrCode = "QR_" + digitalId.substring(0, 6);

        Map<String, Object> record = new HashMap<>();
        record.put("touristInfo", request.get("touristInfo"));
        record.put("documents", request.get("documents"));
        record.put("biometrics", request.get("biometrics"));
        record.put("digitalId", digitalId);
        record.put("blockchainHash", blockchainHash);
        record.put("qrCode", qrCode);
        record.put("createdAt", LocalDateTime.now());

        db.put(digitalId, record);

        return ResponseEntity.ok(Map.of(
                "digitalId", digitalId,
                "blockchainHash", blockchainHash,
                "qrCode", qrCode
        ));
    }

    // Verify Digital ID
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody Map<String, String> request) {
        String digitalId = request.get("digitalId");
        Map<String, Object> record = db.get(digitalId);

        if (record == null) return ResponseEntity.badRequest().body(Map.of("valid", false));

        return ResponseEntity.ok(Map.of(
                "valid", true,
                "touristInfo", record.get("touristInfo"),
                "expiryDate", LocalDateTime.now().plusYears(1)
        ));
    }

    // Update Digital ID
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Map<String, Object> request) {
        String digitalId = (String) request.get("digitalId");
        Map<String, Object> record = db.get(digitalId);

        if (record == null) return ResponseEntity.badRequest().body(Map.of("success", false));

        Map<String, Object> updates = (Map<String, Object>) request.get("updates");
        record.putAll(updates);
        String newHash = UUID.randomUUID().toString().replace("-", "");
        record.put("blockchainHash", newHash);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "newHash", newHash,
                "updatedInfo", record
        ));
    }

    // Revoke Digital ID
    @DeleteMapping("/revoke")
    public ResponseEntity<?> revoke(@RequestBody Map<String, String> request) {
        String digitalId = request.get("digitalId");
        Map<String, Object> record = db.remove(digitalId);

        if (record == null) return ResponseEntity.badRequest().body(Map.of("success", false));

        return ResponseEntity.ok(Map.of(
                "success", true,
                "revokedAt", LocalDateTime.now()
        ));
    }

    // Get History
    @GetMapping("/history")
    public ResponseEntity<?> history(@RequestParam String digitalId) {
        Map<String, Object> record = db.get(digitalId);
        if (record == null) return ResponseEntity.badRequest().body(Map.of("transactions", List.of()));

        return ResponseEntity.ok(Map.of(
                "transactions", List.of("generated", "verified", "updated"),
                "modifications", List.of(record)
        ));
    }

    // QR Scan
    @PostMapping("/qr-scan")
    public ResponseEntity<?> qrScan(@RequestBody Map<String, String> request) {
        String qrData = request.get("qrData");

        Optional<Map.Entry<String, Map<String, Object>>> match = db.entrySet().stream()
                .filter(e -> e.getValue().get("qrCode").equals(qrData))
                .findFirst();

        if (match.isEmpty()) return ResponseEntity.badRequest().body(Map.of("validity", false));

        Map<String, Object> record = match.get().getValue();

        return ResponseEntity.ok(Map.of(
                "touristInfo", record.get("touristInfo"),
                "validity", true,
                "permissions", List.of("travel", "stay", "emergency")
        ));
    }
}
