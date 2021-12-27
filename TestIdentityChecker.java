import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
public class TestIdentityChecker {
	/**
	 * This method is a test method for the backend code and the data wranglers code. The data wranglers
	 * implemented the scanner that reads the testing.txt file and runs the storePerson method for each
	 * line. Each line contains all the data for the person. The backend code is responsible for
	 * hashing all the people in the txt file into the database. This test method checks to make
	 * sure that the number of people in the database is correct after the backend object is 
	 * declared. When it is first declared, there should be 10 people in the database since there
	 * are 10 lines in the testing.txt file. It also checks the HashTableMap class to make sure that 
	 * duplicate people are dealt with properly and that the capacity of the HashTableMap array is 
	 * being updated when it becomes 80% full. 
	 * @return true if the all the tests pass and false otherwise
	 */
	public static boolean dataWranglerNBackEndTest1() {
		// Creating Backend class should add all data in testing.txt into database
		Backend x1 = new Backend("peopleData.txt");
		if (x1.size() != 10) {
			System.out.println("backendTest1 failed the size test. Size should be 10, "
					+ "but returned " + x1.size());
			return false;
		}
		Person newPerson = new Person("John Doe", 17 , "100 State Street" , "wirhc@wisc.edu" , 
				"472-128-3917" , "11-12-2003" , "Male" , 123213219);
		x1.storePerson(newPerson);
		if (x1.size() != 11) {
			System.out.println("backendTest1 failed with size test. After adding a "
					+ "new person with the same name and different birthday, size should increase "
					+ "to 11, but instead the size is " + x1.size());
			return false;
		}
		Person newPerson2 = new Person("David Beckham", 45 , "43 State Street" , "fjkah@yahoo.com" , 
				"573-673-3183" , "5-2-1975" , "Male" , 123214219);
		Person newPerson3 = new Person("David Beckham", 45 , "12 State Street" , "notDB@yahoo.com" , 
				"523-613-3313" , "5-2-1975" ,"Male", 123214220);
		x1.storePerson(newPerson2);
		x1.storePerson(newPerson3);
		if (x1.size() != 12) {
			System.out.println("backendTest1 failed with size test. After adding a duplicate person,"
					+ "the size should have remained the same. However, the size is " + x1.size());
		}
		return true;
		
	}
	
	/**
	 * This test method checks to make sure the findPerson and storePerson are correctly
	 * implemented with the HashTableMap. It checks to see if the data is reachable for people
	 * who are already in the array and also checks to see if the correct error message is thrown 
	 * when the user tries to search for a person who is not in the database. 
	 * @return true if all the test are correct and false otherwise
	 */
	public static boolean backendTest2() {
		Backend x1 = new Backend("peopleData.txt");
		Person newPerson = new Person("John Doe", 17 , "100 State Street" , "wirhc@wisc.edu" , 
				"472-128-3917" , "11-12-2003" , "Male", 123213219);
		x1.storePerson(newPerson);
		if (!x1.findPerson("John Doe", "11-12-2003").equals(newPerson)) {
			System.out.println("A person who should have been in the database was not found.");
			return false;
		}
		Person newPerson2 = new Person("David Beckham", 45 , "43 State Street" , "fjkah@yahoo.com" , 
				"573-673-3183" , "5-2-1975" , "Male", 123214219);
		try {
			Person test = x1.findPerson("David Beckham", "5-2-1975");
		}
		catch (NoSuchElementException e1) {
			return true;
		}
		System.out.println("The method 'findPerson()' was called on someone who is not in the "
				+ "database. A NoSuchElementException was expected but was not received.");
		return false;
	}
	
	/**
	 * This method is a test method that checks to make sure that the front end IdentityCheckerGUI
	 * is working. It checks to make sure the correct responses are sent given the different
	 * scenarios that could happen. Specifically, it tests to make sure that the search method 
	 * returns the correct person in the database and the correct information for that person. It also
	 * checks whether the person actually exists in the database. 
	 * @return true if all the tests are correct and false otherwise.
	 */
	public static boolean frontendTest1() {
		Backend x1 = new Backend("peopleData.txt");
		IdentityCheckerGUI IC = new IdentityCheckerGUI();
		if (!IC.search("Bob Builder:8-3-2020", x1).contains("ID#: 9876584")) {
			System.out.println("The method search() was called on someone who is in the database."
					+ "The person's information should have been printed containing the phrase: "
					+ "'ID#: 987584'. However, this was not the case.");
			return false;
		}
		try {
			String s = IC.search("Fake Person:3-2-2020", x1);
		}
		catch (NoSuchElementException e1) {
			return true;
		}
		System.out.println("The method search() was called on someone who is not in the "
				+ "database. A NoSuchElementException was expected but was not received.");
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("dataWranglerNBackEndTest1: " + dataWranglerNBackEndTest1());
		System.out.println("backendTest2: " + backendTest2());
		System.out.println("frontendTest1: " + frontendTest1());
	}
}
