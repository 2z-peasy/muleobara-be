package com.pj2z.pj2zbe.common.custom;

import com.pj2z.pj2zbe.user.entity.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthCheck {
    Role role() default Role.ROLE_ADMIN;
}
