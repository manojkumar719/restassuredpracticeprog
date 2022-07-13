package Test.Demo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class A01_MapsRestProg {
	@Test
	public void firstProg() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParams("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"MyCountry house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, eno layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://MyCountry.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"));
		
	}
}
