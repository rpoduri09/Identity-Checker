// --== CS400 File Header Information ==--
// Name: <Adam Schmidt>
// Email: <amschmidt9@wisc.edu>
// Team: <HA>
// TA: <Na Li>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

package IdentityChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputArrayList {

	public static ArrayList<String> createArrayList(String fileName){
		ArrayList<String> returnList = new ArrayList<String>();
		try {
			Scanner fileScanner = new Scanner(new File(fileName));
			while (fileScanner.hasNextLine()) {
				returnList.add(fileScanner.nextLine());
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {}
		return returnList;
	}
	
}
