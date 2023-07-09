package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints2;
import api.payload.UserPOJO;
import io.restassured.response.Response;

public class UserTests2 {
    // create Faker & UserPOJO Object
    Faker faker;
    UserPOJO userPayload;

    public Logger logger; // for logs

    // Generate random User Data for User Payload-body
    @BeforeClass
    public void setUp() {
        faker = new Faker();
        userPayload = new UserPOJO();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // logs
        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("******* Creating user  ********");
        // call the CreateUser method of UserEndpoint class
        Response response = UserEndpoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******* User is Created  ********");
    }

    @Test(priority = 2)
    public void testGetUser() {
        logger.info("******* Retrieving the user  ********");
        // call the GetUser method of UserEndpoint class
        Response response = UserEndpoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******* User information is displayed ********");
    }

    @Test(priority = 3)
    public void testUpdateUser() {
        logger.info("******* Updating the user  ********");
        // update data using payload --> regenerate first name, last name & email
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        // call the UpdateUser method of UserEndpoint class
        Response response = UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******* User is updated  ********");

        // call the GetUser method of UserEndpoint class after the update
        Response responseAfterUpdate = UserEndpoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        logger.info("******* Deleting user  ********");
        // call the DeleteUser method of UserEndpoint class
        Response response = UserEndpoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******* User Deleted ********");
    }
}
