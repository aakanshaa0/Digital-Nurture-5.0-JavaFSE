import org.junit.*;
import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calc;
    @Before
    public void setUp() {
        calc = new Calculator();
        System.out.println("Setup");
    }

    @After
    public void tearDown() {
        calc = null;
        System.out.println("Teardown");
    }

    @Test
    public void testAdd() {
        int a = 5, b = 3;
        int result = calc.add(a, b);
        assertEquals(8, result);
    }

    @Test
    public void testSubtract() {
        int a = 10, b = 4;
        int result = calc.subtract(a, b);
        assertEquals(6, result);
    }

    @Test
    public void testMultiply() {
        int a = 6, b = 7;
        int result = calc.multiply(a, b);
        assertEquals(42, result);
    }

    @Test
    public void testDivide() {
        int a = 20, b = 4;
        int result = calc.divide(a, b);
        assertEquals(5, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calc.divide(10, 0);
    }
}