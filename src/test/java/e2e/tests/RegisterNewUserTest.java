package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends TestBase {

    Faker faker = new Faker();

    // Positive
    @Test
    public void registerNewUserWithValidData() {
        // Arrange
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMassage = "noErrorMsg";
        // Act
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        app.getRegister().clickSignUpButton();
        // Assert
        app.getRegister().checkErrorMassege(app.getRegister().errorMassageBlock, expectedErrorMassage);
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
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        Assert.assertFalse(app.getRegister().isElementPresents(app.getRegister().errorMassageBlock));
        // Assert
        app.getRegister().checkErrorMassege(app.getRegister().errorEmailMassageBlock, expectedEmailErrorMassage);
        app.getRegister().checkErrorMassege(app.getRegister().errorPasswordMaxLenghtMassageBlock, expectedPasswordErrorMassage);
    }

    // Negative
    @Test
    public void registerExistingUser() {
        // Arrange
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMassage = "Error! User already exists  now?";
        // Act
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        app.getRegister().clickSignUpButton();
        app.getRegister().checkErrorMassege(app.getRegister().errorMassageBlock, expectedErrorMassage);
    }


}
