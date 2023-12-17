package com.application.personal_financial.modules.users.usecase;

import com.application.personal_financial.crosscutting.domain.Movement;
import com.application.personal_financial.crosscutting.domain.User;
import com.application.personal_financial.crosscutting.exceptions.ResourceNotFoundException;
import com.application.personal_financial.crosscutting.persistence.repository.MovementRepository;
import com.application.personal_financial.crosscutting.persistence.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessUsers {

    private final MovementRepository movementRepository;

    private final UsersRepository usersRepository;

    public ProcessUsers(MovementRepository movementRepository, UsersRepository usersRepository) {
        this.movementRepository = movementRepository;
        this.usersRepository = usersRepository;
    }

    public User create(User user){
        return usersRepository.save(user);
    }

    public Optional<User> getById(Integer id){
        Optional<User> user = usersRepository.findById(id);
        if (user.isEmpty()){
            return Optional.empty();
        }
        user.get().setMovements(this.getMovements(id));
        return user;
    }

    public List<Movement> getMovements(Integer userId){
        return movementRepository.findByUserId(userId);
    }

}
