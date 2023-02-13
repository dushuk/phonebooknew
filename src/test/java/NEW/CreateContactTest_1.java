package NEW;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTest_1 extends Login_1 {
    @Test
    public void createNewContact() {
        String firstName = "";
        String lastName = "";
        String descroption = "";
        Number expectedCountRow = 1;

        // click on the button "Add new contact"
        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        Assert.assertTrue(isElementPresents(By.xpath("//*[@role='dialog']")));
        // Fill field First name
        fillField(firstName, By.xpath("//*[@role='dialog']//*[placeholder='First name']"));
        // Fill field Last name
        fillField(lastName, By.xpath("//*[@role='dialog']//*[placeholder='Last name']"));
        // Fill field About
        fillField(descroption, By.xpath("//*[@role='dialog']//*[placeholder='About']"));
        // Click on the button "Save"
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        Assert.assertFalse(isElementPresents(By.xpath("//*[@role='dialog']")));
        // Filter by creation name
        fillField(firstName, By.xpath("//*['placeholder=Search...']"));

        Number actualCountRow = driver.findElements(By.className("list-group")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
        // Expected result. Created contact show with correct data in the contact table
    }
}
