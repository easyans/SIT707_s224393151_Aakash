package web.service;

public class LoginService {

    public static boolean login(String username, String password, String dob) {

        // Rule 1: null check
        if (username == null || password == null || dob == null) {
            return false;
        }

        // Rule 1: empty check
        if (username.isEmpty() || password.isEmpty() || dob.isEmpty()) {
            return false;
        }

        // Rule 2: dob format must be yyyy-mm-dd
        if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }

        // Rule 3: match all three fields exactly
        if ("ahsan".equals(username)
                && "ahsan_pass".equals(password)
                && "2000-01-01".equals(dob)) {
            return true;
        }

        return false;
    }
}