
package sit707_week6;

import org.junit.Assert;
import org.junit.Test;

public class LoopUtilsTest {

    @Test
    public void testSumUpTo_Five() {
        Assert.assertEquals(15, LoopUtils.sumUpTo(5));
    }
    @Test
    public void testSumUpTo_Zero() {
        Assert.assertEquals(0, LoopUtils.sumUpTo(0));
    }
    @Test
    public void testSumUpTo_One() {
        Assert.assertEquals(1, LoopUtils.sumUpTo(1));
    }
    @Test
    public void testSumUpTo_Ten() {
        Assert.assertEquals(55, LoopUtils.sumUpTo(10)); 
    }
    @Test
    public void testCountEvenNumbers_Five() {
        Assert.assertEquals(2, LoopUtils.countEvenNumbers(5));
    }
    @Test
    public void testCountEvenNumbers_Zero() {
        Assert.assertEquals(0, LoopUtils.countEvenNumbers(0));
    }
    @Test
    public void testCountEvenNumbers_One() {
        Assert.assertEquals(0, LoopUtils.countEvenNumbers(1)); 
    }
    @Test
    public void testCountEvenNumbers_Two() {
        Assert.assertEquals(1, LoopUtils.countEvenNumbers(2));
    }
    @Test
    public void testCountEvenNumbers_Ten() {
        Assert.assertEquals(5, LoopUtils.countEvenNumbers(10)); 
    }
}





