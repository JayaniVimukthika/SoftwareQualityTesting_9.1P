package web.service;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertThrows;

import web.service.MathQuestionService;

public class TestMathQuestionService {

	@Test
	public void testTrueAdd() {
		Assert.assertEquals(MathQuestionService.q1Addition("1", "2"), 3, 0);
	}

	@Test
	public void testAddNumber1Empty() {
		Assert.assertNull(MathQuestionService.q1Addition(" ", "2"));
	}	
	
	@Test
    public void testAdditionValidInputQ1() {
        double result = MathQuestionService.q1Addition("6", "4");
        Assert.assertEquals(result, 10, 0);
    }

    @Test
    public void testAdditionInvalidInputNullQ1() {
        assertThrows(NumberFormatException.class, () -> {
            MathQuestionService.q1Addition(null, "6");
        });
    }

    @Test
    public void testAdditionInvalidInputNonNumericQ1() {
        assertThrows(NumberFormatException.class, () -> {
            MathQuestionService.q1Addition("abc", "6");
        });
    }

    @Test
    public void testSubtractionValidInputQ2() {
        double result = MathQuestionService.q2Subtraction("10", "2");
        Assert.assertEquals(result, 8, 0);
    }

    @Test
    public void testSubtractionInvalidInputNullQ2() {
        assertThrows(NumberFormatException.class, () -> {
            MathQuestionService.q2Subtraction(null, "2");
        });
    }

    @Test
    public void testSubtractionInvalidInputNonNumericQ2() {
        assertThrows(NumberFormatException.class, () -> {
            MathQuestionService.q2Subtraction("abc", "2");
        });
    }
}
