package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response CreateUser (User payload) {
		
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
		
		
		return response;
	}
	
	
	public static Response ReadUser (String userName) {
		
		Response response = given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.when()
		.get(Routes.get_url);
		
		
		return response;
	}
	
	
	public static Response UpdateUser (User payload, String userName) {
		
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		.when()
		.put(Routes.update_url);
		
		
		return response;
	}
	
	
	public static Response DeleteUser (String userName) {
		
		Response response = given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.when()
		.delete(Routes.delete_url);
		
		
		return response;
	}

}
