package com.pj2z.pj2zbe.community.service;

import com.pj2z.pj2zbe.community.dto.PostCreateRequest;
import com.pj2z.pj2zbe.community.dto.PostCreateResponse;
import com.pj2z.pj2zbe.community.entity.Post;
import com.pj2z.pj2zbe.community.entity.Vote;
import com.pj2z.pj2zbe.community.repository.PostRepository;
import com.pj2z.pj2zbe.community.repository.VoteRepository;
import com.pj2z.pj2zbe.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.pj2z.pj2zbe.community.entity.Post.createPost;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final VoteRepository voteRepository;

    public PostCreateResponse create(User user, PostCreateRequest request) {
        Post post = createPost(request.title(), request.content(), user.getId());
        postRepository.save(post);

        Vote vote = Vote.createVote(
                post.getId(),
                request.voteForm().voteA(),
                request.voteForm().voteB(),
                request.voteForm().voteDeadline());
        voteRepository.save(vote);

        return new PostCreateResponse(post.getId());
    }
}
