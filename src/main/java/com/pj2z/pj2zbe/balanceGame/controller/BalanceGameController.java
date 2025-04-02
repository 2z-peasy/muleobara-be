package com.pj2z.pj2zbe.balanceGame.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance-game")
public class BalanceGameController {

    @GetMapping
    public ResponseEntity<Object> getTodaybalanceGame() {
        return null;
    }
}
