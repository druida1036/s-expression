package com.expression;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    @Mock
    private ExpressionEvaluator expressionEvaluator;

    @Mock
    private ExpressionConverter expressionConverter;

    @InjectMocks
    private Calculator calculator;

    @Test
    public void testCalculateWhenInputNullShouldSuccess() {
        calculator.calculate(null);

        verifyNoInteractions(expressionConverter);
        verifyNoInteractions(expressionEvaluator);
    }

    @Test
    public void testCalculateWhenInputEmptyShouldSuccess() {
        calculator.calculate(new String[]{});

        verifyNoInteractions(expressionConverter);
        verifyNoInteractions(expressionEvaluator);
    }

    @Test
    public void testCalculateWhenInputEmptyShouldSuccess1() {
        String[] input = "(add 1 1)".split(" ");

        when(expressionConverter.toPostFix(any())).thenReturn("11+");
        calculator.calculate(input);

        verify(expressionConverter).toPostFix(input);
        verify(expressionEvaluator).evaluatePostfix("11+");
    }
}