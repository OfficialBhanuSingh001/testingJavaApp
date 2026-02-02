package com.predictorInfotech.sarkariUpdates.controller;

import com.predictorInfotech.sarkariUpdates.dto.MenuItemDTO;
import com.predictorInfotech.sarkariUpdates.model.MenuItem;
import com.predictorInfotech.sarkariUpdates.repository.MenuItemRepository;
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
@RequestMapping("api/menuItem")
public class MenuItemController {

    private final MenuItemRepository menuItemRepository;

    public MenuItemController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<?> getAllMenu() {
        try {
            List<MenuItem> menuItems = menuItemRepository.findAll();
            return ResponseEntity.ok(menuItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving Menu-items: " + e.getMessage());
        }
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuById(@PathVariable Long id) {
        try {
            Optional<MenuItem> menuItem = menuItemRepository.findById(id);
            if (menuItem.isPresent()) {
                return ResponseEntity.ok(menuItem.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Menu-item with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving Menu-item: " + e.getMessage());
        }
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Object> addMenuItem(@Valid @RequestBody MenuItemDTO menuItemDTO, BindingResult bindingResult) {
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
            MenuItem menuItem = new MenuItem();
            menuItem.setMenuName(menuItemDTO.getMenuName());
            menuItem.setMenuSlug(menuItemDTO.getMenuSlug());
            menuItem.setMenuIndexing(menuItemDTO.getMenuIndexing());
            menuItem.setIsActive(menuItemDTO.getIsActive());

            menuItemRepository.save(menuItem);
            return ResponseEntity.ok("Menu-item added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving Menu-item: " + e.getMessage());
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMenuItem(@PathVariable Long id, @Valid @RequestBody MenuItemDTO menuItemDTO, BindingResult bindingResult) {
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
            Optional<MenuItem> existingMenuItem = menuItemRepository.findById(id);
            if (existingMenuItem.isPresent()) {
                MenuItem menuItem = existingMenuItem.get();
                menuItem.setMenuName(menuItemDTO.getMenuName());
                menuItem.setMenuSlug(menuItemDTO.getMenuSlug());
                menuItem.setMenuIndexing(menuItemDTO.getMenuIndexing());
                menuItem.setIsActive(menuItemDTO.getIsActive());

                menuItemRepository.save(menuItem);
                return ResponseEntity.ok("Menu-item updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Menu-item with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Menu-item: " + e.getMessage());
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
        try {
            Optional<MenuItem> existingMenuItem = menuItemRepository.findById(id);
            if (existingMenuItem.isPresent()) {
                menuItemRepository.deleteById(id);
                return ResponseEntity.ok("Menu-item deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Menu-item with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting Menu-item: " + e.getMessage());
        }
    }
}
