package NEW;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

public class ChangeLanguage_1 extends Login_1 {
    @BeforeMethod
    public void changeLanguage() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();
    }
}
