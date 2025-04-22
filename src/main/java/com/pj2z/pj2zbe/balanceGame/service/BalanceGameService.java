package com.pj2z.pj2zbe.balanceGame.service;

import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameRequest;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameResponse;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameVoteRequest;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGameVote;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGame;
import com.pj2z.pj2zbe.balanceGame.entity.enums.BalanceGameVoteChoice;
import com.pj2z.pj2zbe.balanceGame.repository.BalanceGameRepository;
import com.pj2z.pj2zbe.balanceGame.repository.BalanceGameVoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BalanceGameService {

    private final BalanceGameRepository balanceGameRepository;
    private final BalanceGameVoteRepository balanceGameVoteRepository;

    public BalanceGameService(BalanceGameRepository balanceGameRepository,
                              BalanceGameVoteRepository balanceGameVoteRepository) {
        this.balanceGameRepository = balanceGameRepository;
        this.balanceGameVoteRepository = balanceGameVoteRepository;
    }
    public BalanceGameResponse getTodayGame(Long userId) {
        LocalDate today = LocalDate.now();
        return getBalanceGameByDate(today, userId);
    }

    public BalanceGameResponse getBalanceGameByDate(LocalDate date, Long userId) {
        return getBalanceGameResponse(date,userId);
    }

    public BalanceGameResponse getBalanceGameResponse(LocalDate date, Long userId) {
        BalanceGame game = balanceGameRepository.findById(date)
                .orElseThrow(() -> new RuntimeException("해당 날짜의 밸런스 게임이 없습니다."));

        // 사용자의 투표 여부 확인
        return balanceGameVoteRepository.findByIdUserIdAndIdGameDate(userId, date)
                .map(vote -> new BalanceGameResponse(game, vote.getChoice())) // 투표한 경우
                .orElse(new BalanceGameResponse(game));
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


    public void vote(Long userid, BalanceGameVoteRequest request) {

        balanceGameRepository.findById(request.getGameDate())
                .orElseThrow(() -> new RuntimeException("오늘의 밸런스 게임이 없습니다."));

        boolean alreadyVoted = balanceGameVoteRepository.existsByIdUserIdAndIdGameDate(userid, request.getGameDate());
        if (alreadyVoted) {
            throw new IllegalStateException("이미 투표한 게임입니다.");
        }

        BalanceGameVote vote = new BalanceGameVote(userid, request.getGameDate(), request.getChoice());
        balanceGameVoteRepository.save(vote);
    }
}
