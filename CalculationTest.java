

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CalculatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CalculationTest
{
    private final Calculation calc = new Calculation();
    @Test
   public void testAdd(){ 
      assertEquals(2, calc.add(1, 1));
    }

}
