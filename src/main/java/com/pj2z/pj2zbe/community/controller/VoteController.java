package com.pj2z.pj2zbe.community.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.community.dto.VoteRequest;
import com.pj2z.pj2zbe.community.dto.VoteResponse;
import com.pj2z.pj2zbe.community.service.VoteService;
import com.pj2z.pj2zbe.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/vote")
public class VoteController {

    private final VoteService voteService;

    @PutMapping("/{voteId}")
    public ResponseEntity<VoteResponse> updateVote(@UserCheck User user,
                                                   @PathVariable Long voteId,
                                                   @RequestBody @Valid VoteRequest voteRequest) {
        VoteResponse response = voteService.updateVoteCount(user, voteId, voteRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
