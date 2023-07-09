package api.endpoints;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import java.util.ResourceBundle;
import api.payload.UserPOJO;

//User Endpoints
//created to perform CRUD requests to the user API
//CRUD: Create, Retrieve, Update, Delete

public class UserEndpoints2 {

    // Method to get routes from properties file
    static ResourceBundle getURL() {
        // Load properties file --> no need to give file extension
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    // Create user endpoint
    public static Response createUser(UserPOJO payload) {
        // call the ResourceBundle Method to get the base URL
        String post_url = getURL().getString("post_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url); // String Variable

        return response;
    }

    // Read/Get user endpoint
    public static Response readUser(String userName) {
        // call the ResourceBundle Method to get the base URL
        String get_url = getURL().getString("get_url");

        Response response = given()
                .pathParam("username", userName)
                .when()
                .get(get_url);

        return response;
    }

    // Update user endpoint
    public static Response updateUser(String userName, UserPOJO payload) {
        // call the ResourceBundle Method to get the base URL
        String update_url = getURL().getString("update_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(update_url);

        return response;
    }

    // Delete user endpoint
    public static Response deleteUser(String userName) {
        // call the ResourceBundle Method to get the base URL
        String delete_url = getURL().getString("delete_url");

        Response response = given()
                .pathParam("username", userName)
                .when()
                .delete(delete_url);

        return response;
    }
}
