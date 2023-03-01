package api.test.contact;

import api.ApiBase;
import api.enums.EndPoint;
import api.model.ContactDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTest extends ApiBase {
    int id;
    int wrongId;
    Response response;
    ContactDto contactDto;
    
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
        wrongId = getWrongId();
        response = doDeleteRequest(EndPoint.DELETE_CONTAKT, 500, wrongId);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE);
    }

}
