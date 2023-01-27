package com.expression;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ExpressionType {
    ADD("ADD", "+", 1) {
        @Override
        int compute(int element1, int element2) {
            return element1 + element2;
        }
    },
    MULTIPLY("MULTIPLY", "*", 2) {
        @Override
        int compute(int element1, int element2) {
            return element1 * element2;
        }
    },
    OPEN_PARENTHESES("(", "(", -1) {
        @Override
        int compute(int element1, int element2) {
            throw new UnsupportedOperationException();
        }
    },

    CLOSE_PARENTHESES(")", ")", -1) {
        @Override
        int compute(int element1, int element2) {
            throw new UnsupportedOperationException();
        }
    };
    private static final Map<String, ExpressionType> symbols = Arrays.stream(values())
            .collect(Collectors.toMap(ExpressionType::getSymbol, Function.identity()));

    private static final Map<String, ExpressionType> labels = Arrays.stream(values())
            .collect(Collectors.toMap(ExpressionType::getLabel, Function.identity()));

    private final String label;
    private final String symbol;
    private final int precedence;

    ExpressionType(String label, String symbol, int precedence) {
        this.label = label;
        this.symbol = symbol;
        this.precedence = precedence;
    }

    abstract int compute(int element1, int element2);

    public static Optional<ExpressionType> findBySymbol(String value) {return Optional.ofNullable(symbols.get(value));}

    public static Optional<ExpressionType> findByLabel(String value) {
        return Optional.ofNullable(labels.get(value));
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPrecedence() {
        return precedence;
    }

    public String getLabel() {
        return label;
    }
}
