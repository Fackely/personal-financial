package com.application.personal_financial.modules.users.api;

import com.application.personal_financial.crosscutting.domain.Movement;
import com.application.personal_financial.crosscutting.domain.User;
import com.application.personal_financial.crosscutting.exceptions.ResourceNotFoundException;
import com.application.personal_financial.modules.users.usecase.ProcessUsers;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users", produces = {
        MediaType.APPLICATION_JSON_VALUE
})
public class UsersWebApi {

    private final ProcessUsers processUsers;

    public UsersWebApi(ProcessUsers processUsers) {
        this.processUsers = processUsers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@NotNull @PathVariable("id") Integer userId){
        Optional<User> user = this.processUsers.getById(userId);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}/movements")
    public ResponseEntity<List<Movement>> getMovements(@NotNull @PathVariable("id") Integer userId){
        Optional<User> user = this.processUsers.getById(userId);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return new ResponseEntity<>(this.processUsers.getMovements(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        return new ResponseEntity<>(this.processUsers.create(user), HttpStatus.OK);
    }
}
