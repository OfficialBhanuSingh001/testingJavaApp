package com.sarkariUpdate.OliveMedia.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class WelcomeController {
    @GetMapping
    public ResponseEntity<?> getAllMenu() {
        try {
            return ResponseEntity.ok("Welcome Java");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving Welcome: " + e.getMessage());
        }
    }
}
