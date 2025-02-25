package com.pj2z.pj2zbe.auth.repository;

import com.pj2z.pj2zbe.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
