package com.signalsense.signal_sense_backend.controller;

import com.signalsense.signal_sense_backend.model.User;
import com.signalsense.signal_sense_backend.model.Organization;
import com.signalsense.signal_sense_backend.repository.UserRepository;
import com.signalsense.signal_sense_backend.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // For development only
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @PostMapping("/register/user")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setRole("VOLUNTEER");
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/register/organization")
    public ResponseEntity<Organization> registerOrganization(@RequestBody Organization organization) {
        organization.setVerificationStatus("PENDING");
        Organization savedOrg = organizationRepository.save(organization);
        return ResponseEntity.ok(savedOrg);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Try user first
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("token", "fake-jwt-token-user-" + userOpt.get().getId(), "type", "USER", "id", userOpt.get().getId()));
        }

        // Try organization
        Optional<Organization> orgOpt = organizationRepository.findByEmail(email);
        if (orgOpt.isPresent() && orgOpt.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("token", "fake-jwt-token-org-" + orgOpt.get().getId(), "type", "ORGANIZATION", "id", orgOpt.get().getId()));
        }

        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
    }
}
