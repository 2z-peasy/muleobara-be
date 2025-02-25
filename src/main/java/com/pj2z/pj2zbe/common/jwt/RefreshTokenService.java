package com.pj2z.pj2zbe.common.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;

    private final RedisTemplate<String, String> redisTemplate;

    public void saveRefreshToken(String userId, String refreshToken) {
        redisTemplate.opsForValue().set(
                getKey(userId),
                refreshToken,
                REFRESH_TOKEN_EXPIRE_TIME,
                TimeUnit.SECONDS
        );
    }

    public String getRefreshToken(String loginId) {
        return redisTemplate.opsForValue().get(loginId);
    }

    public void deleteRefreshToken(String loginId) {
        redisTemplate.delete(loginId);
    }

    public boolean validateRefreshToken(String username, String refreshToken) {
        String storedToken = redisTemplate.opsForValue().get(getKey(username));
        return refreshToken.equals(storedToken);
    }

    private String getKey(String username) {
        return "refreshToken:" + username;
    }
}
