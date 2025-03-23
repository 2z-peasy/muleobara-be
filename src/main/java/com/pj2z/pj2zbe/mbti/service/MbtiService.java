package com.pj2z.pj2zbe.mbti.service;

import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.repository.UserRepository;
import com.pj2z.pj2zbe.mbti.dto.MbtiMakeRequest;
import com.pj2z.pj2zbe.mbti.dto.MbtilSelectAllResponse;
import com.pj2z.pj2zbe.mbti.entity.Mbti;
import com.pj2z.pj2zbe.mbti.repository.MbtiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MbtiService {

    @Autowired
    MbtiRepository mbtiRepository;

    @Autowired
    UserRepository userRepository;

    public void saveMbti(User user, MbtiMakeRequest mbti) {
        mbtiRepository.save(mbti.toEntity(user));
    }

    public Mbti selectUsersMbti(User user) {
        Mbti mbti = mbtiRepository.findTopByUserOrderByCreatedAtDesc(user)
                .orElseThrow(() -> new RuntimeException("mbti not found"));

        return mbti;
    }

    public Page<MbtilSelectAllResponse> getUserMbtiList(User user, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Mbti> mbtiPage = mbtiRepository.findByUserOrderByCreatedAtDesc(user, pageable);
        if (mbtiPage.isEmpty()) {
            throw new RuntimeException("MBTI 데이터가 없습니다.");
        }

        return mbtiPage.map(MbtilSelectAllResponse::from);
    }
}
