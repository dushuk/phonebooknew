import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;

    public static Logger logger() {
        return LoggerFactory.getLogger(TestBase.class);
    }


    @BeforeClass
    public static void setUp(Method m) {

        WebDriverManager.chromedriver().setup();
        logger().info("Setup chrome drive");
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        // driver.navigate().to("https://www.google.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger().info("Start Test");

    }

    public void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userData);
    }

    public boolean isElementPresents(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean isElementClikable(By by) {
        try {
            driver.findElement(by).click();
            return true;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void checkItemText(By locator, String expectedText, String err) {
        String actualText = driver.findElement(locator).getText();
        Assert.assertEquals(actualText, expectedText, err);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // - закроет полностью браузер
            //driver.close(); - закроет только вкладку
            logger().info("Stop Test");
        }
    }
}
