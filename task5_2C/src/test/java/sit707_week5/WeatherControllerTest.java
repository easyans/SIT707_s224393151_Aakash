package sit707_week5;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static int nHours;
    private static double[] hourlyTemperatures;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("+++ setUpBeforeClass +++");
        wController = WeatherController.getInstance();
        nHours = wController.getTotalHours();
        hourlyTemperatures = new double[nHours];
        for (int i = 0; i < nHours; i++) {
            hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("+++ tearDownAfterClass +++");
        wController.close();
    }

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
    public void testTemperatureMin() {
        System.out.println("+++ testTemperatureMin +++");

        double minTemperature = 1000;
        for (int i = 0; i < nHours; i++) {
            if (minTemperature > hourlyTemperatures[i]) {
                minTemperature = hourlyTemperatures[i];
            }
        }

        Assert.assertTrue(wController.getTemperatureMinFromCache() == minTemperature);
    }

    @Test
    public void testTemperatureMax() {
        System.out.println("+++ testTemperatureMax +++");

        double maxTemperature = -1;
        for (int i = 0; i < nHours; i++) {
            if (maxTemperature < hourlyTemperatures[i]) {
                maxTemperature = hourlyTemperatures[i];
            }
        }

        Assert.assertTrue(wController.getTemperatureMaxFromCache() == maxTemperature);
    }

    @Test
    public void testTemperatureAverage() {
        System.out.println("+++ testTemperatureAverage +++");

        double sumTemp = 0;
        for (int i = 0; i < nHours; i++) {
            sumTemp += hourlyTemperatures[i];
        }
        double averageTemp = sumTemp / nHours;

        Assert.assertTrue(wController.getTemperatureAverageFromCache() == averageTemp);
    }

    @Test
    public void testTemperaturePersist() {
        System.out.println("+++ testTemperaturePersist +++");
        final Date frozenTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("H:m:s");
        final String expectedTime = sdf.format(frozenTime);
        wController.setClock(new WeatherController.Clock() {
            public Date now() {
                return frozenTime;
            }
        });

        int testHour = 1;
        double testTemperature = hourlyTemperatures[testHour - 1];
        String persistedTime = wController.persistTemperature(testHour, testTemperature);

        System.out.println("Expected (frozen) time : " + expectedTime);
        System.out.println("Persisted timestamp    : " + persistedTime);
        Assert.assertEquals(
            "Persisted time does not match frozen clock time",
            expectedTime,
            persistedTime
        );

        wController.setClock(new WeatherController.Clock() {
            public Date now() {
                return new Date();
            }
        });
    }
}