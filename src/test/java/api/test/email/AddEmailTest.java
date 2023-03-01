package api.test.email;

import api.ApiBase;
import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.email.AddEmailDto;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddEmailTest extends ApiBase {
    Faker faker = new Faker();
    int id;
    int wrongId;
    Response response;
    ContactDto contactDto;
    AddEmailDto addEmailDto;

    @BeforeMethod(onlyForGroups = {"positive", "correctId"})
    public void precondition() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
    }

    @AfterMethod(onlyForGroups = {"positive", "correctId"})
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTAKT, 200, id);
    }

    @Test(groups = {"positive"})
    public void addEmailTest() {
        addEmailDto = new AddEmailDto();
        addEmailDto.setEmail(faker.internet().emailAddress());
        addEmailDto.setContactId(id);

        doPostRequest(EndPoint.ADD_NEW_EMAIL, 201, addEmailDto);
    }

    @Test(groups = {"correctId"})
    public void addEmailWithoutEmail() {
        addEmailDto = new AddEmailDto();
        addEmailDto.setContactId(id);

        doPostRequest(EndPoint.ADD_NEW_EMAIL, 400, addEmailDto);
    }

    @Test
    public void addEmailWithWrongId() {
        wrongId = getWrongId();
        addEmailDto = new AddEmailDto();
        addEmailDto.setEmail(faker.internet().emailAddress());
        addEmailDto.setContactId(wrongId);

        response = doPostRequest(EndPoint.ADD_NEW_EMAIL, 500, addEmailDto);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);
    }

    @Test
    public void addEmailWithEmptyBody() {
        addEmailDto = new AddEmailDto();
        doPostRequest(EndPoint.ADD_NEW_EMAIL, 400, addEmailDto);
    }


}
