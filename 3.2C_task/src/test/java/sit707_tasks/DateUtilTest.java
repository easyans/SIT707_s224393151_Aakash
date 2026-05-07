package sit707_tasks;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author Ahsan Habib
 */
public class DateUtilTest {
	
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
	public void testMaxJanuary31ShouldIncrementToFebruary1() {
		// January max boundary area: max+1
		DateUtil date = new DateUtil(31, 1, 2024);
        System.out.println("january31ShouldIncrementToFebruary1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxJanuary31ShouldDecrementToJanuary30() {
		// January max boundary area: max-1
		DateUtil date = new DateUtil(31, 1, 2024);
        System.out.println("january31ShouldDecrementToJanuary30 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(1, date.getMonth());
	}
	
	@Test
	public void testNominalJanuary() {
		int rand_day_1_to_31 = 1 + new Random().nextInt(31);
        DateUtil date = new DateUtil(rand_day_1_to_31, 1, 2024);
        System.out.println("testJanuaryNominal > " + date);
        date.increment();
        System.out.println(date);
	}
	
	/*
	 * Complete below test cases.
	 */
	
	@Test
	public void testMinJanuary1ShouldIncrementToJanuary2() {
		DateUtil date = new DateUtil(1,1, 2024);
		System.out.println("testMinJan1Increment >" + date);
		date.increment();
		System.out.println(date);
		
		Assert.assertEquals(2, date.getDay());
		Assert.assertEquals(1, date.getMonth());
	}
	
	@Test
	public void testMinJanuary1ShouldDecrementToDecember31() {
		DateUtil date = new DateUtil(1,1, 2024);
		System.out.println("testMinJan1Decrement >" + date);
		date.decrement();
		System.out.println(date);
		
		Assert.assertEquals(31, date.getDay());
		Assert.assertEquals(12, date.getMonth());
	}
	
	/*
	 * Write tests for rest months of year 2024.
	 */
	
	@Test
	public void testJune1Decrement() {
		DateUtil date = new DateUtil(1, 6, 2024);
		date.decrement();
		Assert.assertEquals(31, date.getDay());
		Assert.assertEquals(5, date.getMonth());
	}

	@Test
	public void testJune2Decrement() {
		DateUtil date = new DateUtil(2, 6, 2024);
		date.decrement();
		Assert.assertEquals(1, date.getDay());
	}

	@Test
	public void testJune30Decrement() {
		DateUtil date = new DateUtil(30, 6, 2024);
		date.decrement();
		Assert.assertEquals(29, date.getDay());
	}

	@Test
	public void testJune1Increment() {
		DateUtil date = new DateUtil(1, 6, 2024);
		date.increment();
		Assert.assertEquals(2, date.getDay());
	}

	@Test
	public void testJune2Increment() {
		DateUtil date = new DateUtil(2, 6, 2024);
		date.increment();
		Assert.assertEquals(3, date.getDay());
	}
	
	@Test
	public void testJune30IncrementToJuly1() {
	    DateUtil date = new DateUtil(30, 6, 2024);
	    System.out.println("testJune30Increment > " + date);
	    
	    date.increment();
	    
	    System.out.println(date);
	    
	    Assert.assertEquals(1, date.getDay());
	    Assert.assertEquals(7, date.getMonth());
	}

	
	@Test
	public void testApril30ToMay1() {
		DateUtil date = new DateUtil(30, 4, 2024);
		date.increment();
		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals(5, date.getMonth());
	}

	@Test
	public void testMarch31ToApril1() {
		DateUtil date = new DateUtil(31, 3, 2024);
		date.increment();
		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals(4, date.getMonth());
	}

	@Test
	public void testDecember31ToJanuary1NextYear() {
		DateUtil date = new DateUtil(31, 12, 2024);
		date.increment();
		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals(2025, date.getYear());
	}
	
	@Test(expected = RuntimeException.class)
	public void testInvalidDateJune31() {
		new DateUtil(31, 6, 2024);
	}

	// ---------- Here is the Leap year ----------
	
	@Test
	public void testLeapYearFeb29() {
		DateUtil date = new DateUtil(29, 2, 2024);
		date.increment();
		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals(3, date.getMonth());
	}
	
	// 3.2C TASK
	@Test
	public void testEC_D1_NormalDay() {
	    DateUtil date = new DateUtil(15, 5, 2023);
	    date.increment();
	    Assert.assertEquals(16, date.getDay());
	}

	@Test
	public void testEC_D2_Day29_NonLeap() {
	    DateUtil date = new DateUtil(29, 3, 2023);
	    date.increment();
	    Assert.assertEquals(30, date.getDay());
	}

	@Test
	public void testEC_D3_Day30() {
	    DateUtil date = new DateUtil(30, 4, 2023);
	    date.increment();
	    Assert.assertEquals(1, date.getDay());
	    Assert.assertEquals(5, date.getMonth());
	}

	@Test
	public void testEC_D4_Day31() {
	    DateUtil date = new DateUtil(31, 1, 2023);
	    date.increment();
	    Assert.assertEquals(1, date.getDay());
	    Assert.assertEquals(2, date.getMonth());
	}

	@Test
	public void testEC_Feb_NonLeapYear() {
	    DateUtil date = new DateUtil(28, 2, 2023);
	    date.increment();
	    Assert.assertEquals(1, date.getDay());
	    Assert.assertEquals(3, date.getMonth());
	}

	@Test
	public void testEC_Feb_LeapYear() {
	    DateUtil date = new DateUtil(28, 2, 2024);
	    date.increment();
	    Assert.assertEquals(29, date.getDay());
	}

	@Test
	public void testEC_YearChange() {
	    DateUtil date = new DateUtil(31, 12, 2023);
	    date.increment();
	    Assert.assertEquals(1, date.getDay());
	    Assert.assertEquals(1, date.getMonth());
	    Assert.assertEquals(2024, date.getYear());
	}
	
	
}