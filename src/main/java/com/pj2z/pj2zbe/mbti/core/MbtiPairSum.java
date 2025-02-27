package com.pj2z.pj2zbe.mbti.core;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MbtiPairSumValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MbtiPairSum {
    String message() default "MBTI의 각 쌍의 합은 100%이 되야합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
