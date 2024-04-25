package HRM.Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    public LoginPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "txtUsername")
    WebElement usernameTextInput;

    @FindBy(id = "txtPassword")
    WebElement passwordTextInput;

    @FindBy(xpath = "//*[@id='btnLogin']")
    WebElement loginButton;

    @FindBy(id = "spanMessage")
    WebElement errorMessage;

    public void login (String username, String password) {
        usernameTextInput.sendKeys(username);
        passwordTextInput.sendKeys(password);
        loginButton.click();
    }

    public void verifyErrorMessage (String expectedErrorMessage) {
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message validation failed!");
    }
}
