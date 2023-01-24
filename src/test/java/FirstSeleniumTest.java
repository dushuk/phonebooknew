import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver;
    By emailField = (By.cssSelector("[placeholder=\"Email\"]"));

    //before
    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        // driver.navigate().to("https://www.google.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    //test
    @Test
    public void locators() {
        // driver.findElement(By.name("email")).sendKeys("test@gmail.com");
        //  driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("test@gmail.com");
        //driver.findElement(By.cssSelector("btn.btn-info"));
        //driver.findElement(By.cssSelector("[placeholder=\"Email\"]"));
        //driver.findElement(By.name("password"));
        //driver.findElement(By.cssSelector("[placeholder=\"Confirm Password\"]"));
        //driver.findElement(By.cssSelector(".btn.btn-info"));


    }

    @Test
    public void registerNewUser() {
        String userData = "test3@gmail.com";
        driver.findElement(By.id("login-form")).isDisplayed();
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registration-form")).isDisplayed();
        fillField(userData, emailField);
        fillField(userData, By.cssSelector("[placeholder=\"Password\"]"));
        fillField(userData, By.name("confirm-password"));
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
    }

    private void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userData);
    }

    //after
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        if (driver != null) {

            driver.quit(); // - закроет полностью браузер
            //driver.close(); - закроет только вкладку
        }
    }
}
