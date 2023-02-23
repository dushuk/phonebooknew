package e2e.tests;

import com.github.javafaker.Faker;
import e2e.helpers.CommonHelpers;
import e2e.helpers.RegisterHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends CommonHelpers {

    Faker faker = new Faker();

    public RegisterHelpers registerHelpers = new RegisterHelpers();

    // Positive
    @Test
    public void registerNewUserWithValidData() {
        // Arrange
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMassage = "noErrorMsg";
        // Act
        registerHelpers.goToRegistrationPage();
        registerHelpers.fillRegistrationForm(userData, password);
        registerHelpers.clickSignUpButton();
        // Assert
        registerHelpers.checkErrorMassege(registerHelpers.errorMassageBlock, expectedErrorMassage);
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
        registerHelpers.goToRegistrationPage();
        registerHelpers.fillRegistrationForm(userData, password);
        Assert.assertFalse(isElementPresents(registerHelpers.errorMassageBlock));
        // Assert
        registerHelpers.checkErrorMassege(registerHelpers.errorEmailMassageBlock, expectedEmailErrorMassage);
        registerHelpers.checkErrorMassege(registerHelpers.errorPasswordMaxLenghtMassageBlock, expectedPasswordErrorMassage);
    }

    // Negative
    @Test
    public void registerExistingUser() {
        // Arrange
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMassage = "Error! User already exists  now?";
        // Act
        registerHelpers.goToRegistrationPage();
        registerHelpers.fillRegistrationForm(userData, password);
        registerHelpers.clickSignUpButton();
        registerHelpers.checkErrorMassege(registerHelpers.errorMassageBlock, expectedErrorMassage);
    }


}
