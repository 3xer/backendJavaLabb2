package com.example.backendjavalabb2.Emojis;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.function.IntPredicate;


//get explaination
public class EmojiSymbolValidator implements ConstraintValidator<EmojiSymbol, String> {
    @Override
    public boolean isValid(String symbol, ConstraintValidatorContext constraintValidatorContext){
            IntPredicate predicate1 = Character::isEmoji;
            IntPredicate predicate2 = Character::isEmojiComponent;
            IntPredicate combinedPredicate = predicate1.or(predicate2);
            return Objects.nonNull(symbol) && symbol.codePoints().allMatch(combinedPredicate);
        }
}
