package com.rest.api.repo;

import com.rest.api.entity.Employ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployJpaRepo extends JpaRepository<Employ, Long> {
}
