package api.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.EndPoints.UserEndPoints;
import api.Payload.User;
import api.Utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	//@Test(priority=1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fname,String lname, String userEmail,String pwd, String phone) {
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(userEmail);
		userPayload.setPhone(phone);
		userPayload.setPassword(pwd);
		
		Response response=UserEndPoints.createUSer(userPayload);
	
		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test(priority=2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) {
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
