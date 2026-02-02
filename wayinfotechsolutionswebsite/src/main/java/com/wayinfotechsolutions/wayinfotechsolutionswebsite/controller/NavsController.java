package com.wayinfotechsolutions.wayinfotechsolutionswebsite.controller;

import com.wayinfotechsolutions.wayinfotechsolutionswebsite.dto.NavsDTO;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.Navs;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.Solutions;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection.NavsProjection;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository.NavsRepository;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository.SolutionsRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/navs")
public class NavsController {
    private final NavsRepository navsRepository;
    private final SolutionsRepository solutionsRepository;

    public NavsController(NavsRepository navsRepository, SolutionsRepository solutionsRepository) {
        this.navsRepository = navsRepository;
        this.solutionsRepository = solutionsRepository;
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<?> createNav(@Valid @RequestBody NavsDTO navsDTO) {

        Solutions solution = solutionsRepository.findById(navsDTO.getSolutionId())
                .orElseThrow(() -> new RuntimeException("Solution not found"));

        // Validation: Check duplicates within the same solution
        if (navsRepository.existsByNavNameAndSolutionIdAndDeletedAtIsNull(navsDTO.getNavName(), solution)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nav name already exists for this solution");
        }
        if (navsRepository.existsByNavSlugAndSolutionIdAndDeletedAtIsNull(navsDTO.getNavSlug(), solution)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nav slug already exists for this solution");
        }
        if (navsRepository.existsByNavOrderAndSolutionIdAndDeletedAtIsNull(navsDTO.getNavOrder(), solution)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nav order already exists for this solution");
        }

        Navs navs = new Navs();
        navs.setNavSlug(navsDTO.getNavSlug());
        navs.setNavName(navsDTO.getNavName());
        navs.setNavOrder(navsDTO.getNavOrder());
        navs.setSolutionId(solution);
        navs.setCreatedBy(1L); // replace with logged-in user id

        navsRepository.save(navs);
        return new ResponseEntity<>("Nav created successfully", HttpStatus.CREATED);
    }

    // ================= READ ALL =================
//    @GetMapping
//    public ResponseEntity<List<NavsProjection>> getAllNavs() {
//        return ResponseEntity.ok(
//                navsRepository.findAllByDeletedAtIsNullOrderByNavOrderAsc()
//        );
//    }
    @GetMapping
    public ResponseEntity<?>getAllNavs() {
        try {
            List<Navs> list = navsRepository.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error Retrieving Solution: " + e.getMessage());
        }
    }

    // ================= READ BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<Navs> getNavById(@PathVariable Long id) {

        Navs navs = navsRepository.findById(id)
                .filter(n -> n.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("Nav not found"));

        return ResponseEntity.ok(navs);
    }
    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNav(
            @PathVariable Long id,
            @Valid @RequestBody NavsDTO navDTO) {

        Navs navs = navsRepository.findById(id)
                .filter(n -> n.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("Nav not found"));

        Solutions solution = solutionsRepository.findById(navDTO.getSolutionId())
                .orElseThrow(() -> new RuntimeException("Solution not found"));

        // Validation: Check duplicates within the same solution
        if (navsRepository.existsByNavNameAndSolutionIdAndDeletedAtIsNull(navDTO.getNavName(), solution)
                && !navDTO.getNavName().equals(navs.getNavName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nav name already exists for this solution");
        }
        if (navsRepository.existsByNavSlugAndSolutionIdAndDeletedAtIsNull(navDTO.getNavSlug(), solution)
                && !navDTO.getNavSlug().equals(navs.getNavSlug())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nav slug already exists for this solution");
        }
        if (navsRepository.existsByNavOrderAndSolutionIdAndDeletedAtIsNull(navDTO.getNavOrder(), solution)
                && !navDTO.getNavOrder().equals(navs.getNavOrder())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nav order already exists for this solution");
        }


        navs.setNavSlug(navDTO.getNavSlug());
        navs.setNavName(navDTO.getNavName());
        navs.setNavOrder(navDTO.getNavOrder());
        navs.setSolutionId(solution);
        navs.setUpdatedBy(1L);
        navs.setUpdatedAt(LocalDateTime.now());

        navsRepository.save(navs);
        return ResponseEntity.ok("Nav updated successfully");
    }

    // ================= SOFT DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNav(@PathVariable Long id) {

        Navs navs = navsRepository.findById(id)
                .filter(n -> n.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("Nav not found"));

        navs.setDeletedAt(LocalDateTime.now());
        navs.setDeletedBy(1L);

        navsRepository.save(navs);
        return ResponseEntity.ok("Nav deleted successfully");
    }
}
