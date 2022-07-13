package just.Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGProg {
	@Test
	public void start() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver webDriver=new ChromeDriver();
		webDriver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_login_page/9a83bda125cd7398f9f482a3d6d45ea4/static/attachments/reference_page.html");
		
	       webDriver.findElement(By.id("password-input")).sendKeys("password");
	       webDriver.findElement(By.id("login-button")).click();

	       String strMsg = webDriver.findElement(By.xpath("//div[@class='validation error']")).getText();

	       Assert.assertEquals(strMsg,"Email is required");

	       webDriver.findElement(By.id("email-input")).sendKeys("unknown@codility.com");
	       webDriver.findElement(By.id("password-input")).clear();
	    
	       webDriver.findElement(By.id("login-button")).click();

	       String strMsg1 = webDriver.findElement(By.xpath("//div[@class='validation error']")).getText();

	       Assert.assertEquals(strMsg1,"Password is required");
		
	}

}
