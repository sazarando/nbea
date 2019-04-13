import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Samples {

	public void fileCompareSample() throws IOException {
		BufferedReader reader1 = new BufferedReader(new FileReader("C:\\TEST\\file01.txt"));
		BufferedReader reader2 = new BufferedReader(new FileReader("C:\\TEST\\file02.txt"));
		
		String line1 = reader1.readLine();	
		String line2 = reader2.readLine();
	
		boolean areEqual = true;
		int lineNum = 1;
		
		while (line1 != null || line2 != null) {
			if(line1 == null || line2 == null) {
				areEqual = false;
				break;
			}
			else if(! line1.equalsIgnoreCase(line2)) {
				areEqual = false;
				break;
			}
			
			line1 = reader1.readLine();
			line2 = reader2.readLine();
			lineNum++;
		}
		
		if(areEqual) {
			System.out.println("Two files have same content."); 
		}
		else {
			System.out.println("Two files have different content. They differ at line "+lineNum);
			System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
		}
		
		reader1.close();		
		reader2.close();
	}
	
	public void checkFiles() {
		 Map<String, String> userCityMapping = new HashMap<>();

	        // Check if a HashMap is empty
	        System.out.println("is userCityMapping empty? : " + userCityMapping.isEmpty());

	        userCityMapping.put("John", "New York");
	        userCityMapping.put("Rajeev", "Bengaluru");
	        userCityMapping.put("Steve", "London");

	        System.out.println("userCityMapping HashMap : " + userCityMapping);

	        // Find the size of a HashMap
	        System.out.println("We have the city information of " + userCityMapping.size() + " users");

	        String userName = "Steve";
	        // Check if a key exists in the HashMap
	        if(userCityMapping.containsKey(userName)) {
	            // Get the value assigned to a given key in the HashMap
	            String city = userCityMapping.get(userName);
	            System.out.println(userName + " lives in " + city);
	        } else {
	            System.out.println("City details not found for user " + userName);
	        }

	        // Check if a value exists in a HashMap
	        if(userCityMapping.containsValue("New York")) {
	            System.out.println("There is a user in the userCityMapping who lives in New York");
	        } else {
	            System.out.println("There is no user in the userCityMapping who lives in New York");
	        }


	        // Modify the value assigned to an existing key
	        userCityMapping.put(userName, "California");
	        System.out.println(userName + " moved to a new city " + userCityMapping.get(userName) + ", New userCityMapping : " + userCityMapping);

	        // The get() method returns `null` if the specified key was not found in the HashMap
	        System.out.println("Lisa's city : " + userCityMapping.get("Lisa"));
	}
}
