package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class EditContactInfoTest extends TestBase {

    @Test(dataProvider = "changeLastNameAndDescription", dataProviderClass = DataProviders.class)
    public void editContactInfo(String lastName, String description) throws InterruptedException {
        String firstName = "87cb48d1-5b25-4b63-9408-8534b403a2a8";
        app.getLogin().login();
        app.getEditeContact().changeLanguage();
        app.getEditeContact().goToContactPageAndFillFilterField(firstName);
        app.getEditeContact().checkCountRows(1);
        app.getEditeContact().openContact();
        app.getEditeContact().openEditForm();
        app.getEditeContact().editeLastNameAndDescription(lastName, description);
        app.getEditeContact().saveEditedContact();
        app.getEditeContact().checkFieldsOnContactInfo(firstName, lastName, description);

    }
}
