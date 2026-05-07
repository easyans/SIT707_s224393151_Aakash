package sit707_week5;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {

	private static WeatherController wController;
	private static int nHours;
	private static double[] hourlyTemperatures;

	// ARRANGE 
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

	// AFTER - runs ONCE after all tests (handles will slow shutdown)
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

		// ACT - use shared hourlyTemperatures array (fast, no slow calls)
		double minTemperature = 1000;
		for (int i = 0; i < nHours; i++) {
			if (minTemperature > hourlyTemperatures[i]) {
				minTemperature = hourlyTemperatures[i];
			}
		}

		// ASSERT
		Assert.assertTrue(wController.getTemperatureMinFromCache() == minTemperature);
	}

	@Test
	public void testTemperatureMax() {
		System.out.println("+++ testTemperatureMax +++");

		// ACT - use shared hourlyTemperatures array (fast, no slow calls)
		double maxTemperature = -1;
		for (int i = 0; i < nHours; i++) {
			if (maxTemperature < hourlyTemperatures[i]) {
				maxTemperature = hourlyTemperatures[i];
			}
		}

		// ASSERT
		Assert.assertTrue(wController.getTemperatureMaxFromCache() == maxTemperature);
	}

	@Test
	public void testTemperatureAverage() {
		System.out.println("+++ testTemperatureAverage +++");

		// ACT - use shared hourlyTemperatures array (fast, no slow calls)
		double sumTemp = 0;
		for (int i = 0; i < nHours; i++) {
			sumTemp += hourlyTemperatures[i];
		}
		double averageTemp = sumTemp / nHours;

		// ASSERT
		Assert.assertTrue(wController.getTemperatureAverageFromCache() == averageTemp);
	}

	@Test
	public void testTemperaturePersist() {

	}
}