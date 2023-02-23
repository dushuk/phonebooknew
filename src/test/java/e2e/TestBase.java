package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;

    static Logger logger = LoggerFactory.getLogger(TestBase.class);


    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        logger.info("Setup chrome drive");
    }

    @BeforeMethod
    public void setupTest(Method m, Object[] p) {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
    }

    @AfterMethod
    public void tearDown(Method m) {
        //Thread.sleep(1000);
        if (driver != null) {
            driver.quit();
        }
        logger.info("Stop test " + m.getName());
        logger.info("=========================================");
    }
}
