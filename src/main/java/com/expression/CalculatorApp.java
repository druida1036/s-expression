package com.expression;

import com.expression.Calculator.Builder;

public class CalculatorApp {

    public static void main(String[] args) {
        new Builder().defaultConfig().calculate(args);
    }


}
