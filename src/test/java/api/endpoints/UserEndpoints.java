package api.endpoints;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import api.payload.UserPOJO;

//User Endpoints
//created to perform CRUD requests to the user API
//CRUD: Create, Retrieve, Update, Delete

public class UserEndpoints {
    // Create user endpoint
    public static Response createUser(UserPOJO payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

        return response;
    }

    // Read/Get user endpoint
    public static Response readUser(String userName) {
        Response response = given()
                .pathParam("username", userName)
                .when()
                .get(Routes.get_url);

        return response;
    }

    // Update user endpoint
    public static Response updateUser(String userName, UserPOJO payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(Routes.update_url);

        return response;
    }

    // Delete user endpoint
    public static Response deleteUser(String userName) {
        Response response = given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.delete_url);

        return response;
    }
}
