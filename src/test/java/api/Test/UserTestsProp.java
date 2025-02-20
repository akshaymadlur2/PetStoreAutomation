package api.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.EndPoints.UserEndPoints;
import api.EndPoints.UserEndPointsUsingProp;
import api.Payload.User;
import io.restassured.response.Response;

public class UserTestsProp {
	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
				//logs using log4j
		logger = LogManager.getLogger(this.getClass());
		
	}
	@Test(priority=1)
	public void testPostUser() {
		logger.info("*****Creating User************");
		Response response=UserEndPointsUsingProp.createUSer(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***** User is Created************");
	}
	@Test(priority=2)
	public void testgetUserByName() {
		logger.info("***** Reading User************");
		Response response =UserEndPointsUsingProp.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*****User is Displayed************");
	}
	@Test(priority=3)
	public void testUpdateUserByName() {
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		logger.info("*****Updating User************");
		Response response=UserEndPointsUsingProp.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 200);
		//checking data after update
		
		Response responseAfterUpdate =UserEndPointsUsingProp.readUser(this.userPayload.getUsername());
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		logger.info("***** User Updated************");
	}
	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("*****Deleting User************");
		Response response=UserEndPointsUsingProp.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("****** User Deleted************");
	}
}
