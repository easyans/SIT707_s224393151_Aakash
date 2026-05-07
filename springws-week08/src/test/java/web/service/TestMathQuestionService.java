package web.service;

import org.junit.Assert;
import org.junit.Test;

public class TestMathQuestionService {

    @Test
    public void testQ1AdditionNormalValues() {
        Assert.assertEquals(8.0, MathQuestionService.q1Addition("3", "5"), 0);
    }

    @Test
    public void testQ1AdditionEmptyInput() {
        Assert.assertTrue(Double.isNaN(MathQuestionService.q1Addition("", "2")));
    }

    @Test
    public void testQ1AdditionInvalidInput() {
        Assert.assertTrue(Double.isNaN(MathQuestionService.q1Addition("abc", "2")));
    }

    @Test
    public void testQ2SubtractionNormalValues() {
        Assert.assertEquals(6.0, MathQuestionService.q2Subtraction("10", "4"), 0);
    }

    @Test
    public void testQ2SubtractionEmptyInput() {
        Assert.assertTrue(Double.isNaN(MathQuestionService.q2Subtraction("", "4")));
    }

    @Test
    public void testQ2SubtractionInvalidInput() {
        Assert.assertTrue(Double.isNaN(MathQuestionService.q2Subtraction("xyz", "4")));
    }

    @Test
    public void testQ3MultiplicationNormalValues() {
        Assert.assertEquals(12.0, MathQuestionService.q3Multiplication("4", "3"), 0);
    }

    @Test
    public void testQ3MultiplicationEmptyInput() {
        Assert.assertTrue(Double.isNaN(MathQuestionService.q3Multiplication("", "3")));
    }

    @Test
    public void testQ3MultiplicationInvalidInput() {
        Assert.assertTrue(Double.isNaN(MathQuestionService.q3Multiplication("abc", "3")));
    }
}




