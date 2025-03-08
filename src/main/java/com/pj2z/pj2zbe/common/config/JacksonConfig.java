package com.pj2z.pj2zbe.common.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Snake case로 자동 변환
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        // JavaTimeModule 추가 (LocalDateTime 직렬화/역직렬화 지원)//
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") 로 이용
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }
}
