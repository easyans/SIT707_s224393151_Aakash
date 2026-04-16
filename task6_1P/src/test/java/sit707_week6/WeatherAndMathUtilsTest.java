package sit707_week6;
import org.junit.Assert;
import org.junit.Test;

public class WeatherAndMathUtilsTest {

    @Test
    public void testStudentIdentity() {
        String studentId = "s224393151";
        Assert.assertNotNull("Student ID is null", studentId);
    }
    @Test
    public void testStudentName() {
        String studentName = "Aakash Kumar Raj Sakhineti";
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testFalseNumberIsEven() {
        Assert.assertFalse(WeatherAndMathUtils.isEven(3));
    }

    @Test
    public void testTrueNumberIsEven() {
        Assert.assertTrue(WeatherAndMathUtils.isEven(4));
    }

    @Test
    public void testZeroIsEven() {
        Assert.assertTrue(WeatherAndMathUtils.isEven(0));
    }

    @Test
    public void testIsPrime_Prime() {
        Assert.assertTrue(WeatherAndMathUtils.isPrime(7));
    }

    @Test
    public void testIsPrime_NotPrime() {
        Assert.assertFalse(WeatherAndMathUtils.isPrime(9));
    }

    @Test
    public void testIsPrime_One() {
        Assert.assertFalse(WeatherAndMathUtils.isPrime(1));
    }

    @Test
    public void testIsPrime_Two() {
        Assert.assertTrue(WeatherAndMathUtils.isPrime(2));
    }

    @Test
    public void testIsPrime_Negative() {
        Assert.assertFalse(WeatherAndMathUtils.isPrime(-5));
    }

    @Test
    public void testCancelWeatherAdvice() {
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(70.1, 0.0));
    }

    @Test
    public void testCancelWeatherAdvice_DangerousRain() {
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(0.0, 6.1));
    }

    @Test
    public void testCancelWeatherAdvice_DangerousWindAndRain() {
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(45.1, 4.1));
    }

    @Test
    public void testWarnWeatherAdvice_Wind() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(46.0, 0.0));
    }

    @Test
    public void testWarnWeatherAdvice_Rain() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(0.0, 4.1));
    }

    @Test
    public void testAllClearWeatherAdvice() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(0.0, 0.0));
    }
    
    @Test
    public void testWeatherAdvice_BoundaryWind45() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(45.0, 0.0));
    }

    @Test
    public void testWeatherAdvice_BoundaryRain4() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(0.0, 4.0));
    }

    @Test
    public void testWeatherAdvice_JustBelowDangerousWind() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(69.9, 0.0));
    }

    @Test
    public void testWeatherAdvice_JustBelowDangerousRain() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(0.0, 5.9));
    }
    
}
