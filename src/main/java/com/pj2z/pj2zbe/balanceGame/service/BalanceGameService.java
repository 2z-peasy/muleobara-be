package com.pj2z.pj2zbe.balanceGame.service;

import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameRequest;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameResponse;
import com.pj2z.pj2zbe.balanceGame.dto.BalanceGameVoteRequest;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGameVote;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGame;
import com.pj2z.pj2zbe.balanceGame.exception.*;
import com.pj2z.pj2zbe.balanceGame.repository.BalanceGameRepository;
import com.pj2z.pj2zbe.balanceGame.repository.BalanceGameVoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public BalanceGameResponse getTodayGame(Long userId) {
        LocalDate today = LocalDate.now();
        return getBalanceGameByDate(today, userId);
    }

    public BalanceGameResponse getBalanceGameByDate(LocalDate date, Long userId) {
        if (date.isAfter(LocalDate.now())) {
            throw new CannotViewFutureBalanceGameException();
        }
        return getBalanceGameResponse(date,userId);
    }

    public BalanceGameResponse getBalanceGameResponse(LocalDate date, Long userId) {
        BalanceGame game = balanceGameRepository.findById(date)
                .orElseThrow(BalaceGameNotFoundException::new);

        // 사용자의 투표 여부 확인
        return balanceGameVoteRepository.findByIdUserIdAndIdGameDate(userId, date)
                .map(vote -> new BalanceGameResponse(game, vote.getChoice())) // 투표한 경우
                .orElse(new BalanceGameResponse(game));
    }

    public BalanceGame createBalanceGame(BalanceGameRequest request) {
        if (balanceGameRepository.existsById(request.getGameDate())) {
            throw new AlreadyExistBalanceGameDateException(request.getGameDate());
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
        // 1. 게임이 없을경우 불가
        // 2. 투표를 한사람이 있을 경우 불가.

        BalanceGame existing = balanceGameRepository.findById(request.getGameDate())
                .orElseThrow(BalaceGameNotFoundException::new);

        //Balance게임에 있는 카운트로 해도 될듯
        boolean alreadyVoted = balanceGameVoteRepository.existsByIdGameDate(request.getGameDate());
        if (alreadyVoted) {
            throw new AlreadyBalanceGameVotedException();
        }

        existing.updateBalanceGame(request.getQuestion(), request.getOptionA(), request.getOptionB());
        return balanceGameRepository.save(existing);
    }

    //투표와 득표율이 같이 적용되도록 추가
    @Transactional
    public void vote(Long userid, BalanceGameVoteRequest request) {
        //1. 당일게임이 아닌경우 불가
        //2. 게임이 없을 경우 불가
        //3. 이미 투표를 했을경우 불가

        if(!request.getGameDate().equals(LocalDate.now())) {
            throw new OnlyVoteTodayBalanceGameException();
        }

        BalanceGame balanceGame = balanceGameRepository.findById(request.getGameDate())
                .orElseThrow(BalaceGameNotFoundException::new);

        boolean alreadyVoted = balanceGameVoteRepository.existsByIdUserIdAndIdGameDate(userid, request.getGameDate());
        if (alreadyVoted) {
            throw new AlreadyBalanceGameVotedUserException();
        }

        BalanceGameVote vote = new BalanceGameVote(userid, request.getGameDate(), request.getChoice());

        balanceGameVoteRepository.save(vote);

        // 득표 수 업데이트
        balanceGame.incrementOptionCount(request.getChoice());
        balanceGameRepository.save(balanceGame);
    }
}
