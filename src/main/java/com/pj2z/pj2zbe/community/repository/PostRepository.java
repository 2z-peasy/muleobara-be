package com.pj2z.pj2zbe.community.repository;

import com.pj2z.pj2zbe.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop10ByOrderByLikeCountDesc();
}
