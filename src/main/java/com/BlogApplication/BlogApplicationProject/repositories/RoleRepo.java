package com.BlogApplication.BlogApplicationProject.repositories;

import com.BlogApplication.BlogApplicationProject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
