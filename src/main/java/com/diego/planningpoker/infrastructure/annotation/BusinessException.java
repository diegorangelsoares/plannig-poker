package com.diego.planningpoker.infrastructure.annotation;

import org.springframework.http.HttpStatus;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BusinessException {

    String key();

    HttpStatus status() default HttpStatus.BAD_REQUEST;

    boolean returnMessageException() default false;

}
