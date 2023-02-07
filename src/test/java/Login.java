import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class Login extends TestBase {

    By loginForm = By.id("login-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By constantsTable = By.id("contacts-list");

    @BeforeMethod
    public void login() {
        String username = "test@gmail.com";
        String password = "test@gmail.com";

        driver.findElement(loginForm).isDisplayed();
        fillField(username, emailField);
        fillField(password, passwordField);
        driver.findElement(loginButton).click();

        Assert.assertTrue(isElementPresents(constantsTable));
    }

}

