package com.application.personal_financial.crosscutting.persistence.repository;

import com.application.personal_financial.crosscutting.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}
