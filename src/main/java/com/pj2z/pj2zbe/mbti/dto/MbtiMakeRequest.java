package com.pj2z.pj2zbe.mbti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pj2z.pj2zbe.auth.entity.User;
import com.pj2z.pj2zbe.mbti.entity.Mbti;
import com.pj2z.pj2zbe.mbti.entity.enums.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.pj2z.pj2zbe.mbti.core.Mbtiutil.MakeMbtiType;

@Getter
@Setter
@Data
public class MbtiMakeRequest {


    @JsonProperty("eiType")
    private MbtiEI eiType;

    @JsonProperty("eiPercent")
    private Long eiPercent;

    @JsonProperty("nsType")
    private MbtiNS nsType;

    @JsonProperty("nsPercent")
    private Long nsPercent;

    @JsonProperty("tfType")
    private MbtiTF tfType;

    @JsonProperty("tfPercent")
    private Long tfPercent;

    @JsonProperty("pjType")
    private MbtiPJ pjType;

    @JsonProperty("pjPercent")
    private Long pjPercent;

    public MbtiType getMbti(){
        return MakeMbtiType(eiType,nsType,tfType,pjType);
    }
    public Mbti toEntity(User user) {


        return Mbti.builder()
                .user(user)
                .mbtiType(MakeMbtiType(eiType,nsType,tfType,pjType))
                .ePercent(eiType == MbtiEI.E ? eiPercent : 100-eiPercent)
                .iPercent(eiType == MbtiEI.I ? eiPercent : 100-eiPercent)
                .nPercent(nsType == MbtiNS.N ? nsPercent : 100-nsPercent)
                .sPercent(nsType == MbtiNS.S ? nsPercent : 100-nsPercent)
                .tPercent(tfType == MbtiTF.T ? tfPercent : 100-tfPercent)
                .fPercent(tfType == MbtiTF.F ? tfPercent : 100-tfPercent)
                .pPercent(pjType == MbtiPJ.P ? pjPercent : 100-pjPercent)
                .jPercent(pjType == MbtiPJ.J ? pjPercent : 100-pjPercent).build();

    }
}
