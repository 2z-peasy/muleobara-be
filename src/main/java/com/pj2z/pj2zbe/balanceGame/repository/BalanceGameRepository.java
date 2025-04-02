package com.pj2z.pj2zbe.balanceGame.repository;

import com.pj2z.pj2zbe.balanceGame.entity.BalanceGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BalanceGameRepository extends JpaRepository<BalanceGame, LocalDate> {
}
