package NEW;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTest_1 extends ChangeLanguage_1 {

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

    @Test
    public void createNewContact() throws InterruptedException {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.internet().uuid();
        Number expectedCountRow = 1;

        openAddNewContactDialog();
        fillAddNewContactForm(firstName, lastName, description);
        saveNewContact();
        checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        goToContactPageAndFillFilterField(firstName);
        checkCountRows(expectedCountRow);

    }

}
