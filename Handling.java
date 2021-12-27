package IdentityChecker;

import java.util.ArrayList;

public class Handling {
	public static void main(String[] args){
			ArrayList<Person> people = new ArrayList<Person>();
			ArrayList<String> data = new ArrayList<String>();
			data = InputArrayList.createArrayList("peopleData.txt");
			
			for(String x: data){
				String[] person = x.split(",");
				Person p = new Person(person[0], Integer.parseInt(person[1]), person[2], person[3], person[4], person[5], person[6], Integer.parseInt(person[7]));
				people.add(p);
			}
		
		
	}
	
	
}
