package com.pj2z.pj2zbe.community.repository;

import com.pj2z.pj2zbe.community.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Integer countByPostId(Long postId);
    boolean existsByPostIdAndUserId(Long postId, Long id);
    void deleteByPostIdAndUserId(Long postId, Long id);
}
