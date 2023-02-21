package e2e;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

public class MainPage extends Login {
    @BeforeMethod
    public void changeLanguage() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();
    }
}
