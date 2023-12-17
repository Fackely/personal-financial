package com.application.personal_financial.modules.movements.usecase;

import com.application.personal_financial.crosscutting.domain.Movement;
import com.application.personal_financial.crosscutting.persistence.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessMovements {

    private final MovementRepository movementRepository;

    public ProcessMovements(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public Movement create(Movement movement){
        return movementRepository.save(movement);
    }

    public Optional<Movement> getById(Integer id){
        return movementRepository.findById(id);
    }

    public List<Movement> getByUser(Integer userId){
        return movementRepository.findByUserId(userId);
    }

}
