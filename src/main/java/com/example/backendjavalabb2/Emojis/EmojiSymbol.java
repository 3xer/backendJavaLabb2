package com.example.backendjavalabb2.Emojis;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = EmojiSymbolValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EmojiSymbol {
    String message() default "Must be emoji";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

