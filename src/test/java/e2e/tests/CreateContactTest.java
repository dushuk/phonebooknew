package e2e.tests;

import com.github.javafaker.Faker;
import e2e.MainPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateContactTest extends MainPage {

    Faker faker = new Faker();

    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Alex", "Dushuk", "This I"});
        list.add(new Object[]{"Max", "Spiegel", "This Max"});
        list.add(new Object[]{"Andrei", "Luft", "This Andrei"});

        return list.iterator();
    }

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

    @Test(dataProvider = "newContact")
    public void createNewContact(String firstName, String lastName, String description) throws InterruptedException {

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
