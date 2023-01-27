package com.expression;

import java.util.Stack;

import static com.expression.NumberUtils.isInteger;

public class ExpressionEvaluator {

    public static final String INSUFFICIENT_TERMS = "Insufficient terms to evaluate the expression";
    public static final String INVALID_EXPRESSION = "Invalid expression";

    int evaluatePostfix(final String input) {
        if (input.isEmpty()) {
            return 0;
        }
        if (isInteger(input)){
            return Integer.parseInt(input);
        }

        final Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char element = input.charAt(i);
            if (Character.isDigit(element)) {
                stack.push(Character.getNumericValue(element));
                continue;
            }
            if (stack.size() <2 ) {
                throw new IllegalArgumentException(INSUFFICIENT_TERMS);
            }
            final int result = ExpressionType.findBySymbol(String.valueOf(element))
                .orElseThrow(() -> {throw new IllegalArgumentException(INVALID_EXPRESSION);})
                .compute(stack.pop(), stack.pop());
            stack.push(result);
        }
        return stack.pop();
    }

}
