package com.pj2z.pj2zbe.mbti.repository;

import com.pj2z.pj2zbe.auth.entity.User;
import com.pj2z.pj2zbe.mbti.entity.Mbti;
import com.pj2z.pj2zbe.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MbtiRepository  extends JpaRepository<Mbti, Long> {
       Page<Mbti> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);


    Optional<Mbti> findTopByUserOrderByCreatedAtDesc(User user);

}
