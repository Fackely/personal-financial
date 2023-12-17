package com.application.personal_financial.modules.movements.api;

import com.application.personal_financial.crosscutting.domain.Movement;
import com.application.personal_financial.crosscutting.domain.User;
import com.application.personal_financial.crosscutting.exceptions.ResourceNotFoundException;
import com.application.personal_financial.modules.movements.usecase.ProcessMovements;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/movements", produces = {
        MediaType.APPLICATION_JSON_VALUE
})
public class MovementsWebApi {

    private final ProcessMovements processMovements;

    public MovementsWebApi(ProcessMovements processMovements) {
        this.processMovements = processMovements;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movement> get(@NotNull @PathVariable("id") Integer movementId){
        Optional<Movement> movement = this.processMovements.getById(movementId);
        if (movement.isEmpty()){
            throw new ResourceNotFoundException("Movement not found with id: " + movementId);
        }
        return new ResponseEntity<>(movement.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movement> create(@Valid @RequestBody Movement movement){
        return new ResponseEntity<>(this.processMovements.create(movement), HttpStatus.OK);
    }
}
