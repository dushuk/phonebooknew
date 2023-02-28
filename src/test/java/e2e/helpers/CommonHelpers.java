package e2e.helpers;

import e2e.utils.Recorder;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CommonHelpers {

    WebDriver driver;
    public ScreenRecorder screenRecorder;

    public CommonHelpers(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnVisibleElement(By locator) {
        Assert.assertTrue(isElementPresents(locator));
        driver.findElement(locator).click();
    }

    public void startRecording() throws IOException, AWTException {
        File file = new File("records");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        screenRecorder = new Recorder(gc, captureSize, new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG, CompressorNameKey, ENCODING_AVI_MJPG,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null,
                file, "My Video");
        screenRecorder.start();
    }

    public void stopRecording() throws IOException {
        screenRecorder.stop();
    }

    public String deleteFiles(String folder) {
        File directory = new File(folder);
        File[] files = directory.listFiles();
        for (File f : files) {
            f.delete();
        }
        return "deleted all files: " + folder;
    }

    public void openDialog(By locator) {
        clickOnVisibleElement(locator);
        Assert.assertTrue(isElementPresents(By.xpath("//*[@role='dialog']")));
    }

    public void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
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

    public void checkItemText(By locator, String expectedText, String err) {
        String actualText = driver.findElement(locator).getText();
        Assert.assertEquals(actualText, expectedText, err);
    }

}
