package api.test.contact;

import api.ApiBase;
import api.enums.EndPoint;
import api.model.ContactDto;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AddNewContactTest extends ApiBase {

    Faker faker = new Faker();
    ContactDto contactDto;
    Response response;
    int id;

    @AfterMethod
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTAKT, 200, id);
    }

    @Test
    public void createContactTest() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());
    }

}
