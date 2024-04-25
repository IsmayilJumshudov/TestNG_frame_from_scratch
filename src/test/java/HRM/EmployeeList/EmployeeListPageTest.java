package HRM.EmployeeList;

import Common.DriverFactory;
import HRM.Home.HomePage;
import HRM.JobTitles.JobTitlesPage;
import HRM.Login.LoginPage;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EmployeeListPageTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    EmployeeListPage employeeListPage;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup (String browserName) throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        driver = DriverFactory.createDriver(browserName);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        employeeListPage = new EmployeeListPage(driver);
        driver.get("http://dev-hrm.yoll.io/index.php/auth/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    //TODO HW: automate a scenario where you navigate and validate Employee Information Page

    /**
     * Given I’m logged in with Admin Account
     * And I click on PIM → Employee List
     * Then I click on an employee name
     * I click on contact Details
     * Then I click on Edit and Fill out Address
     * Then I click on Save
     * And I verify address is displayed
     */
    @Test
    public void user_should_be_able_to_add_contact_details () throws InterruptedException {
        loginPage.login("yoll-student", "Bootcamp5#");
        homePage.validateUsernameHeader("Welcome Yoll");

        /* navigate to Employee Information Page */
        homePage.navigateToEmployeeListSubTab();

        //TODO HM: refactor this code base on POM Design
        Thread.sleep(2000);
        driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[3]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ul[@id='sidenav']/li/a[text()='Contact Details']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@value='Edit'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("contact_street1")).clear();
        Thread.sleep(1000);

        Faker faker = new Faker();
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        driver.findElement(By.id("contact_street1")).sendKeys(address);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown () throws InterruptedException {
        Thread.sleep(1000);
        //driver.close();
    }
}
