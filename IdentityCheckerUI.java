// Name: Isaac Hu
// Email: iyhu@wisc.edu
// Team: HA
// TA: Na Li
// Lecturer: Gary Dahl
// Notes to Grader: N/A

package IdentityChecker;

import java.util.Scanner;

public class IdentityCheckerUI {
  
  public static String search(String query, Backend backend) {
    String[] queryArray = query.split(":");
    if(queryArray.length != 2)
      return "Invalid data, no matching person found in database";
    Person person = backend.findPerson(queryArray[0], queryArray[1]);
    return person == null ? "Invalid data, no matching person found in database" : 
        String.join("\n\n", "Name: " + person.getName(),
        "ID#: " + person.getId(), "Gender: " + person.getGender(),
        "Date of Birth: " + person.getdOB(), "Phone #: " + person.getPhoneNumber(),
        "Email: " + person.getEmail(), "Address: " + person.getAddress());
  }

  public static void main(String[] args) {
    
    // Initialize variables
    final String filePath = "peopleData.txt";
    String query = "";
    final Backend backend = new Backend(filePath);
    Scanner s = new Scanner(System.in);
    boolean exit = false;
    
    // Loop until the user decides to quit by entering 'exit'
    while(!exit) {
      System.out.println("Please enter the name of the person you wish to search,"
          + " or enter 'exit' to quit.");
      query = s.nextLine().toLowerCase();
      query.replace(":", "");
      
      // Exit program if user enters 'exit'
      if(query.equals("exit")) {
        System.out.println("Have a wonderful day!");
        exit = true;
        s.close();
        break;
      }
      System.out.println("Now please enter the date of birth in the format 'mm-dd-yyyy'");
      String[] date = s.nextLine().split("-");
      while(date.length != 3 || date[0].length() != 2
          || date[1].length() != 2 || date[2].length() != 4) {
        System.out.println("Invalid format. Please try using the format 'mm-dd-yyyy'");
        date = s.nextLine().split("-");
      }
      query += ":" + String.join("-", date);
      try {
        
        // Print the query results
        System.out.println(search(query, backend));
      }
      
      // NoSuchElementException, person not found
      catch(Exception e) {
        System.out.println("Data could not be retrieved: invalid data or format. Please try again.");
      }
      
    }
    
  }

}
