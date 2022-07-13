package Test.Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourses;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.List;

public class A06_OAuth2_Deserialize {
	
	@Test
	public void oAuthDemo() throws Exception {
		/*
		 * OAuth 2.0
		 * 1. Get the Authorization code (in this case, paste the link in the )
		 * 2. Get the Access token using the Authorization code
		 * 3. Perform requsts using Authentication token
		 */
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.manage().window().maximize();
		driver.findElement(By.id("identifierId")).sendKeys("manojautomation78"+Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("AutoManoj@123"+Keys.ENTER);
		Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		String code = url.split("code=")[1].split("&")[0];
		System.out.println(code);
		driver.close();
		
		String accessTokenResp = given().urlEncodingEnabled(false)
				.queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code")
				.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath jp2 = new JsonPath(accessTokenResp);
		String accessToken = jp2.getString("access_token");
		
		GetCourses gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		
		System.out.println(gc.getInstructor()+"->"+gc.getLinkedIn());
//		print All courses from WebAutomation
		List<WebAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
		for(WebAutomation course:webAutomationCourses)
			if(Integer.parseInt(course.getPrice())>40)
				System.out.println(course.getCourseTitle()+" ---price---> "+course.getPrice());
	}

}
