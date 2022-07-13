package Test.Demo;

import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class A03_ComplexJSONParse {

	/* ****************** TASKS ******************
	 *  1.  Print number of courses returned by API
	 *  2. Print Purchase Amount
	 *  3. Print Title of the first course
	 *  4. Print All course titles and their respective prices
	 *  5.  Print number of copies sold by RPA course
	 *  6. Verify that sum of courses and purchase amount matches
	 */
	@Test
	public void coursePrice() {
		JsonPath jp = new JsonPath(Payload.coursePrice());
		
//		//-------------------------------------------------------------My Code-------------------------------------------------------------
//		System.out.println("1.  Print number of courses returned by API :  "+jp.getList("courses.title").size());
//		System.out.println("2. Print Purchase Amount : "+jp.get("dashboard.purchaseamount"));
//		System.out.println("3. Print Title of the first course : "+jp.get("courses[0].title"));
//		System.out.println("4. Print All course titles and their respective prices : ");
//		for(int i=0;i<jp.getList("courses.title").size();i++)
//			System.out.println(jp.get("courses["+i+"].title")+" -> "+jp.get("courses["+i+"].price"));
//		System.out.println("5.  Print number of copies sold by RPA course : ");
//		for(int i=0;i<jp.getList("courses.title").size();i++)
//			if(jp.get("courses["+i+"].title").equals("RPA"))
//				System.out.print(jp.get("courses["+i+"].copies")+"\n");
//		int sum = 0;
//		for(int i=0;i<jp.getList("courses.title").size();i++)
//			sum+= Integer.parseInt(jp.get("courses["+i+"].price").toString())*Integer.parseInt(jp.get("courses["+i+"].copies").toString());
//		boolean matchAmt= false;
//		if(Integer.parseInt(jp.get("dashboard.purchaseamount").toString()) == sum)
//			matchAmt = true;
//		System.out.println("6. Verify that sum of courses and purchase amount matches : "+ matchAmt);
//		//-------------------------------------------------------------Rahul's code-------------------------------------------------------------
				System.out.println("1.  Print number of courses returned by API :  "+jp.getInt("courses.size()"));
				System.out.println("2. Print Purchase Amount : "+jp.getInt("dashboard.purchaseamount"));
				System.out.println("3. Print Title of the first course : "+jp.getString("courses[0].title"));
				System.out.println("4. Print All course titles and their respective prices : ");
				System.out.println("4. Print All course titles and their respective prices : ");
				for(int i=0;i<jp.getList("courses.title").size();i++)
					System.out.println(jp.get("courses["+i+"].title")+" -> "+jp.get("courses["+i+"].price"));
				System.out.println("5.  Print number of copies sold by RPA course : ");
				for(int i=0;i<jp.getList("courses.title").size();i++)
					if(jp.get("courses["+i+"].title").equals("RPA")) {
						System.out.print(jp.get("courses["+i+"].copies")+"\n");
						break;
					}
				int sum = 0;
				for(int i=0;i<jp.getList("courses.title").size();i++)
					sum+= jp.getInt("courses["+i+"].price")*jp.getInt("courses["+i+"].copies");
				boolean matchAmt= false;
				if(jp.getInt("dashboard.purchaseamount") == sum)
					matchAmt = true;
				System.out.println("6. Verify that sum of courses and purchase amount matches : "+ matchAmt);
	}
}
