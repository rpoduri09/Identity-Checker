package IdentityChecker;

public class Person {
	private String name;
	private int age;
	private String address;
	private String email;
	private String phoneNumber;
	private String dOB;
	private String gender;
	int id;
	
	public Person(String name, int age, String address, String email, String phoneNumber, String dOB, String gender, int id) {
		this.age = age;
		this.address = address;
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.dOB = dOB;
		this.gender = gender;
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}



	public int getage() {
		return age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", dOB=" + dOB + ", id=" + id + "]";
	}

	public void setage(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getdOB() {
		return dOB;
	}

	public void setdOB(String dOB) {
		this.dOB = dOB;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
