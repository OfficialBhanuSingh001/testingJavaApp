package com.predictorInfotech.sarkariUpdates.controller;

import com.predictorInfotech.sarkariUpdates.dto.MenuDetailDTO;
import com.predictorInfotech.sarkariUpdates.model.MenuDetail;
import com.predictorInfotech.sarkariUpdates.model.MenuItem;
import com.predictorInfotech.sarkariUpdates.repository.MenuDetailsRepository;
import com.predictorInfotech.sarkariUpdates.repository.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/details")
public class MenuDetailController {

    private final MenuDetailsRepository menuDetailsRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuDetailController(MenuDetailsRepository menuDetailsRepository, MenuItemRepository menuItemRepository) {
        this.menuDetailsRepository = menuDetailsRepository;
        this.menuItemRepository = menuItemRepository;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<?> getAllMenuDetails() {
        try {
            List<MenuDetail> menuDetails = menuDetailsRepository.findAll();
            return ResponseEntity.ok(menuDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving menuDetails: " + e.getMessage());
        }
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuDetailById(@PathVariable Long id) {
        try {
            Optional<MenuDetail> menuDetail = menuDetailsRepository.findById(id);
            if (menuDetail.isPresent()) {
                return ResponseEntity.ok(menuDetail.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Menu-detail with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving menu-detail: " + e.getMessage());
        }
    }

    //GET BY Title
    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getMenuDetailBySlug(@PathVariable String slug) {
        try {
            Optional<MenuDetail> menuDetail = menuDetailsRepository.findBySlug(slug);
            if (menuDetail.isPresent()) {
                return ResponseEntity.ok(menuDetail.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Menu-detail with ID " + slug + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving menu-detail: " + e.getMessage());
        }
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Object> saveMenuDetail(@Valid @RequestBody MenuDetailDTO menuDetailDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("timestamp", LocalDateTime.now());
            response.put("errors", errors);
            response.put("message", "Validation failed");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            MenuItem menuItem = menuItemRepository.findById(menuDetailDTO.getMenuItemId())
                    .orElseThrow(() -> new EntityNotFoundException("Menu-item not found"));

            MenuDetail menuDetail = new MenuDetail();
            menuDetail.setMenuItem(menuItem);
            menuDetail.setPostedDate(menuDetailDTO.getPostedDate());
            menuDetail.setMetaTitle(menuDetailDTO.getMetaTitle());
            menuDetail.setMetaDescription(menuDetailDTO.getMetaDescription());
            menuDetail.setSlug(menuDetailDTO.getSlug());
            menuDetail.setTitle(menuDetailDTO.getTitle());
            menuDetail.setDescription(menuDetailDTO.getDescription());
            menuDetail.setApplyStartDate(menuDetailDTO.getApplyStartDate());
            menuDetail.setApplyLastDate(menuDetailDTO.getApplyLastDate());
            menuDetail.setNotificationDate(menuDetailDTO.getNotificationDate());
            menuDetail.setAdmitCardDate(menuDetailDTO.getAdmitCardDate());
            menuDetail.setResultDate(menuDetailDTO.getResultDate());
            menuDetail.setApplicableFee(menuDetailDTO.getApplicableFee());
            menuDetail.setAgeLimit(menuDetailDTO.getAgeLimit());
            menuDetail.setTotalPost(menuDetailDTO.getTotalPost());
            menuDetail.setVacancyDetails(menuDetailDTO.getVacancyDetails());
            menuDetail.setFaqQuestions(menuDetailDTO.getFaqQuestions());

            menuDetailsRepository.save(menuDetail);
            return ResponseEntity.ok("Menu-detail added successfully");

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding Menu-detail: " + e.getMessage());
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMenuDetail(@PathVariable Long id,
                                                   @Valid @RequestBody MenuDetailDTO menuDetailDTO,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("timestamp", LocalDateTime.now());
            response.put("errors", errors);
            response.put("message", "Validation failed");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            MenuDetail menuDetail = menuDetailsRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Menu-detail not found"));

            MenuItem menuItem = menuItemRepository.findById(menuDetailDTO.getMenuItemId())
                    .orElseThrow(() -> new EntityNotFoundException("Menu-item not found"));

            menuDetail.setMenuItem(menuItem);
            menuDetail.setPostedDate(menuDetailDTO.getPostedDate());
            menuDetail.setMetaTitle(menuDetailDTO.getMetaTitle());
            menuDetail.setMetaDescription(menuDetailDTO.getMetaDescription());
            menuDetail.setSlug(menuDetailDTO.getSlug());
            menuDetail.setTitle(menuDetailDTO.getTitle());
            menuDetail.setDescription(menuDetailDTO.getDescription());
            menuDetail.setApplyStartDate(menuDetailDTO.getApplyStartDate());
            menuDetail.setApplyLastDate(menuDetailDTO.getApplyLastDate());
            menuDetail.setNotificationDate(menuDetailDTO.getNotificationDate());
            menuDetail.setAdmitCardDate(menuDetailDTO.getAdmitCardDate());
            menuDetail.setResultDate(menuDetailDTO.getResultDate());
            menuDetail.setApplicableFee(menuDetailDTO.getApplicableFee());
            menuDetail.setAgeLimit(menuDetailDTO.getAgeLimit());
            menuDetail.setTotalPost(menuDetailDTO.getTotalPost());
            menuDetail.setVacancyDetails(menuDetailDTO.getVacancyDetails());
            menuDetail.setFaqQuestions(menuDetailDTO.getFaqQuestions());

            menuDetailsRepository.save(menuDetail);
            return ResponseEntity.ok("Menu-detail updated successfully");

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Menu-detail: " + e.getMessage());
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuDetail(@PathVariable Long id) {
        try {
            MenuDetail menuDetail = menuDetailsRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Menu-detail not found"));
            menuDetailsRepository.delete(menuDetail);
            return ResponseEntity.ok("Menu-detail deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting Menu-detail: " + e.getMessage());
        }
    }
}
