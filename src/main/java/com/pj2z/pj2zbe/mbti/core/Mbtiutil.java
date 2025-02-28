package com.pj2z.pj2zbe.mbti.core;

import com.pj2z.pj2zbe.mbti.entity.enums.*;
import org.springframework.stereotype.Component;

@Component
public class Mbtiutil {
    public static MbtiType MakeMbtiType(MbtiEI mbtiEI, MbtiNS mbtiNS, MbtiTF mbtiTF, MbtiPJ mbtiPJ) {
        String mbti = mbtiEI.name() + mbtiNS.name() + mbtiTF.name() + mbtiPJ.name();
        return MbtiType.valueOf(mbti);
    }
}
