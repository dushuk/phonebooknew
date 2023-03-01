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

public class GetEmailByIdTest extends ApiBase {
    Faker faker = new Faker();
    int contactId;
    int emailId;
    int wrongId;
    Response response;
    String email = faker.internet().emailAddress();
    Response responseForEmail;
    ContactDto contactDto;
    AddEmailDto addEmailDto;

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
    public void getEmailByEmailIdTest() {
        response = doGetRequestWithParam(EndPoint.GET_EMAIL_BY_EMAIL_ID, 200, emailId);
        EmailDto emailDto = response.as(EmailDto.class);

        Assert.assertEquals(emailDto.getId(), emailId);
        Assert.assertEquals(emailDto.getEmail(), email);
        Assert.assertEquals(emailDto.getContactId(), contactId);
    }

    @Test
    public void getListOfEmailsByContactIdTest() {
        Assert.assertEquals(responseForEmail.jsonPath().getString("[0].email"), email);
        Assert.assertEquals(responseForEmail.jsonPath().getInt("[0].contactId"), contactId);
    }
}
