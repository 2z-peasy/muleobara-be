package com.pj2z.pj2zbe.balanceGame.controller;

import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameRequest;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameVoteRequest;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGame;
import com.pj2z.pj2zbe.balanceGame.service.BalanceGameService;
import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance-game")
public class BalanceGameController {

    private final BalanceGameService balanceGameService;

    public BalanceGameController(BalanceGameService balanceGameService) {
        this.balanceGameService = balanceGameService;
    }
    @GetMapping("/today")
    public ResponseEntity<Object> getTodaybalanceGame() {
        BalanceGame todayGame = balanceGameService.getTodayGame();
        return null;
    }

    @PostMapping
    public ResponseEntity<BalanceGame> createGame(@RequestBody BalanceGameRequest request) {
        return ResponseEntity.ok(balanceGameService.createBalanceGame(request));
    }

    @PutMapping
    public ResponseEntity<BalanceGame> updateGame(@RequestBody BalanceGameRequest request) {
        return ResponseEntity.ok(balanceGameService.updateBalanceGame(request));
    }

    @PostMapping("/vote")
    public ResponseEntity<BalanceGame> voteGame(@UserCheck User user, @RequestBody BalanceGameVoteRequest request) {
        balanceGameService.vote(user.getId(),request.getGameDate(), request.getChoice());
        return ResponseEntity.ok().build();
    }
}
