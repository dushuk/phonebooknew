package api.test.contact;

import api.ApiBase;
import api.enums.EndPoint;
import api.model.contact.ContactDto;
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

    @AfterMethod(onlyForGroups = {"positive"})
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTAKT, 200, id);
    }

    @Test(groups = ("positive"))
    public void createContactTest() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());
    }

    @Test
    public void createContactWithoutFirstName() {
        contactDto = new ContactDto();
        contactDto.setLastName(faker.name().lastName());
        contactDto.setDescription(faker.lorem().sentence(4));
        doPostRequest(EndPoint.ADD_NEW_CONTACT, 400, contactDto);
    }

}
