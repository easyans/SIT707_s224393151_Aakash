package web.service;

import org.junit.Assert;
import org.junit.Test;

public class LoginServiceUnitTest {

    /** EC-01: All three fields correct → true */
    @Test
    public void testValidLogin() {
        Assert.assertTrue(LoginService.login("ahsan", "ahsan_pass", "2000-01-01"));
    }

    /** EC-02: Wrong username, correct password and dob → false */
    @Test
    public void testWrongUsername() {
        Assert.assertFalse(LoginService.login("wrong_user", "ahsan_pass", "2000-01-01"));
    }

    /** EC-03: Correct username, wrong password, correct dob → false */
    @Test
    public void testWrongPassword() {
        Assert.assertFalse(LoginService.login("ahsan", "wrong", "2000-01-01"));
    }

    /** EC-04: Correct username and password, wrong dob → false */
    @Test
    public void testWrongDob() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "1999-01-01"));
    }

    /** EC-05: All three fields wrong → false */
    @Test
    public void testAllFieldsWrong() {
        Assert.assertFalse(LoginService.login("bad_user", "bad_pass", "1990-12-31"));
    }

    /** BVA-01: Username is null → false */
    @Test
    public void testNullUsername() {
        Assert.assertFalse(LoginService.login(null, "ahsan_pass", "2000-01-01"));
    }

    /** BVA-02: Password is null → false */
    @Test
    public void testNullPassword() {
        Assert.assertFalse(LoginService.login("ahsan", null, "2000-01-01"));
    }

    /** BVA-03: Dob is null → false */
    @Test
    public void testNullDob() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", null));
    }

    /** BVA-04: All fields null → false */
    @Test
    public void testNullInput() {
        Assert.assertFalse(LoginService.login(null, null, null));
    }

    /** BVA-05: Username is empty → false */
    @Test
    public void testEmptyUsername() {
        Assert.assertFalse(LoginService.login("", "ahsan_pass", "2000-01-01"));
    }

    /** BVA-06: Password is empty → false */
    @Test
    public void testEmptyPassword() {
        Assert.assertFalse(LoginService.login("ahsan", "", "2000-01-01"));
    }

    /** BVA-07: Dob is empty → false */
    @Test
    public void testEmptyDob() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", ""));
    }

    /** BVA-08: All fields empty → false */
    @Test
    public void testEmptyInput() {
        Assert.assertFalse(LoginService.login("", "", ""));
    }

    /** BVA-09: Dob wrong format - slashes instead of dashes → false */
    @Test
    public void testDobWrongFormatSlashes() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "01/01/2000"));
    }

    /** BVA-10: Dob wrong format - no separators → false */
    @Test
    public void testDobWrongFormatNoSeparator() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "20000101"));
    }

    /** DT-01: Correct username, wrong password, wrong dob → false */
    @Test
    public void testCorrectUsername_WrongPasswordAndDob() {
        Assert.assertFalse(LoginService.login("ahsan", "bad_pass", "1985-06-20"));
    }

    /** DT-02: Wrong username, correct password, wrong dob → false */
    @Test
    public void testWrongUsername_CorrectPassword_WrongDob() {
        Assert.assertFalse(LoginService.login("bad_user", "ahsan_pass", "1985-06-20"));
    }

    /** DT-03: Wrong username, wrong password, correct dob → false */
    @Test
    public void testWrongUsernameAndPassword_CorrectDob() {
        Assert.assertFalse(LoginService.login("bad_user", "bad_pass", "2000-01-01"));
    }

    /** CS-01: Username uppercase → false (case-sensitive) */
    @Test
    public void testUppercaseUsername() {
        Assert.assertFalse(LoginService.login("AHSAN", "ahsan_pass", "2000-01-01"));
    }

    /** CS-02: Password uppercase → false (case-sensitive) */
    @Test
    public void testUppercasePassword() {
        Assert.assertFalse(LoginService.login("ahsan", "AHSAN_PASS", "2000-01-01"));
    }
}