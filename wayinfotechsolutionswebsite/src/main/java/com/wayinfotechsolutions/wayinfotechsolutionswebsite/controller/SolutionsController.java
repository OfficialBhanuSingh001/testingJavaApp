package com.wayinfotechsolutions.wayinfotechsolutionswebsite.controller;

import com.wayinfotechsolutions.wayinfotechsolutionswebsite.dto.SolutionsDTO;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.Solutions;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection.SolutionProjection;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository.SolutionsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/solutions")
public class SolutionsController {

    private final SolutionsRepository solutionsRepository;

    public SolutionsController(SolutionsRepository solutionsRepository) {
        this.solutionsRepository = solutionsRepository;
    }


    @GetMapping
    public ResponseEntity<?>getSolutionList() {
        try {
            List<Solutions> list = solutionsRepository.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error Retrieving Solution: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addSolution(@Valid @RequestBody SolutionsDTO solutionsDTO){

        try{
                Solutions solutions = new Solutions();
                solutions.setSolutionName(solutionsDTO.getSolutionName());
                solutions.setSolutionSlug(solutionsDTO.getSolutionSlug());
                solutions.setSolutionOrder(solutionsDTO.getSolutionOrder());
                solutionsRepository.save(solutions);

                return ResponseEntity.ok("Solution add successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error Add Solutions : " + e.getMessage());
        }
    }
    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSolution(
            @PathVariable Long id,
            @Valid @RequestBody SolutionsDTO solutionsDTO) {

        return solutionsRepository.findById(id).map(existing -> {
            existing.setSolutionName(solutionsDTO.getSolutionName());
            existing.setSolutionSlug(solutionsDTO.getSolutionSlug());
            existing.setSolutionOrder(solutionsDTO.getSolutionOrder());

            solutionsRepository.save(existing);
            return ResponseEntity.ok("Solution updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Solution not found with id: " + id));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSolution(@PathVariable Long id) {

        if (!solutionsRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Solution not found with id: " + id);
        }

        solutionsRepository.deleteById(id);
        return ResponseEntity.ok("Solution deleted successfully");
    }


    // ✅ GET BY ID
    @Transactional
    @GetMapping("/helpbook/{slug}")
    public ResponseEntity<?> getSolutionsData(@PathVariable String slug) {
        try {
            List<SolutionProjection> list = solutionsRepository.getSolutionFullDataBySlug(slug);

            if (list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Solution not found for slug: " + slug);
            }

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching solution data: " + e.getMessage());
        }
    }
}
