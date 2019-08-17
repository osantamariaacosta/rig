package com.cenfotec.rigs.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cenfotec.rigs.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
