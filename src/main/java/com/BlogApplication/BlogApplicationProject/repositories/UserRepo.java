package com.BlogApplication.BlogApplicationProject.repositories;

import com.BlogApplication.BlogApplicationProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
