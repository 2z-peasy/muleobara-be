package com.pj2z.pj2zbe.balanceGame.controller;

import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameRequest;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameVoteRequest;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGame;
import com.pj2z.pj2zbe.balanceGame.service.BalanceGameService;
import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/balance-game")
public class BalanceGameController {

    private final BalanceGameService balanceGameService;

    public BalanceGameController(BalanceGameService balanceGameService) {
        this.balanceGameService = balanceGameService;
    }
    @GetMapping("/today")
    public ResponseEntity<Object> getTodaybalanceGame() {
        try {
            BalanceGame todayGame = balanceGameService.getTodayGame();

            return ResponseEntity.ok(todayGame);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                    "message", ex.getMessage()
            ));
        }
    }

    ///e.g. balance-game?date=2025-04-05
    @GetMapping
    public ResponseEntity<Object> getBalanceGame(@RequestParam("date") String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            BalanceGame game = balanceGameService.getBalanceGameByDate(date);
            return ResponseEntity.ok(game);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createGame(@RequestBody BalanceGameRequest request) {
        try {
            BalanceGame makeGame = balanceGameService.createBalanceGame(request);
            return ResponseEntity.ok(makeGame);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateGame(@RequestBody BalanceGameRequest request) {
        try {
            BalanceGame updateGame = balanceGameService.updateBalanceGame(request);
            return ResponseEntity.ok(updateGame);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        }

    }

    @PostMapping("/vote")
    public ResponseEntity<Object> voteGame(@UserCheck User user, @RequestBody BalanceGameVoteRequest request) {
       // balanceGameService.vote(user.getId(),request.getGameDate(), request.getChoice());
       // return ResponseEntity.ok().build();
        return null;
    }
}
