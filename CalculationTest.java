

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
    @Test
    public void testSub(){
        assertEquals(3, calc.sub(6,3));
    }
    @Test
    public void testMult(){
        assertEquals(6, calc.sub(2,3));
    }
    @Test
    public void testDiv(){
        assertEquals(2, calc.sub(6,3));
    }
    @Test
    public void testMod(){
        assertEquals(1, calc.sub(11,10));
    }

}
