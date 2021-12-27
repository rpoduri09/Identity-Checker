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
