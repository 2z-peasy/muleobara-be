package com.pj2z.pj2zbe.balanceGame.service;

import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameRequest;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGameVote;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGame;
import com.pj2z.pj2zbe.balanceGame.entity.enums.BalanceGameVoteChoice;
import com.pj2z.pj2zbe.balanceGame.repository.BalanceGameRepository;
import com.pj2z.pj2zbe.balanceGame.repository.BalanceGameVoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BalanceGameService {

    private final BalanceGameRepository balanceGameRepository;
    private final BalanceGameVoteRepository balanceGameVoteRepository;

    public BalanceGameService(BalanceGameRepository balanceGameRepository,
                              BalanceGameVoteRepository balanceGameVoteRepository) {
        this.balanceGameRepository = balanceGameRepository;
        this.balanceGameVoteRepository = balanceGameVoteRepository;
    }
    public BalanceGame getTodayGame() {
        LocalDate today = LocalDate.now();
        return balanceGameRepository.findById(today)
                .orElseThrow(() -> new RuntimeException("오늘의 밸런스 게임이 없습니다."));
    }

    public BalanceGame getBalanceGameByDate(LocalDate date) {
        return balanceGameRepository.findById(date)
                .orElseThrow(() -> new RuntimeException("해당 날짜의 밸런스 게임이 없습니다."));
    }

    public BalanceGame createBalanceGame(BalanceGameRequest request) {
        if (balanceGameRepository.existsById(request.getGameDate())) {
            throw new IllegalArgumentException("이미 해당 날짜의 밸런스 게임이 존재합니다.");
        }

        BalanceGame game = new BalanceGame(
                request.getGameDate(),
                request.getQuestion(),
                request.getOptionA(),
                request.getOptionB()
        );
        return balanceGameRepository.save(game);
    }

    public BalanceGame updateBalanceGame(BalanceGameRequest request) {
        BalanceGame existing = balanceGameRepository.findById(request.getGameDate())
                .orElseThrow(() -> new RuntimeException("수정할 밸런스 게임이 없습니다."));

        existing.updateBalanceGame(request.getQuestion(), request.getOptionA(), request.getOptionB());
        return balanceGameRepository.save(existing);
    }


    public void vote(Long userid, LocalDate gameDate, BalanceGameVoteChoice choice) {
        BalanceGameVote vote = new BalanceGameVote(userid, gameDate, choice);
        balanceGameVoteRepository.save(vote);
    }
}
