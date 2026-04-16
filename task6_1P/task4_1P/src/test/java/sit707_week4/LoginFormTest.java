package sit707_week4;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests functions in LoginForm.
 * @author Ahsan Habib
 */
public class LoginFormTest 
{

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
    public void testFailEmptyUsernameAndEmptyPasswordAndDontCareValCode()
    {
		LoginStatus status = LoginForm.login(null, null);
		Assert.assertTrue( status.isLoginSuccess() == false );
    }
	
	/*
	 * Write more test functions below.
	 */
	@Test
	public void testEmptyUsernameValidPassword() {
	    LoginStatus status = LoginForm.login(null, "ahsan_pass");
	    Assert.assertFalse(status.isLoginSuccess());
	}
	@Test
	public void testValidUsernameEmptyPassword() {
	    LoginStatus status = LoginForm.login("ahsan", null);
	    Assert.assertFalse(status.isLoginSuccess());
	}
	@Test
	public void testWrongUsernameWrongPassword() {
	    LoginStatus status = LoginForm.login("abc", "xyz");
	    Assert.assertFalse(status.isLoginSuccess());
	}
	@Test
	public void testCorrectUsernameWrongPassword() {
	    LoginStatus status = LoginForm.login("ahsan", "xyz");
	    Assert.assertFalse(status.isLoginSuccess());
	}
	@Test
	public void testWrongUsernameCorrectPassword() {
	    LoginStatus status = LoginForm.login("abc", "ahsan_pass");
	    Assert.assertFalse(status.isLoginSuccess());
	}
	@Test
	public void testCorrectUsernameCorrectPassword() {
	    LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
	    Assert.assertTrue(status.isLoginSuccess());
	}
	@Test
	public void testEmptyStringUsername() {
	    LoginStatus status = LoginForm.login("", "ahsan_pass");
	    Assert.assertFalse(status.isLoginSuccess());
	}
	@Test
	public void testEmptyStringPassword() {
	    LoginStatus status = LoginForm.login("ahsan", "");
	    Assert.assertFalse(status.isLoginSuccess());
	}
	@Test
	public void testEmptyStringUsernameAndPassword() {
	    LoginStatus status = LoginForm.login("", "");
	    Assert.assertFalse(status.isLoginSuccess());
	}
	// validate code test 
	@Test
	public void testValidationCodeScenarios() {
	    Assert.assertFalse(LoginForm.validateCode(null));
	    Assert.assertFalse(LoginForm.validateCode("abcd"));
	    Assert.assertTrue(LoginForm.validateCode("123456"));
	}
}
