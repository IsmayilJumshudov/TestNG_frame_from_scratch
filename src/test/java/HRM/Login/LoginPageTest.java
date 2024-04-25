package HRM.Login;

import Common.DriverFactory;
import HRM.Home.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup (@Optional("chrome") String browserName) throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        driver = DriverFactory.createDriver(browserName);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        driver.get("http://dev-hrm.yoll.io/index.php/auth/login");
        Thread.sleep(3000);
    }

    @Test(priority = 2, groups = "smoke")
    public void verify_successful_login_with_valid_credentials () {
        loginPage.login("yoll-student", "Bootcamp5#");
        homePage.validateUsernameHeader("Welcome Yoll");
    }

    @Test(dataProviderClass = LoginPageData.class, dataProvider = "loginCredentials", priority = 1, enabled = false)
    public void user_should_not_be_able_to_Login_with_invalid_empty_credentials (String username, String password, String errorMessage) {
        loginPage.login(username, password);
        loginPage.verifyErrorMessage(errorMessage);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown () throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

}
