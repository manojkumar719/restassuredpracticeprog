package files;

public class Payload {
	public static String addPlace() {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String updatePlace(String placeId, String newAddress) {
		return "{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String coursePrice() {
		return "{\r\n"
				+ "	\"dashboard\":{\r\n"
				+ "		\"purchaseamount\":910,\r\n"
				+ "		\"website\":\"abc.com\"\r\n"
				+ "	},\r\n"
				+ "	\"courses\":[\r\n"
				+ "	{\r\n"
				+ "		\"title\":\"Selenium python\",\r\n"
				+ "		\"price\":50,\r\n"
				+ "		\"copies\":6\r\n"
				+ "	},\r\n"
				+ "	{\r\n"
				+ "		\"title\":\"Cypress\",\r\n"
				+ "		\"price\":40,\r\n"
				+ "		\"copies\":4\r\n"
				+ "	},\r\n"
				+ "	{\r\n"
				+ "		\"title\":\"RPA\",\r\n"
				+ "		\"price\":45,\r\n"
				+ "		\"copies\":10\r\n"
				+ "	}\r\n"
				+ "	]\r\n"
				+ "}";
	}
	
	public static String addBook(String isbn, int aisle) {
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String createJiraDefect() {
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"RES\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"RestAssured API Defect\",\r\n"
				+ "        \"description\": \"Defect raised though API Automation \",\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}";
	}
	

}
