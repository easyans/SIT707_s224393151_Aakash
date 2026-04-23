package web.service;

import org.junit.Assert;
import org.junit.Test;

public class LoginServiceUnitTest {

    @Test
    public void testValidLogin() {
        Assert.assertTrue(LoginService.login("ahsan", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testWrongUsername() {
        Assert.assertFalse(LoginService.login("wrong_user", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testWrongPassword() {
        Assert.assertFalse(LoginService.login("ahsan", "wrong", "2000-01-01"));
    }

    @Test
    public void testWrongDob() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "1999-01-01"));
    }

    @Test
    public void testAllFieldsWrong() {
        Assert.assertFalse(LoginService.login("bad_user", "bad_pass", "1990-12-31"));
    }

    @Test
    public void testNullUsername() {
        Assert.assertFalse(LoginService.login(null, "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testNullPassword() {
        Assert.assertFalse(LoginService.login("ahsan", null, "2000-01-01"));
    }

    @Test
    public void testNullDob() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", null));
    }

    @Test
    public void testNullInput() {
        Assert.assertFalse(LoginService.login(null, null, null));
    }

    @Test
    public void testEmptyUsername() {
        Assert.assertFalse(LoginService.login("", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testEmptyPassword() {
        Assert.assertFalse(LoginService.login("ahsan", "", "2000-01-01"));
    }

    @Test
    public void testEmptyDob() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", ""));
    }

    @Test
    public void testEmptyInput() {
        Assert.assertFalse(LoginService.login("", "", ""));
    }

    @Test
    public void testDobWrongFormatSlashes() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "01/01/2000"));
    }

    @Test
    public void testDobWrongFormatNoSeparator() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "20000101"));
    }

    @Test
    public void testCorrectUsername_WrongPasswordAndDob() {
        Assert.assertFalse(LoginService.login("ahsan", "bad_pass", "1985-06-20"));
    }

    @Test
    public void testWrongUsername_CorrectPassword_WrongDob() {
        Assert.assertFalse(LoginService.login("bad_user", "ahsan_pass", "1985-06-20"));
    }

    @Test
    public void testWrongUsernameAndPassword_CorrectDob() {
        Assert.assertFalse(LoginService.login("bad_user", "bad_pass", "2000-01-01"));
    }

    @Test
    public void testUppercaseUsername() {
        Assert.assertFalse(LoginService.login("AHSAN", "ahsan_pass", "2000-01-01"));
    }

    @Test
    public void testUppercasePassword() {
        Assert.assertFalse(LoginService.login("ahsan", "AHSAN_PASS", "2000-01-01"));
    }
}
