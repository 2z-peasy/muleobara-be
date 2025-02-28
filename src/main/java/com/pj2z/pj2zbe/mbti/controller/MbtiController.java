package com.pj2z.pj2zbe.mbti.controller;

import com.pj2z.pj2zbe.common.jwt.JwtUtil;
import com.pj2z.pj2zbe.mbti.dto.MbtiMakeRequest;
import com.pj2z.pj2zbe.mbti.service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users/mbti")
public class MbtiController {

    @Autowired
    MbtiService mbtiService;

    private final JwtUtil jwtUtil;

    @Autowired
    public MbtiController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }




}
