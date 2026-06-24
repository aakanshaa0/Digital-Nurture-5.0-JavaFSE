import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {
    Calculator calc = new Calculator();
    @Test
    public void testAssertions() {
        assertEquals(5, calc.add(2, 3));
        assertTrue(calc.isPositive(5));
        assertFalse(calc.isPositive(-3));
        assertNull(calc.greet(null));
        assertNotNull(calc.greet("John"));
    }
}