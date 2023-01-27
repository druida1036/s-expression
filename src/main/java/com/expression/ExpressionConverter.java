package com.expression;

import java.util.Stack;

import static com.expression.NumberUtils.isInteger;

public class ExpressionConverter {

    private static final String INPUT_INVALID = "Invalid expression: [%s].";
    private static final String UNBALANCED_PARENTHESIS = "Unbalanced parenthesis.";

    public String toPostFix(final String[] input) {
        final Stack<String> stack = new Stack<>();
        final StringBuilder output = new StringBuilder();

        for (String element : addParenthesisSpace(input)) {
            if (isInteger(element)) {
                output.append(element);
                continue;
            }
            ExpressionType expression = getExpressionType(element);
            switch (expression) {
                case OPEN_PARENTHESES -> stack.push(element);
                case CLOSE_PARENTHESES -> processCloseParentheses(stack, output);
                default -> processFunctions(stack, output, expression);
            }
        }
        processRemainingStackElements(stack, output);
        return output.toString();
    }

    private String[] addParenthesisSpace(final String[] input) {
        return String.join(" ", input)
            .replaceAll("\\(", "( ")
            .replaceAll("\\)", " )")
            .split(" ");
    }

    private ExpressionType getExpressionType(final String value) {
        return ExpressionType.findByLabel(value.toUpperCase()).orElseThrow(() -> {
            throw new IllegalArgumentException(String.format(INPUT_INVALID, value));
        });
    }

    private void processCloseParentheses(final Stack<String> stack, final StringBuilder output) {

        while (!stack.isEmpty() && !isOpenParentheses(stack.peek())) {
            output.append(stack.pop());
        }

        if (stack.isEmpty()) {
            throw new IllegalArgumentException(UNBALANCED_PARENTHESIS);
        }
        stack.pop();
    }

    private void processFunctions(final Stack<String> stack, final StringBuilder output,
        final ExpressionType currentElement) {
        while (!stack.isEmpty()) {
            int precedenceStackElement = getPrecedenceByExpressionTypeSymbol(stack.peek());
            if (currentElement.getPrecedence() > precedenceStackElement) {
                break;
            }
            output.append(stack.pop());
        }
        stack.push(currentElement.getSymbol());
    }

    private Integer getPrecedenceByExpressionTypeSymbol(final String value) {
        return ExpressionType.findBySymbol(value).map(ExpressionType::getPrecedence).orElse(-1);
    }

    private void processRemainingStackElements(final Stack<String> stack, final StringBuilder output) {
        while (!stack.isEmpty()) {
            if (isOpenParentheses(stack.peek())) {
                throw new IllegalArgumentException(UNBALANCED_PARENTHESIS);
            }
            output.append(stack.pop());
        }
    }

    private boolean isOpenParentheses(final String value) {
        return ExpressionType.OPEN_PARENTHESES.getLabel().equals(value);
    }
}
