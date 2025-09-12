package com.tourism.safety.tourist_safety_system.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.tourism.safety.tourist_safety_system.Service.TouristService;
import com.tourism.safety.tourist_safety_system.Model.Tourist;
import com.tourism.safety.tourist_safety_system.Payload.TouristRequest;
import com.tourism.safety.tourist_safety_system.Payload.TouristResponse;
import com.tourism.safety.tourist_safety_system.Payload.VerifyResponse;

@RestController
@RequestMapping("/api/tourist")
@CrossOrigin
public class BlockchainFunctionsController {

    @Autowired
    private TouristService touristService;

    // POST - Register Tourist
    @PostMapping("/register")
    public ResponseEntity<TouristResponse> registerTourist(@RequestBody TouristRequest request) {
        try {
            TouristResponse response = touristService.registerTourist(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new TouristResponse(null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET - Fetch Tourist Info
    @GetMapping("/{touristId}")
    public ResponseEntity<Tourist> getTouristInfo(@PathVariable String touristId) {
        try {
            Tourist tourist = touristService.getTouristInfo(touristId);
            return ResponseEntity.ok(tourist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // GET - Verify Tourist
    @GetMapping("/verify/{touristId}")
    public ResponseEntity<VerifyResponse> verifyTourist(@PathVariable String touristId) {
        try {
            VerifyResponse response = touristService.verifyTourist(touristId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new VerifyResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
