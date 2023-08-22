package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	

	static ResourceBundle getURL() {
		ResourceBundle resource = ResourceBundle.getBundle("routes");
		return resource;
	}
	
public static Response CreateUser (User payload) {
	
	    String post_url = getURL().getString("post_url");
		
		Response response = 
		 given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_url);
		
		
		return response;
	}
	
	
	public static Response ReadUser (String userName) {
		
		String get_url = getURL().getString("get_url");
		
		Response response = given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.when()
		.get(get_url);
		
		
		return response;
	}
	
	
	public static Response UpdateUser (User payload, String userName) {
		
		String update_url = getURL().getString("update_url");
		
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		.when()
		.put(update_url);
		
		
		return response;
	}
	
	
	public static Response DeleteUser (String userName) {
		
		String delete_url = getURL().getString("delete_url");
		
		Response response = given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.when()
		.delete(delete_url);
		
		
		return response;
	}

}




