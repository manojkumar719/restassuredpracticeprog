package Test.Demo;

import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.List;

public class A05_JiraApiAutomation {

	@Test
	public void jiraDemo() {
		RestAssured.baseURI = "http://localhost:8080";
//		Logging into Jira
//		String cookieResp = given().log().all().header("content-type","application/json").body("{ \"username\": \"lordofhumans.manoj\", \"password\": \"M@N0j@123\" }")
//		.when().post("rest/auth/1/session")
//		.then().log().all().assertThat().statusCode(200).extract().response().asString();
//		
//		JsonPath jp = new JsonPath(cookieResp);
//		String cookie = jp.getString("session.name")+"="+jp.getString("session.value");
//		
//		System.out.println(cookie);									//Instead of this,  another way for session storing
		
//		Logging into Jira
		SessionFilter session = new SessionFilter();
		given().header("content-type","application/json").log().all().body("{ \"username\": \"lordofhumans.manoj\", \"password\": \"M@N0j@123\" }").filter(session)
				.when().post("rest/auth/1/session")
				.then().log().all().assertThat().statusCode(200);
		
//		Creating Defect
		String defectResp = given().log().all().header("content-type","application/json").body(Payload.createJiraDefect()).filter(session)
				.when().post("rest/api/2/issue")
				.then().assertThat().statusCode(201).log().all().extract().response().asString();
		
		JsonPath jp = new JsonPath(defectResp);
		String bugId = jp.getString("id");
		
//		Adding comment to ticket
		String cmtResp = given().pathParam("issueId", bugId).log().all().header("content-type","application/json").filter(session).body("{\"body\": \"Comment by Automation\"}")
				.when().post("/rest/api/2/issue/{issueId}/comment")
				.then().log().all().extract().response().asString();
		
		JsonPath jp2 = new JsonPath(cmtResp);
		String cmtId = jp2.getString("id");
		
//		Adding attachment
		String attachmentResp = given().pathParam("issueId", bugId).log().all().header("X-Atlassian-Token","no-check").filter(session).multiPart("file",new File("./src/test\\java\\files\\testAttachment.txt")).header("content-type","multipart/form-data")
				.when().post("/rest/api/2/issue/{issueId}/attachments")
				.then().log().all().extract().response().asString();
		
		JsonPath jp3 = new JsonPath(attachmentResp);
		String attId = jp3.getString("[0].id");
		
//		Get Issue details
		String getIssueResp = given().log().all().filter(session).pathParam("issueId", bugId)
		.when().get("/rest/api/2/issue/{issueId}")
		.then().log().all().extract().response().asString();
		System.out.println(getIssueResp);
		
		JsonPath jp4 = new JsonPath(getIssueResp);
		List l = jp4.getList("fields.comment.comments.id");
		
		boolean cmtPresent = false, attPresent = false;
		for(int i=0;i<l.size();i++)
			if(jp4.getString("fields.comment.comments["+i+"].id").equals(cmtId))
			{
				cmtPresent = true;
				break;
			}
		List l2 = jp4.getList("fields.attachment.id");
		for(int i=0;i<l2.size();i++)
			if(jp4.getString("fields.attachment["+i+"].id").equals(attId))
			{
				attPresent = true;
				break;
			}
		if(cmtPresent)
			System.out.println("Comment present");
		else
			System.out.println("Comment not present");
		
		if(attPresent)
			System.out.println("Attachment present");
		else
			System.out.println("Attachment not present");
	
	}
}
