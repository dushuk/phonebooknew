package e2e.helpers;

import org.openqa.selenium.By;
import org.testng.Assert;

public class RegisterHelpers extends CommonHelpers {

    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.name("confirm-password");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    public By errorMassageBlock = By.id("error-message");
    public By errorEmailMassageBlock = By.id("email-error-invalid");
    public By errorPasswordMaxLenghtMassageBlock = By.id("password-error-maxlength");

    public void goToRegistrationPage() {
        Assert.assertTrue(isElementPresents(loginForm));
        driver.findElement(userRegistrationLink).click();
        Assert.assertTrue(isElementPresents(registrationForm));
    }

    public void fillRegistrationForm(String userData, String password) {
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
    }

    public void clickSignUpButton() {
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
    }

    public void checkErrorMassege(By locator, String expectedErrorMassage) {
        String err = "Actual error message is not equal expected";
        checkItemText(locator, expectedErrorMassage, err);
    }
}
