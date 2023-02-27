package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ContactHelpers extends CommonHelpers {

    public ContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void changeLanguage() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();
    }

    public void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));
    }

    public void openContact() {
        clickOnVisibleElement(By.xpath("//*[@id='contacts-list']//*[@class ='list-group']"));
    }

    public void openRemoveContactDialog() {
        openDialog(By.xpath("//*[@id='contacts-list']//*[@class ='list-group-item']/img"));
    }

    public void removeContact() throws InterruptedException {
        clickOnVisibleElement(By.id("check-box-remove-contact"));
        clickOnVisibleElement(By.id("submit-remove"));
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresents(By.xpath("//*[@role='dialog']")));
    }

    public void checkFieldsOnContactInfo(String firstName, String lastName, String description) {
        checkItemText(By.id("contact-first-name"), firstName, "Actual first name is not equal expected first name");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name is not equal expected last name");
        checkItemText(By.id("contact-description"), description, "Actual desciption is not equal expected desciption");
    }

    public void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(By.xpath("//*[@id='contacts-list']//*[@class ='list-group']")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }
}
