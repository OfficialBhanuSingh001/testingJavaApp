package com.wayinfotechsolutions.wayinfotechsolutionswebsite.controller;

import com.wayinfotechsolutions.wayinfotechsolutionswebsite.dto.SubNavContentsDTO;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.SubNavContents;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.SubNavs;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository.SubNavContentsRepository;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository.SubNavRepository;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subnavcontents")

public class SubNavContentsController {

    private final SubNavContentsRepository subNavContentsRepository;
    private final SubNavRepository subNavRepository;

    public SubNavContentsController(SubNavContentsRepository contentRepository, SubNavContentsRepository subNavContentsRepository, SubNavRepository subNavRepository) {
        this.subNavContentsRepository = subNavContentsRepository;
        this.subNavRepository = subNavRepository;
    }

    /* ===========================
       GET ALL (Projection)
       =========================== */
    @GetMapping
    public ResponseEntity<?> getAllContents() {
        try {
            List<SubNavContents> list = subNavContentsRepository.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error Retrieving Solution: " + e.getMessage());
        }
    }

    /* ===========================
       GET BY SUB NAV ID
       =========================== */
//    @GetMapping("/subnav/{subNavId}")
//    public List<SubNavContentsProjection> getBySubNav(
//            @PathVariable Long subNavId) {
//
//        return contentRepository.findBySubNav_SubNavId(subNavId);
//    }

    /* ===========================
       CREATE
       =========================== */
    @PostMapping
    public ResponseEntity<?> createContent(
            @Valid @RequestBody SubNavContentsDTO dto) {

        SubNavs subNav = subNavRepository.findById(dto.getSubNavId())
                .orElseThrow(() ->
                        new RuntimeException("Sub Nav not found"));
        // ===== Validation: prevent duplicates within the same SubNav =====
        if (subNavContentsRepository.existsBySubNavId(subNav)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("This Sub Nav Content Already Exists");
        }

        SubNavContents content = new SubNavContents();


        content.setSubNavId(subNav);
        content.setHeading(dto.getHeading());
        content.setTitle(dto.getTitle());
        content.setDescription(dto.getDescription());

        subNavContentsRepository.save(content);

        return ResponseEntity.ok("Sub Nav content created successfully");
    }

    /* ===========================
       UPDATE
       =========================== */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContent(
            @PathVariable Long id,
            @Valid @RequestBody SubNavContentsDTO dto) {

        SubNavContents content = subNavContentsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Content not found"));

        SubNavs subNav = subNavRepository.findById(dto.getSubNavId())
                .orElseThrow(() ->
                        new RuntimeException("Sub Nav not found"));

        // ===== Validation: exclude current content =====
        if (!content.getSubNavId().getSubNavId().equals(dto.getSubNavId())
                && subNavContentsRepository.existsBySubNavId(subNav)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Content already exists for this SubNav");
        }
        content.setSubNavId(subNav);
        content.setHeading(dto.getHeading());
        content.setTitle(dto.getTitle());
        content.setDescription(dto.getDescription());

        subNavContentsRepository.save(content);

        return ResponseEntity.ok("Sub Nav content updated successfully");
    }

    /* ===========================
       DELETE
       =========================== */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable Long id) {

        if (!subNavContentsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        subNavContentsRepository.deleteById(id);
        return ResponseEntity.ok("Sub Nav content deleted successfully");
    }
}
