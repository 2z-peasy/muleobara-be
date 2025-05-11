package com.pj2z.pj2zbe.community.repository;

import com.pj2z.pj2zbe.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE DATE(p.createdAt) = CURRENT_DATE ORDER BY p.likeCount DESC")
    List<Post> findTopPostsByToday();
}
