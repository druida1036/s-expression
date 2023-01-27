package com.expression;

public final class NumberUtils {
    private NumberUtils() {
    }

    public static boolean isInteger(final String number) {
        return number.chars().allMatch(Character::isDigit);
    }
}
