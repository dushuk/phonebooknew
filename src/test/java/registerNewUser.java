import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class registerNewUser extends TestBase {

    By lofinForm = By.id("login-form");
    By userRegistrationForm = By.cssSelector("[href=\"/user/registration\"]");
    By RegistrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.name("confirm-password");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By errorMassageBlock = By.id("error-massage");

    Faker faker = new Faker();

    @Test
    public void registerNewUser() {
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        driver.findElement(lofinForm).isDisplayed();
        driver.findElement(userRegistrationForm).click();
        driver.findElement(RegistrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        driver.findElement(loginButton).click();
        String actualerrorMassage = driver.findElement(errorMassageBlock).getText();
        Assert.assertEquals(actualerrorMassage, expectedErrorMessage, "");
    }

}
