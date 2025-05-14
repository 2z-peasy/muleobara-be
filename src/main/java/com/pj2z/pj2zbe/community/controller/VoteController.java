package com.pj2z.pj2zbe.community.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.community.dto.VoteRequest;
import com.pj2z.pj2zbe.community.dto.VoteResponse;
import com.pj2z.pj2zbe.community.service.VoteService;
import com.pj2z.pj2zbe.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/vote")
@Tag(name = "커뮤니티 하단_투표", description = "투표 생성, 수정, 조회 API")
public class VoteController {

    private final VoteService voteService;

    @PutMapping("/{voteId}")
    @Operation(summary = "투표 수정", description = "투표를 수정합니다.")
    public ResponseEntity<VoteResponse> updateVote(@UserCheck User user,
                                                   @PathVariable Long voteId,
                                                   @RequestBody @Valid VoteRequest voteRequest) {
        VoteResponse response = voteService.updateVoteCount(user, voteId, voteRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{voteId}")
    @Operation(summary = "투표 조회", description = "투표를 조회합니다.")
    public ResponseEntity<VoteResponse> retrieveVote(@PathVariable Long voteId) {
        VoteResponse response = voteService.retrieve(voteId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
