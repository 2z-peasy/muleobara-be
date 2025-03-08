package com.pj2z.pj2zbe.mbti.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj2z.pj2zbe.mbti.entity.Mbti;
import com.pj2z.pj2zbe.mbti.entity.enums.MbtiType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class MbtilSelectAllResponse {
    private Long id;
    private MbtiType mbtiType;
    private Long epercent;
    private Long ipercent;
    private Long npercent;
    private Long spercent;
    private Long tpercent;
    private Long fpercent;
    private Long ppercent;
    private Long jpercent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    public static MbtilSelectAllResponse from(Mbti mbti) {
        return new MbtilSelectAllResponse(
                mbti.getId(),
                mbti.getMbtiType(),
                mbti.getEPercent(),
                mbti.getIPercent(),
                mbti.getNPercent(),
                mbti.getSPercent(),
                mbti.getTPercent(),
                mbti.getFPercent(),
                mbti.getPPercent(),
                mbti.getJPercent(),
                mbti.getCreatedAt(),
                mbti.getModifiedAt()
        );
    }
}
