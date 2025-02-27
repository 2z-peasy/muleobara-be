package com.pj2z.pj2zbe.mbti.core;

import com.pj2z.pj2zbe.mbti.entity.Mbti;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MbtiPairSumValidator implements ConstraintValidator<MbtiPairSum, Mbti> {
    @Override
    public boolean isValid(Mbti mbti, ConstraintValidatorContext context) {
        return (mbti.getEPercent() + mbti.getIPercent() == 100) &&
                (mbti.getNPercent() + mbti.getSPercent() == 100) &&
                (mbti.getTPercent() + mbti.getFPercent() == 100) &&
                (mbti.getPPercent() + mbti.getJPercent() == 100);
    }
}

