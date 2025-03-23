package com.pj2z.pj2zbe.mbti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pj2z.pj2zbe.mbti.entity.Mbti;
import com.pj2z.pj2zbe.mbti.entity.enums.MbtiType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class MbtiSelectDetailResponse
{

    @JsonProperty("mbti")
    private MbtiType mbtiType;

    @Max(100) @Min(0)
    @JsonProperty("ePercent")
    private Long ePercent;

    @Max(100) @Min(0)
    @JsonProperty("iPercent")
    private Long iPercent;

    @Max(100) @Min(0)
    @JsonProperty("nPercent")
    private Long nPercent;
    @Max(100) @Min(0)
    @JsonProperty("sPercent")
    private Long sPercent;

    @Max(100) @Min(0)
    @JsonProperty("tPercent")
    private Long tPercent;
    @Max(100) @Min(0)
    @JsonProperty("fPercent")
    private Long fPercent;

    @Max(100) @Min(0)
    @JsonProperty("pPercent")
    private Long pPercent;
    @Max(100) @Min(0)
    @JsonProperty("jPercent")
    private Long jPercent;

    public  MbtiSelectDetailResponse(Mbti mbti) {
        this.mbtiType = mbti.getMbtiType();
        this.ePercent = mbti.getEPercent();
        this.iPercent = mbti.getIPercent();
        this.nPercent = mbti.getNPercent();
        this.sPercent = mbti.getSPercent();
        this.tPercent = mbti.getTPercent();
        this.fPercent = mbti.getFPercent();
        this.pPercent = mbti.getPPercent();
        this.jPercent = mbti.getJPercent();
    }
}
