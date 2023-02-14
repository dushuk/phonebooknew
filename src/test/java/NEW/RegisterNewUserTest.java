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
    By errorEmailMassageBlock = By.id("email-error-invalid");
    By errorPasswordMaxLenghtMassageBlock = By.id("password-error-maxlength");
    Faker faker = new Faker();

    private void goToRegistrationPage() {
        Assert.assertTrue(isElementPresents(loginForm));
        driver.findElement(userRegistrationLink).click();
        Assert.assertTrue(isElementPresents(registrationForm));
    }

    private void fillRegistrationForm(String userData, String password) {
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
    }

    private void clickSignUpButton() {
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
    }

    private void checkErrorMassege(By locator, String expectedErrorMassage) {
        String actualErrorMessage = driver.findElement(locator).getText();
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualErrorMessage, expectedErrorMassage, err);
    }


    // Positive
    @Test
    public void registerNewUserWithValidData() {
        // Arrange
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMassage = "noErrorMsg";
        // Act
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        clickSignUpButton();
        // Assert
        checkErrorMassege(errorMassageBlock, expectedErrorMassage);
    }


    // Negative
    @Test
    public void registerNewUserWithInvalidData() {
        // Arrange
        String userData = faker.internet().password();
        String password = faker.internet().emailAddress();
        String expectedEmailErrorMassage = "Email must be a valid email address.";
        String expectedPasswordErrorMassage = "Password must be no longer than 20 characters.";
        // Act
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        Assert.assertFalse(isElementPresents(errorMassageBlock));
        // Assert
        checkErrorMassege(errorEmailMassageBlock, expectedEmailErrorMassage);
        checkErrorMassege(errorPasswordMaxLenghtMassageBlock, expectedPasswordErrorMassage);
    }

    // Negative
    @Test
    public void registerExistingUser() {
        // Arrange
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMassage = "Error! User already exists Login now?";
        // Act
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        clickSignUpButton();
        checkErrorMassege(errorMassageBlock, expectedErrorMassage);
    }


}
