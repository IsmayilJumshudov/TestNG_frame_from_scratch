package HRM.JobTitles;

import Common.DriverFactory;
import HRM.Home.HomePage;
import HRM.Login.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class JobTitlesPageTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    JobTitlesPage jobTitlesPage;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup (@Optional("chrome") String browserName) throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        driver = DriverFactory.createDriver(browserName);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        jobTitlesPage = new JobTitlesPage(driver);
        driver.get("http://dev-hrm.yoll.io/index.php/auth/login");
//        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test(groups = "smoke")
    public void verify_user_is_able_to_navigate_to_job_titles_page () throws InterruptedException {
        loginPage.login("yoll-student", "Bootcamp5#");
        homePage.validateUsernameHeader("Welcome Yoll");

        /* navigate to Job Titles Page */
        homePage.navigateToJobTitlesPage();
        /* verify Job Titles header */
        String actualHeader = jobTitlesPage.jobTitlesHeader.getText();
        String expectedHeader = "Job Titles";
        Assert.assertEquals(actualHeader, expectedHeader, "Job Titles header verification failed!");
    }

    /** Scenario: User should be able to add Job Title
     * Given I'm on homepage
     * I click on Admin→ Job → Job Titles
     * I see Add and Delete buttons displayed
     * I can add new Job Title
     * Then newly Added Job Title is displayed in Table
     */
    @Test(enabled = false)
    public void user_should_be_able_to_add_job_title () throws InterruptedException {
        String jobTitle = "JobTitle001";
        String jobDescription = "Automation Test Engineer";

        loginPage.login("yoll-student", "Bootcamp5#");
        homePage.validateUsernameHeader("Welcome Yoll");

        /* navigate to Job Titles Page */
        homePage.navigateToJobTitlesPage();
        /* verify that Add and Delete buttons are displayed */
        jobTitlesPage.verifyButtonsDisplayed();
        /* delete if Job Title already exists */
        Assert.assertTrue(jobTitlesPage.verifyJobTitleDeleted(driver, jobTitle), "Job Title deletion failed!");
        /* create a new job title */
        jobTitlesPage.addJobTitle(jobTitle, jobDescription);
        /* verify that new Job Title is displayed on the table */
        Assert.assertTrue(jobTitlesPage.verifyNewJobTitle(jobTitle), "Job Title verification failed!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown () throws InterruptedException {
        Thread.sleep(1000);
        //driver.close();
    }
}
