package com.pj2z.pj2zbe.balanceGame.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
public class BalanceGameVoteId implements Serializable {
    @Column(name = "game_id")
    private LocalDate gameDate;

    @Column(name = "user_id")
    private Long userId;

    public BalanceGameVoteId(LocalDate gameDate, Long userId) {
        this.gameDate = gameDate;
        this.userId = userId;
    }
}
