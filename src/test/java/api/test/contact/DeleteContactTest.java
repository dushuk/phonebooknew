package api.test.contact;

import api.ApiBase;
import api.enums.EndPoint;
import api.model.ContactDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class DeleteContactTest extends ApiBase {
    int id;
    Response response;
    ContactDto contactDto;

    String errorMassage = "Error! This contact doesn't exist in our DB";

    @BeforeMethod(onlyForGroups = {"positive"})
    public void precondition() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
    }

    @Test(groups = ("positive"))
    public void deleteContactTest() {
        doDeleteRequest(EndPoint.DELETE_CONTAKT, 200, id);
    }

    @Test
    public void deleteContactWithoutId() {
        Random rnd = new Random();
        int wrongId = 100000 + rnd.nextInt(900000);
        response = doDeleteRequest(EndPoint.DELETE_CONTAKT, 500, wrongId);
        Assert.assertEquals(response.jsonPath().getString("message"), errorMassage);
    }

}
