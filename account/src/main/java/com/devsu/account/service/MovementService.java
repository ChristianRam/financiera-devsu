package com.devsu.account.service;

import com.devsu.account.model.dto.MovementDto;

import java.util.List;
import java.util.Optional;

public interface MovementService {

    /**
     * Save a new movement
     *
     * @param movementDto movement to save
     */
    void saveMovement(MovementDto movementDto);

    /**
     * Save a movement list
     *
     * @param movementDtos movements to save
     */
    void saveMovementsList(List<MovementDto> movementDtos);

    /**
     * Update a movement by id
     *
     * @param id movement identifier
     * @param movementDto movement to update
     */
    void updateMovement(Long id, MovementDto movementDto);

    /**
     * Delete a movement
     * @param id movement identifier
     */
    void deleteMovement(Long id);

    /**
     * Retrieves a movement by id if exists, otherwise return an empty optional
     *
     * @param id movement identifier
     * @return movement optional
     */
    Optional<MovementDto> findMovementById(Long id);

    /**
     * Return all movements
     *
     * @return movements list
     */
    List<MovementDto> findAllMovements();
}
