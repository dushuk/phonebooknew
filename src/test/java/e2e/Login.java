package e2e;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class Login extends TestBase {

    By loginForm = By.id("login-form");
    By emailField = By.name("email");
    By passwordField = By.name("password");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By contactsTable = By.id("contacts-list");

    @BeforeMethod
    public void login() {
        String userName = "test@gmail.com";
        String password = "test@gmail.com";

        driver.findElement(loginForm).isDisplayed();
        fillField(userName, emailField);
        fillField(password, passwordField);
        driver.findElement(loginButton).click();

        Assert.assertTrue(isElementPresents(contactsTable));
    }

}
