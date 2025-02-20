package api.EndPoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//contains CRUD Operations
public class UserEndPoints {
//create operation 
	public static Response createUSer(User payload)//req body
	{
		Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
			return response;
	}
	
	public static Response readUser(String userName)//req body
	{
		Response response =given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
			return response;
	}
	
	public static Response updateUser(String userName, User payload)//req body
	{
		Response response =given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.update_url);
			return response;
	}
	
	public static Response deleteUser( String userName)//req body
	{
		Response response =given()
				
			.pathParam("username", userName)
			
		.when()
			.delete(Routes.delete_url);
			return response;
	}
	

}
	