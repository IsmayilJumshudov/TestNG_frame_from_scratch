package HRM.Login;

import org.testng.annotations.DataProvider;

public class LoginPageData {
    @DataProvider(name = "loginCredentials")
    public Object[][] getCredentialsAndErrorMessage () {
        return new Object[][] {
            {"invalidUsername",    "invalidPassword", "Invalid credentials"     },
            {"",                   "invalidPassword", "Username cannot be empty"},
            {"invalidUsername",    "",                "Password cannot be empty"},
            {"",                   "",                "Username cannot be empty"}
        };
    }
}
