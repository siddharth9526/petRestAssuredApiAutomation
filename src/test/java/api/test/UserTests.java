package api.test;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker = new Faker();
	User userpayloads = new User();
	public Logger logger;
	
	@BeforeClass
	public void setup() {
//		faker = new Faker();
//		userpayloads = new User();
		
		userpayloads.setId(faker.idNumber().hashCode());
		userpayloads.setUsername(faker.name().username());
		userpayloads.setFirstName(faker.name().firstName());
		userpayloads.setLastName(faker.name().lastName());
		userpayloads.setEmail(faker.internet().safeEmailAddress());
		userpayloads.setPassword(faker.internet().password(5,10));
		userpayloads.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testUser() {
		
		logger.info("********* CREATE AN USER ***********");
		Response response = UserEndPoints.CreateUser(userpayloads);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testUserByName() {
		
		logger.info("********* GET AN USER ***********");
		Response response = UserEndPoints.ReadUser(this.userpayloads.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test()
	public void updateUserByName() {
		
		logger.info("********* UPDATE AN USER ***********");
		userpayloads.setFirstName(faker.name().firstName());
		userpayloads.setLastName(faker.name().lastName());
		userpayloads.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response = UserEndPoints.UpdateUser(userpayloads, this.userpayloads.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********* GET AN UPDATED USER ***********");
		Response responseAfterUpdate = UserEndPoints.ReadUser(this.userpayloads.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}

}
