package com.pj2z.pj2zbe.balanceGame.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGameVote;
import com.pj2z.pj2zbe.balanceGame.entity.BalanceGameVoteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BalanceGameVoteRepository extends JpaRepository<BalanceGameVote, BalanceGameVoteId> {
    boolean existsByIdUserIdAndIdGameDate(Long userId, LocalDate gameDate);

    // 추후 데이터가 10만건 이상일 경우에는 단독인덱스 추가 고려
    boolean existsByIdGameDate(LocalDate gameDate);

    Optional<BalanceGameVote> findByIdUserIdAndIdGameDate(Long userId, LocalDate gameDate);


}
