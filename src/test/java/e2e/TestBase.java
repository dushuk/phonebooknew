package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    static Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected final ApplicationManager app = new ApplicationManager();


    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        logger.info("Setup chrome drive");
    }

    @BeforeMethod
    public void setupTest() {
        app.init();
    }

    @BeforeMethod
    public void startTest(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
    }

    @AfterMethod
    public void tearDown() {
        //Thread.sleep(1000);
        app.stop();
    }

    @AfterMethod
    public void stopTest(ITestResult result) throws IOException {
        if (result.isSuccess()) {
            logger.info("PASSED" + result.getMethod().getMethodName());
        } else {
            logger.info("FAILED" + result.getMethod().getMethodName() + "Screens path: " + app.takeScreenshot());
        }

        logger.info("=========================================");
    }
}
