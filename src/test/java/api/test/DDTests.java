package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UserEndpoints;
import api.payload.UserPOJO;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
    // Create Multiple Users with Data Providers
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostuser(String userID, String userName, String fname, String lname, String useremail, String pwd,
            String ph) {
        UserPOJO userPayload = new UserPOJO();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        // call the CreateUser method of UserEndpoint class
        Response response = UserEndpoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // Delete the same users by user name
    @Test(priority = 2, dataProvider = "Usernames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName) {
        Response response = UserEndpoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
