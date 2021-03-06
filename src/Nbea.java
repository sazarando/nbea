import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Nbea {
	
	public static void main(String[] args) throws IOException {
		
		String oldBackupFilesFileName = "C:\\TEST\\Old.sha1.txt";
		String newBackupFilesFileName = "C:\\TEST\\New.sha1.txt";
		String oldNotInNewFileName = "C:\\TEST\\OldNotInNew.txt";
		String newNotInOldFileName = "C:\\TEST\\NewNotInOld.txt";
		
		HashMap<String, String> oldFileHmap = fileToHashMap(oldBackupFilesFileName);
		HashMap<String, String> newFileHmap = fileToHashMap(newBackupFilesFileName);
		
		
		//Look for "Old Not in New" - "Present in Old, but Missing from New"
		writeMismatchesToFile(oldNotInNewFileName, oldFileHmap, newFileHmap);
		//Look for "New Not in Old" - "Present in New, but Missing from Old"
		writeMismatchesToFile(newNotInOldFileName, newFileHmap, oldFileHmap);
		
		System.out.println("Finished!");
	}
	
	public static void writeMismatchesToFile(String filename
			, HashMap<String, String> hMap01
			, HashMap<String, String> hMap02) throws IOException {
		
		BufferedWriter bWriter = new BufferedWriter(new FileWriter(filename));
		
		
		hMap01.forEach((k,v) -> {
			if (hMap02.containsKey(k)) { //do nothing
			} else {
				try {
					bWriter.write(v); //add file name as a new line
					bWriter.append("\r\n");
				}
				catch(IOException e) {System.out.println(e.toString());}
			}
		}); 
		
		bWriter.close();
	}
	
	public static HashMap<String, String> fileToHashMap(String filename) throws IOException {
		
		BufferedReader bReader = new BufferedReader(new FileReader(filename));
		HashMap<String, String> hMap = new HashMap<String, String>(); 
		
		String line = bReader.readLine();
		StringBuffer keySHA = new StringBuffer();
		StringBuffer valueFileName = new StringBuffer();

		
		while (line != null) { //read file in line-by-line and store data in HashMap
			keySHA.delete(0, keySHA.length()); //reset variables
			valueFileName.delete(0, valueFileName.length());
			
			//No malformed-input checking
			keySHA.append(line.substring(0, line.indexOf(' '))); //grab first 40 chars - (up to index of first space char)
			valueFileName.append(line.substring(line.indexOf(' ')+1)); //grab all chars after first space char
			
			hMap.put(keySHA.toString(), valueFileName.toString()); //add SHA and File Name as Key, Value in HashMap
			
			line = bReader.readLine();
		}
		
		bReader.close();
		return hMap;
	}
}

