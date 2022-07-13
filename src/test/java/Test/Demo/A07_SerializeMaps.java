package Test.Demo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.LocationLatLng;

import static io.restassured.RestAssured.*;

import java.util.Arrays;

public class A07_SerializeMaps {
	
	@Test
	public void demoSerialize() {
		AddPlace ap = new AddPlace();
		
		LocationLatLng l=new LocationLatLng();
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		ap.setLocation(l);
		ap.setAccuracy(50);
		ap.setName("MyCountry house");
		ap.setAddress("29, eno layout, cohen 09");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setTypes(Arrays.asList("shoe park","shop"));
		ap.setWebsite("http://MyCountry.com");
		ap.setLanguage("French-IN");
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String postResp = given().log().all().queryParams("key", "qaclick123").header("Content-Type","application/json").body(ap)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println(postResp);
	}

}
