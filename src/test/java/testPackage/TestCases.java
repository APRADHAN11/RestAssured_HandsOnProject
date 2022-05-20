package testPackage;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;






public class TestCases {
	
	@Test(priority=1)
	public void Post() throws IOException {
		
//Calling JSON file		
		FileInputStream fs= new FileInputStream(new File(System.getProperty("user.dir")+"\\data\\Post.json"));
		
		Response res=BaseClass.BaseClassMethod(IOUtils.toString(fs),"POST","/user","");

//performing assertation for successful post method
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		Assert.assertEquals(res.jsonPath().getInt("code"), 200);
		
	}
	
	
	@Test(priority=2)
	public void Get() throws IOException {
		
//performing assertation for successful get method
		
		Response res=BaseClass.BaseClassMethod("APradhan","GET","/user","");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
		
	}
	
	@Test(priority=3)
	public void Put() throws IOException {
		
		FileInputStream fs= new FileInputStream(new File(System.getProperty("user.dir")+"\\data\\Put.json"));

//performing assertation for successful put method
		
		Response res=BaseClass.BaseClassMethod(IOUtils.toString(fs),"PUT","/user","APradhan");

		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
		Assert.assertEquals(res.jsonPath().getInt("code"), 200);
		
	}
	
	@Test(priority=4)
	public void Delete() throws IOException {
	
//performing assertation for successful delete method
	
		Response res=BaseClass.BaseClassMethod("","DELETE","/user","APradhan");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
		Assert.assertEquals(res.jsonPath().getInt("code"), 200);
		
	}
	
	@Test(priority=5)
    public void DeleteNegativeScenario() throws IOException {
		
//performing assertation for unsuccessful delete method

		Response res=BaseClass.BaseClassMethod("","DELETE","/user","Anything");
		
		Assert.assertEquals(res.getStatusCode(), 404);
		
	}
	@Test(priority=6)
	public void PostMethodUsingStringBody(){//4.Perform the POST Operation with escape char , perform assertions for success code
		RestAssured.baseURI= "https://petstore.swagger.io/v2";
		String body="{\"username\":\"APradhan\",\"firstName\":\"avinash\",\"lastName\":\"pradhan\",\"email\":\"apradhan@gmail.com\",\"password\":\"123456\",\"phone\":\"1234567891\",\"userStatus\":0}";

//performing assertation for successful code test for post method

		Response resp=given().contentType(ContentType.JSON)
		.body(body).
		when()
		.post("/user");
		System.out.println(resp.asString());
		assertEquals(resp.getStatusCode(),200);
	
	}
	@Test(priority=7)
	public void PutMethodUsingStringBody(){//6. Perform the PUT Operation with escape char, perform assertions for success code
		RestAssured.baseURI= "https://petstore.swagger.io/v2";
		String body="{\"username\":\"APradhan\",\"firstName\":\"avinash\",\"lastName\":\"pradhan\",\"email\":\"apradhan@gmail.com\",\"password\":\"123456\",\"phone\":\"1234567891\",\"userStatus\":0}";

//performing assertation for successful code test for put method

		Response resp=given().contentType(ContentType.JSON)
		.body(body).
		when()
		.put("/user/APradhan");
		System.out.println(resp.asString());
		assertEquals(resp.getStatusCode(),200);
	}
	
}