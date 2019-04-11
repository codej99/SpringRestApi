package com.rest.api.repo;

import com.rest.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<User, Integer> {
}
