package Test.Demo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class A04_DynamicJson {
	
	@Test(dataProvider = "BookData")
	public void book(String isbn, int aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String resp = given().log().all().header("content-type","application/json").body(Payload.addBook(isbn, aisle)).baseUri("http://216.10.245.166")
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath jp = new JsonPath(resp);
		String respId = jp.getString("ID");
		System.out.println(respId);
		Assert.assertEquals(respId, isbn+aisle);
		
		given().log().all().header("content-type","application/json").queryParam("AuthorName", "John foe").queryParam("ID", respId)
		.when().get("Library/GetBook.php")
		.then().assertThat().statusCode(200).log().all();
		
		given().log().all().header("content-type","application/json").queryParam("ID", respId)
		.when().get("Library/GetBook.php")
		.then().assertThat().statusCode(200).log().all();
		
		
		given().log().all().queryParam("ID", respId).header("content-type","application/json")
		.when().post("Library/DeleteBook.php")
		.then().log().all();
		
	}
	
	@DataProvider(name = "BookData")
	public Object[][] getData(){
		return new Object[][] {{"isbn", 1010},{"isbn", 1011},{"isbn", 1012},{"isbn", 1013},{"isbn", 1014},{"isbn", 1015}};
	}

}
