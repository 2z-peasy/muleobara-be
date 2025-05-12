package com.pj2z.pj2zbe.balanceGame.controller;

import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameRequest;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameResponse;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameVoteRequest;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGame;
import com.pj2z.pj2zbe.balanceGame.service.BalanceGameService;
import com.pj2z.pj2zbe.common.custom.AuthCheck;
import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.common.exception.BalanceGameException;
import com.pj2z.pj2zbe.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/balance-game")
public class BalanceGameController {

    private final BalanceGameService balanceGameService;

    public BalanceGameController(BalanceGameService balanceGameService) {
        this.balanceGameService = balanceGameService;
    }

    @GetMapping("/today")
    public ResponseEntity<Object> getTodaybalanceGame(@UserCheck User user) {
        try {
            BalanceGameResponse todayGame = balanceGameService.getTodayGame(user.getId());

            return ResponseEntity.ok(todayGame);
        } catch (BalanceGameException ex) {
            return ResponseEntity.status(ex.getStatus())
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        }
    }

    ///e.g. balance-game/?date=2025-04-05
    @GetMapping("/")
    public ResponseEntity<Object> getBalanceGame(@UserCheck User user, @RequestParam("date") String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            BalanceGameResponse game = balanceGameService.getBalanceGameByDate(date, user.getId());
            return ResponseEntity.ok(game);
        } catch (BalanceGameException ex) {
            return ResponseEntity.status(ex.getStatus())
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        }
    }

    @AuthCheck
    @PostMapping("/")
    public ResponseEntity<Object> createGame(@RequestBody BalanceGameRequest request) {
        try {

            BalanceGame makeGame = balanceGameService.createBalanceGame(request);
            return ResponseEntity.ok(makeGame);
        } catch (BalanceGameException ex) {
            return ResponseEntity.status(ex.getStatus())
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        }
    }

    @AuthCheck
    @PutMapping("/")
    public ResponseEntity<Object> updateGame(@RequestBody BalanceGameRequest request) {
        try {
            BalanceGame updateGame = balanceGameService.updateBalanceGame(request);
            return ResponseEntity.ok(updateGame);
        }catch (BalanceGameException ex) {
            return ResponseEntity.status(ex.getStatus())
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        }

    }

    @PostMapping("/vote")
    public ResponseEntity<Object> voteGame(@UserCheck User user, @RequestBody BalanceGameVoteRequest request) {
        try {
            balanceGameService.vote(user.getId(), request);
            return ResponseEntity.ok(Map.of(
                    "message", "투표완료"
            ));
        } catch (BalanceGameException ex) {
            return ResponseEntity.status(ex.getStatus())
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        }
    }
}
