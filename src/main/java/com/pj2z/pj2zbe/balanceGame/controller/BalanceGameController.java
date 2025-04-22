package com.pj2z.pj2zbe.balanceGame.controller;

import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameRequest;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameResponse;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameVoteRequest;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGame;
import com.pj2z.pj2zbe.balanceGame.service.BalanceGameService;
import com.pj2z.pj2zbe.common.custom.UserCheck;
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
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> createGame(@UserCheck User user,@RequestBody BalanceGameRequest request) {
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

    @PutMapping("/")
    public ResponseEntity<Object> updateGame(@UserCheck User user,@RequestBody BalanceGameRequest request) {
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

    // 투표관련되서는 밸런스게임과 커뮤니티의 투표가 별도이며 밸런스게임 하나의 종속적인 투표기능이기 떄문에 합쳤습니다.
    @PostMapping("/vote")
    public ResponseEntity<Object> voteGame(@UserCheck User user, @RequestBody BalanceGameVoteRequest request) {
        try {
            balanceGameService.vote(user.getId(), request);
            return ResponseEntity.ok("투표 완료");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", ex.getMessage()
                    ));
        }
    }
}
