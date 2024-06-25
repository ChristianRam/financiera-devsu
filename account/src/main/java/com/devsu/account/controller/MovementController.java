package com.devsu.account.controller;

import com.devsu.account.model.dto.MovementDto;
import com.devsu.account.service.MovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for movements request
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movement")
public class MovementController {

    private final MovementService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMovement(@Valid @RequestBody MovementDto movementDto) {
        service.saveMovement(movementDto);
    }

    @PostMapping("/save-list")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMovementsList(@Valid @RequestBody List<MovementDto> movementDtos) {
        service.saveMovementsList(movementDtos);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovement(@PathVariable Long id, @Valid @RequestBody MovementDto movementDto) {
        service.updateMovement(id, movementDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovement(@PathVariable Long id) {
        service.deleteMovement(id);
    }

    @GetMapping
    public ResponseEntity<List<MovementDto>> findAllMovements() {
        return ResponseEntity.ok(service.findAllMovements());
    }
}
