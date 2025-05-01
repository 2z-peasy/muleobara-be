package com.pj2z.pj2zbe.community.service;

import com.pj2z.pj2zbe.community.dto.LikeResponse;
import com.pj2z.pj2zbe.community.entity.Likes;
import com.pj2z.pj2zbe.community.repository.LikeRepository;
import com.pj2z.pj2zbe.community.repository.PostRepository;
import com.pj2z.pj2zbe.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public LikeResponse likePost(User user, Long postId) {
        validatePostExists(postId);

        if (!likeRepository.existsByPostIdAndUserId(postId, user.getId())) {
            Likes likes = new Likes(postId, user.getId());
            likeRepository.save(likes);
        } else {
            likeRepository.deleteByPostIdAndUserId(postId, user.getId()); // 이미 공감을 눌렀다면 취소
        }

        Integer likeCount = likeRepository.countByPostId(postId);
        return new LikeResponse(likeCount);
    }

    private void validatePostExists(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("해당 게시글이 없습니다.");
        }
    }
}
