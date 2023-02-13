package NEW;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends TestBase_1 {

    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.name("confirm-password");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By errorMassageBlock = By.id("error-message");
    Faker faker = new Faker();


    @Test
    public void registerNewUser() {
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMassage = "noErrorMsg";

        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        driver.findElement(loginButton).click();
        String actualErrorMessage = driver.findElement(errorMassageBlock).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMassage);

    }

    private void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userData);
    }

}
