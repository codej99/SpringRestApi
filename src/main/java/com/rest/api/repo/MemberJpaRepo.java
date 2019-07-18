package com.rest.api.repo;

import com.rest.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepo extends JpaRepository<Member, Long> {
}
