package HRM.JobTitles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class JobTitlesPage {
    public JobTitlesPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='head']/h1")
    WebElement jobTitlesHeader;

    @FindBy(id = "btnAdd")
    WebElement addButton;

    @FindBy(id = "btnDelete")
    WebElement deleteButton;

    @FindBy(id = "jobTitle_jobTitle")
    WebElement jobTitleTextBox;

    @FindBy(id = "jobTitle_jobDescription")
    WebElement jobDescriptionTextBox;

    @FindBy(id = "btnSave")
    WebElement saveButton;

    @FindBy(id = "dialogDeleteBtn")
    WebElement modalWindowOKButton;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr/td[2]/a")
    List<WebElement> allJobTitles;

    public void verifyButtonsDisplayed () throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(addButton.isDisplayed(), "Add button verification failed!");
        Assert.assertTrue(deleteButton.isDisplayed(), "Delete button verification failed!");
    }

    public void addJobTitle (String jobTitle, String jobDescription) throws InterruptedException {
        Thread.sleep(2000);
        addButton.click();
        Thread.sleep(2000);
        jobTitleTextBox.sendKeys(jobTitle);
        Thread.sleep(1000);
        jobDescriptionTextBox.sendKeys(jobDescription);
        Thread.sleep(1000);
        saveButton.click();
    }

    public boolean verifyNewJobTitle (String expectedJobTitle) throws InterruptedException {
        Thread.sleep(2000);
        boolean isNewJobTitleCreated = false;

        for (WebElement jobTitle : allJobTitles) {
            String actualJobTitleValue = jobTitle.getText();

            if (actualJobTitleValue.equals(expectedJobTitle)) {
                isNewJobTitleCreated = true;
                break;
            }
        }

        return isNewJobTitleCreated;
    }

    public boolean verifyJobTitleDeleted (WebDriver driver, String expectedJobTitle) throws InterruptedException {
        Thread.sleep(2000);
        boolean isJobTitleDeleted = false;

        for (WebElement jobTitle : allJobTitles) {
            String actualJobTitleValue = jobTitle.getText();

            if (actualJobTitleValue.equals(expectedJobTitle)) {
                driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[2]/a[text()='"+expectedJobTitle+"']/../../td[1]")).click();
                Thread.sleep(1000);
                deleteButton.click();
                Thread.sleep(1000);
                modalWindowOKButton.click();
                isJobTitleDeleted = true;
                break;
            }
        }

        return isJobTitleDeleted;
    }
}
