package Test.Demo;

import org.testng.Assert;
import org.testng.annotations.Test;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class A02_PostPutGetMethods {

	@Test
	public void endToEndFlow() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
//		Post -> Adding a new place using POST
		System.out.println("==========================POST Operation==========================");
		String response = given().log().all().queryParam("key", "qaclick123").body(Payload.addPlace()).headers("Content-Type","Application/json" )
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		System.out.println("------------------------------------------ Response is ------------------------------------------");
		System.out.println(response);
		
		JsonPath jp = new JsonPath(response);
		String placeId = jp.get("place_id");
		
		System.out.println("Place Id =>"+placeId);
		
//		Put -> Updating Address of the place using PUT
		System.out.println("==========================PUT Operation==========================");
		String newAddress = "Just Summer walk, INDIA";
		given().queryParam("key","qaclick123").headers("Content-Type","application/json").body(Payload.updatePlace(placeId,newAddress)).log().all()
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).log().all().body("msg",equalTo("Address successfully updated"));
		
//		Get -> Retrieving place using GET
		System.out.println("==========================GET Operation==========================");
		String getResponse = given().queryParam("key","qaclick123").queryParam("place_id",placeId).log().all()
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(getResponse);
		String actualAddress = js.get("address");
		Assert.assertEquals(newAddress, actualAddress,"Expected and Actual address is not matching");
	}
}
