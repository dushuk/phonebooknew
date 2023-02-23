package e2e.tests;

import com.github.javafaker.Faker;
import e2e.helpers.MainHelpers;
import e2e.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTest extends MainHelpers {

    Faker faker = new Faker();

    private void openAddNewContactDialog() {
        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        Assert.assertTrue(isElementPresents(By.xpath("//*[@role='dialog']")));
    }

    private void fillAddNewContactForm(String firstName, String lastName, String description) {
        fillField(firstName, By.xpath("//*[@role='dialog']//*[@placeholder='First name']"));
        fillField(lastName, By.xpath("//*[@role='dialog']//*[@placeholder='Last name']"));
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));
    }

    private void saveNewContact() throws InterruptedException {
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresents(By.xpath("//*[@role='dialog']")));
    }

    private void checkFieldsOnContactInfoAfterCreatedContact(String firstName, String lastName, String description) {
        checkItemText(By.id("contact-first-name"), firstName, "Actual first name is not equal expected first name");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name is not equal expected last name");
        checkItemText(By.id("contact-description"), description, "Actual desciption is not equal expected desciption");
    }

    private void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));
    }

    private void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(By.className("list-group")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }

    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void createNewContactDataProvider(String firstName, String lastName, String description) throws InterruptedException {

        // String firstName = faker.internet().uuid();
        // String lastName = faker.internet().uuid();
        // String description = faker.internet().uuid();
        Number expectedCountRow = 1;

        openAddNewContactDialog();
        fillAddNewContactForm(firstName, lastName, description);
        saveNewContact();
        checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        goToContactPageAndFillFilterField(firstName);
        checkCountRows(expectedCountRow);
    }

    @Test(dataProvider = "newContactWithCSV", dataProviderClass = DataProviders.class)
    public void createNewContactDataProviderWithFileCSV(String firstName, String lastName, String description) throws InterruptedException {

        // String firstName = faker.internet().uuid();
        // String lastName = faker.internet().uuid();
        // String description = faker.internet().uuid();
        Number expectedCountRow = 1;

        openAddNewContactDialog();
        fillAddNewContactForm(firstName, lastName, description);
        saveNewContact();
        checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        goToContactPageAndFillFilterField(firstName);
        checkCountRows(expectedCountRow);
    }

}
