package com.pj2z.pj2zbe.mbti.controller;

import com.pj2z.pj2zbe.common.custom.UserCheck;
import com.pj2z.pj2zbe.mbti.dto.MbtiMakeRequest;
import com.pj2z.pj2zbe.mbti.dto.MbtiSelectDetailResponse;
import com.pj2z.pj2zbe.mbti.dto.MbtilSelectAllResponse;
import com.pj2z.pj2zbe.mbti.entity.Mbti;
import com.pj2z.pj2zbe.mbti.service.MbtiService;
import com.pj2z.pj2zbe.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users/mbti")
public class MbtiController {

    @Autowired
    MbtiService mbtiService;

    @PostMapping
    public ResponseEntity<Object> saveMbti(@UserCheck User user, @RequestBody MbtiMakeRequest request) {
        mbtiService.saveMbti(user, request);
        return ResponseEntity.ok(Map.of(
                "mbti", request.getMbti(),
                "message", "MBTI 정보가 저장되었습니다."
        ));
    }

    @GetMapping
    public ResponseEntity<Object> selectUsersMbti(@UserCheck User user) {
        Mbti mbti = mbtiService.selectUsersMbti(user);
        return ResponseEntity.ok(Map.of(
                "mbti", mbti.getMbtiType()
        ));
    }

    @GetMapping("/detail")
    public ResponseEntity<Object> selectUsersMbtidetail(@UserCheck User user) {
        Mbti mbti = mbtiService.selectUsersMbti(user);
        return ResponseEntity.ok(new MbtiSelectDetailResponse(mbti));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getUserMbtiList(@UserCheck User user,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        try {
            Page<MbtilSelectAllResponse> mbtiPage = mbtiService.getUserMbtiList(user, page, size);
            return ResponseEntity.ok(mbtiPage);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", ex.getMessage()
            ));
        }
    }
}
