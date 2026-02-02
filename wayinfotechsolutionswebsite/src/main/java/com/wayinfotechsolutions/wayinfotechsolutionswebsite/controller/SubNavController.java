package com.wayinfotechsolutions.wayinfotechsolutionswebsite.controller;

import com.wayinfotechsolutions.wayinfotechsolutionswebsite.dto.SubNavsDTO;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.Navs;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.SubNavs;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository.NavsRepository;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository.SubNavRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/subnavs")
public class SubNavController {

    private final SubNavRepository subNavRepository;
    private final NavsRepository navsRepository;

    public SubNavController(SubNavRepository subNavRepository,
                            NavsRepository navsRepository) {
        this.subNavRepository = subNavRepository;
        this.navsRepository = navsRepository;
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<?> createSubNav(@Valid @RequestBody SubNavsDTO dto) {

        Navs navs = navsRepository.findById(dto.getNavId())
                .orElseThrow(() -> new RuntimeException("Nav not found"));
        // ===== Validation: prevent duplicates within the same Nav =====
        if (subNavRepository.existsBySubNavNameAndNavIdAndDeletedAtIsNull(dto.getSubNavName(), navs)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("SubNav name already exists for this Nav");
        }
        if (subNavRepository.existsBySubNavSlugAndNavIdAndDeletedAtIsNull(dto.getSubNavSlug(), navs)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("SubNav slug already exists for this Nav");
        }
        if (subNavRepository.existsBySubNavOrderAndNavIdAndDeletedAtIsNull(dto.getSubNavOrder(), navs)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("SubNav order already exists for this Nav");
        }
        SubNavs subNav = new SubNavs();
        subNav.setSubNavSlug(dto.getSubNavSlug());
        subNav.setSubNavName(dto.getSubNavName());
        subNav.setSubNavOrder(dto.getSubNavOrder());
        subNav.setNavId(navs);
        subNav.setCreatedBy(1L); // replace with logged-in user id

        subNavRepository.save(subNav);

        return new ResponseEntity<>("SubNav created successfully", HttpStatus.CREATED);
    }

    // ================= READ ALL =================
    @GetMapping
    public ResponseEntity<?> getAllSubNavs() {
        try {
            List<SubNavs> list = subNavRepository.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving SubNavs: " + e.getMessage());
        }
    }

    // ================= READ BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<SubNavs> getSubNavById(@PathVariable Long id) {

        SubNavs subNav = subNavRepository.findById(id)
                .filter(s -> s.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("SubNav not found"));

        return ResponseEntity.ok(subNav);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubNav(
            @PathVariable Long id,
            @Valid @RequestBody SubNavsDTO dto) {

        SubNavs subNav = subNavRepository.findById(id)
                .filter(s -> s.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("SubNav not found"));

        Navs navs = navsRepository.findById(dto.getNavId())
                .orElseThrow(() -> new RuntimeException("Nav not found"));

        // ===== Validation: exclude current SubNav =====
        if (subNavRepository.existsBySubNavNameAndNavIdAndDeletedAtIsNull(dto.getSubNavName(), navs)
                && !dto.getSubNavName().equals(subNav.getSubNavName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("SubNav name already exists for this Nav");
        }
        if (subNavRepository.existsBySubNavSlugAndNavIdAndDeletedAtIsNull(dto.getSubNavSlug(), navs)
                && !dto.getSubNavSlug().equals(subNav.getSubNavSlug())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("SubNav slug already exists for this Nav");
        }
        if (subNavRepository.existsBySubNavOrderAndNavIdAndDeletedAtIsNull(dto.getSubNavOrder(), navs)
                && !dto.getSubNavOrder().equals(subNav.getSubNavOrder())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("SubNav order already exists for this Nav");
        }

        subNav.setSubNavSlug(dto.getSubNavSlug());
        subNav.setSubNavName(dto.getSubNavName());
        subNav.setSubNavOrder(dto.getSubNavOrder());
        subNav.setNavId(navs);
        subNav.setUpdatedBy(1L);
        subNav.setUpdatedAt(LocalDateTime.now());

        subNavRepository.save(subNav);

        return ResponseEntity.ok("SubNav updated successfully");
    }

    // ================= SOFT DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubNav(@PathVariable Long id) {

        SubNavs subNav = subNavRepository.findById(id)
                .filter(s -> s.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("SubNav not found"));

        subNav.setDeletedAt(LocalDateTime.now());
        subNav.setDeletedBy(1L);

        subNavRepository.save(subNav);

        return ResponseEntity.ok("SubNav deleted successfully");
    }
}
