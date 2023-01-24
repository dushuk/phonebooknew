import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class RegisterNewUser extends TestBase {

    By lofinForm = By.id("login-form");
    By userRegistrationForm = By.cssSelector("[href=\"/user/registration\"]");
    By RegistrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.name("confirm-password");
    By loginButton = By.xpath("//*[@type=\"submit\"]");

    Faker faker = new Faker();

    @Test
    public void registerNewUser() {
        String userData = faker.internet().emailAddress();
        driver.findElement(lofinForm).isDisplayed();
        driver.findElement(userRegistrationForm).click();
        driver.findElement(RegistrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(userData, passwordField);
        fillField(userData, confirmPasswordField);
        driver.findElement(loginButton).click();
    }


}
