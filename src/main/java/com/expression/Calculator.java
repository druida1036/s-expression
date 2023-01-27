package com.expression;

public class Calculator {

    private final ExpressionConverter expressionConverter;
    private final ExpressionEvaluator expressionEvaluator;


    public Calculator(ExpressionConverter expressionConverter, ExpressionEvaluator expressionEvaluator) {
        this.expressionConverter = expressionConverter;
        this.expressionEvaluator = expressionEvaluator;
    }

    public void calculate(String[] input) {
        if (input == null || input.length == 0) {
            return;
        }
        final String expressionParsed = expressionConverter.toPostFix(input);
        System.out.println(expressionEvaluator.evaluatePostfix(expressionParsed));
    }

    public final static class Builder {
        public Builder() {
        }
        public Calculator defaultConfig(){
            return new Calculator(new ExpressionConverter(), new ExpressionEvaluator());
        }


    }

}
