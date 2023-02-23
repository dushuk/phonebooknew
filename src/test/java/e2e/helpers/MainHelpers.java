package e2e.helpers;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

public class MainHelpers extends LoginHelpers {
    @BeforeMethod
    public void changeLanguage() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();
    }
}
