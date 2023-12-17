package com.application.personal_financial.crosscutting.persistence.repository;

import com.application.personal_financial.crosscutting.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer> {

    List<Movement> findByUserId (Integer userId);
}
