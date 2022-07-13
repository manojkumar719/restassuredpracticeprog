package Test.Demo;

import java.util.Arrays;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import pojo.AddPlace;
import pojo.LocationLatLng;

public class A08_Req_Res_SpecBuilder {
	
	@Test
	public void demoSecificationBuilder() {
	
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
		
		RequestSpecBuilder reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")																		//Using SpecBuilder instead of RequestSpecification for demo
				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123");
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();						//ResponseSpecification is used
		
		AddPlace ap2 =	given().log().all().spec(reqSpec.build()).body(ap)														//build() converts reqspecbuilder to reqspecification
		.when().post("/maps/api/place/add/json")
		.then().log().all().spec(resSpec).extract().as(AddPlace.class);
		
		System.out.println(ap2.getPlace_id());
				

	}
}
