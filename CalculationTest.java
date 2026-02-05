import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Junit tests responsible for verifying the calculation class is correctly implemented
 */

// TODO: the existing test cases can be rewritten so that they trip the history function, this can be used to easily test the full history system
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
        assertEquals(6, calc.mul(2,3));
    }
    @Test
    public void testDiv(){
        assertEquals(2, calc.div(6,3));
    }
    @Test
    public void testMod(){
        assertEquals(1, calc.mod(11,10));
    }

}
