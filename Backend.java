package IdentityChecker;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Backend class used to store Person records.
 */
public class Backend {
     private HashTableMap<String, Person> table;// key:value => name:Person

     /**
      * Constructor of Backend class.
      * 
      * @param filePath The path of the .txt file the backend will draw records from.
      */
     public Backend(String filePath) {
          this(new File(filePath));
     }

     /**
      * Constructor of Backend class. The passed file will be used to populate the
      * backend data.
      * 
      * @param file The file the backend will draw records from.
      */
     public Backend(File file) {
          table = new HashTableMap<String, Person>();
          ArrayList<Person> people = new ArrayList<Person>();
          // will stores each line of the specified file as a new String
          ArrayList<String> data = new ArrayList<String>();

          // parse data in file
          data = InputArrayList.createArrayList(file.getPath());

          for (String x : data) {
               String[] person = x.split(",");
               Person p = new Person(person[0].toLowerCase(), Integer.parseInt(person[1]), person[2], person[3], person[4], person[5],
                         person[6], Integer.parseInt(person[7]));
               // creates Person object from parsed data
               people.add(p);
          }

          // add each person to the hash table
          for (Person person : people) {
               storePerson(person);
          }
     }

     /**
      * Constructor of Backend class. No Persons will be populated.
      * 
      * @param file The file the backend will draw records from.
      */
     public Backend() {
          this(new File("newFile.txt"));
     }

     /**
      * Method that returns the size of the Backend.
      * 
      * @returns size of Backend's hashtable.
      */
     public int size() {
          return table.size();
     }

     /**
      * stores person in backend. If the Person object is already in the hash table
      * (or hashcode + id# match), they will not be added and the method will return
      * false.
      * 
      * @param person the person to be added to the Backend.
      * @returns true when the Person is added to the backend, and false otherwise.
      */
     public boolean storePerson(Person person) {
          String key = person.getName().toLowerCase() + person.getdOB().toLowerCase();
          return table.put(key, person);
     }

     /**
      * retrieves an array of all Person objects with the specified name + dob. This
      * is a good way to handle duplicate names (duplicate hash codes).
      * 
      * @param name the name of the person
      * @param dob  the date of birth of the person (M-D-YYYY).
      * @returns the Person stored in backend with the specified name.
      * @throws NoSuchElementException When no one by that name is in the table.
      */
     public Person findPerson(String name, String dob) throws NoSuchElementException {
          return (table.get(name.toLowerCase() + dob.toLowerCase()));
     }

}
