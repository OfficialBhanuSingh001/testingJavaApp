package com.sarkariUpdate.OliveMedia.Controller;

import com.sarkariUpdate.OliveMedia.Entity.Navs;
import com.sarkariUpdate.OliveMedia.Projections.NavsProjection;
import com.sarkariUpdate.OliveMedia.Repository.NavRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/navs")
public class NavController {
    private final NavRepository navRepository;

    public NavController( NavRepository navRepository) {
        this.navRepository = navRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllNavs() {
        try {
            List<Navs> list = navRepository.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error Retrieving Menu List: " + e.getMessage());
        }
    }
}
