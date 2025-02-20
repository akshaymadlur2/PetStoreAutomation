package api.EndPoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//contains CRUD Operations
public class UserEndPointsUsingProp {
	
	//Load properties file and read data
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes.properties");
		return routes;
	}
	
	
//create operation 
	public static Response createUSer(User payload)//req body
	{
		String post_url=getURL().getString("post_url");
		Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
			return response;
	}
	
	public static Response readUser(String userName)//req body
	{
		String get_url=getURL().getString("get_url");
		Response response =given()
			.pathParam("username", userName)
		.when()
			.get(get_url);
			return response;
	}
	
	public static Response updateUser(String userName, User payload)//req body
	{
		String update_url=getURL().getString("update_url");
		Response response =given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(update_url);
			return response;
	}
	
	public static Response deleteUser( String userName)//req body
	{
		String delete_url=getURL().getString("delete_url");
		Response response =given()
				
			.pathParam("username", userName)
			
		.when()
			.delete(delete_url);
			return response;
	}
	

}
	