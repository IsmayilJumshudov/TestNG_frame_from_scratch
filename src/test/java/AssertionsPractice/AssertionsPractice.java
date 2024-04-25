package AssertionsPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsPractice {


    @Test
    public void verifyAmazonLogo () throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        Thread.sleep(7000);

        WebElement amazonLogo = driver.findElement(By.id("nav-logo-sprites"));
        Assert.assertTrue(amazonLogo.isDisplayed(), "Amazon Logo verification failed!");
        driver.quit();
    }

    @Test
    public void verifyBestBuyLogo () throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.bestbuy.com/");
        Thread.sleep(7000);

        WebElement bestbuyLogo = driver.findElement(By.xpath("//*[@title='BestBuy.com']"));
        Assert.assertTrue(bestbuyLogo.isDisplayed(), "BestBuy Logo verification failed!");
        driver.quit();
    }


    @Test
    public void verifyBestBuyCity () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.bestbuy.com/");
        Thread.sleep(3000);

        WebElement cityElement = driver.findElement(By.cssSelector(".store-display-name"));
        String cityValue = cityElement.getText();

        if (cityValue.equals("Fairlakes")) {
            cityElement.click();
        } else {
            Assert.fail("City validation failed!");
        }
    }


    @Test
    public void verify_bestbuy_title () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.bestbuy.com/");
        Thread.sleep(3000);

        String expectedTitle = "Best Buy | Official Online Store | Shop Now & Save";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title verification failed!");
        driver.close();
    }


    @Test
    public void verify_bestbuy_title_not_equals () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.bestbuy.com/");
        Thread.sleep(3000);

        String expectedTitle = "Best Buy";
        String actualTitle = driver.getTitle();
        Assert.assertNotEquals(actualTitle, expectedTitle, "Title verification failed!");
        driver.close();
    }


    @Test
    public void verify_text_area_disabled () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demos.jquerymobile.com/1.4.3/forms-disabled/");
        Thread.sleep(3000);

        WebElement textAreaInputField = driver.findElement(By.id("textarea-1"));
        String attrValue = textAreaInputField.getAttribute("disabled");
        Assert.assertEquals(attrValue, "true", "Textarea disabled attr verification failed!");
        Assert.assertNotNull(attrValue, "Textarea disabled attr verification failed!");
    }


    @Test
    public void verify_bestbuy_title_with_soft_assert () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.bestbuy.com/");
        Thread.sleep(3000);

        String expectedTitle = "Best Buy";
        String actualTitle = driver.getTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, expectedTitle, "Title verification failed!");
        softAssert.assertAll();
    }

    @Test
    public void verify_assert_true () {
        Assert.assertTrue(5<6, "Are you joking?!");
    }

    @Test
    public void verify_assert_false () {
        Assert.assertFalse(100 == 101, "hmmm?!");
    }

    @Test
    public void verify_assert_fail () {
        Assert.fail("My expected value failed!");
    }

}
