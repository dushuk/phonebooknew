package api.test.email;

import api.ApiBase;
import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.email.AddEmailDto;
import api.model.email.EmailDto;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpDateEmailTest extends ApiBase {
    Faker faker = new Faker();
    int contactId;
    int emailId;
    int wrongEmailId;
    int wrongContactId;
    Response response;
    String email = faker.internet().emailAddress();
    String newEmail = faker.internet().emailAddress();
    Response responseForEmail;
    ContactDto contactDto;
    AddEmailDto addEmailDto;
    EmailDto emailDto;

    @BeforeMethod
    public void precondition() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        contactId = response.jsonPath().getInt("id");
        addEmailDto = new AddEmailDto();
        addEmailDto.setEmail(email);
        addEmailDto.setContactId(contactId);
        doPostRequest(EndPoint.ADD_NEW_EMAIL, 201, addEmailDto);
        responseForEmail = doGetRequestWithParam(EndPoint.GET_LIST_OF_EMAILS_BY_CONTACT_ID, 200, contactId);
        emailId = responseForEmail.jsonPath().getInt("[0].id");
    }

    @AfterMethod
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTAKT, 200, contactId);
    }

    @Test
    public void updateEmail() {
        emailDto = new EmailDto();
        emailDto.setId(emailId);
        emailDto.setEmail(newEmail);
        emailDto.setContactId(contactId);

        doPutRequest(EndPoint.UPDATE_EMAIL, 200, emailDto);
    }

    @Test
    public void updateEmailWithWrongEmail() {
        wrongEmailId = getWrongId();
        emailDto = new EmailDto();
        emailDto.setId(wrongEmailId);
        emailDto.setEmail(newEmail);
        emailDto.setContactId(contactId);

        Response responseForUpdate = doPutRequest(EndPoint.UPDATE_EMAIL, 500, emailDto);

        Assert.assertEquals(responseForUpdate.jsonPath().getString("message"), ERROR_MESSAGE_FOR_EMAIL);
    }

    @Test
    public void updateEmailWithWrongContactId() {
        wrongContactId = getWrongId();
        emailDto = new EmailDto();
        emailDto.setId(emailId);
        emailDto.setEmail(newEmail);
        emailDto.setContactId(wrongContactId);

        Response responseForUpdate = doPutRequest(EndPoint.UPDATE_EMAIL, 500, emailDto);

        Assert.assertEquals(responseForUpdate.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);

    }
}
