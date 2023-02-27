package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginHelpers extends CommonHelpers {

    By loginForm = By.id("login-form");
    By emailField = By.name("email");
    By passwordField = By.name("password");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By contactsTable = By.id("contacts-list");

    public LoginHelpers(WebDriver driver) {
        super(driver);
    }

    public void login() {
        String email = "test@gmail.com";
        String password = "test@gmail.com";
        driver.findElement(loginForm).isDisplayed();
        fillField(email, emailField);
        fillField(password, passwordField);
        driver.findElement(loginButton).click();
        Assert.assertTrue(isElementPresents(contactsTable));
    }

}
