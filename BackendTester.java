package IdentityChecker;

import java.io.File;

public class BackendTester {

     /**
      * tests the Backend class's various constructors.
      */
     public static boolean testConstructor() {
          try {
               File data = new File("peopleData.txt");
               Backend backendPopulated = new Backend(data);
               // backend1.saveDataToFile("savedData.txt"); //cant implement this method
               Backend backendEmpty = new Backend();
               return (backendPopulated.size() == 10 && backendEmpty.size() == 0);

          } catch (Exception e) {
               e.printStackTrace();
               return false;
          }

     }

     /**
      * tests the Backend class's .storePerson(Person person) method.
      * 
      * @returns true when this test determines correct functionality, and false
      *          otherwise.
      */
     public static boolean testStorePerson() {
          try {
               File data = new File("peopleData.txt");
               Backend backend = new Backend(data);
               boolean added;
               Person LebronJames = new Person("Lebron James", 12, "43 Main St.", "ewxwz@gmail.com", "423-322-0933",
                         "5-3-2008", "Male", 2314432);
               added = backend.storePerson(LebronJames);// duplicate entry
               return ((!added) && (backend.size() == 10));

          } catch (Exception e) {
               return false;
          }
     }

     /**
      * tests the Backend class's findPerson(name) method.
      */
     public static boolean testFindPerson() {
          try {
               File data = new File("peopleData.txt");
               Backend backend = new Backend(data);
               Person returnedPerson = backend.findPerson("thoMas JEFFerSon", "4-13-1743");

               Person expectedPerson = new Person("Thomas Jefferson", 83, "Virginia", "Tjeff@outlook.com",
                         "877-241-4213", "4-13-1743", "Male", 3453543);
               if (!returnedPerson.getName().equals("thomas jefferson"))
                    return false;
               return true;
          } catch (Exception e) {
               return false;
          }
     }

     public static void main(String[] args) {
          System.out.println("testConstructor(): " + testConstructor());
          System.out.println("testStorePerson(): " + testStorePerson());
          System.out.println("testFindPerson(): " + testFindPerson());
     }
}
