//import java.util.Scanner;

public class BuddyInfo {
	private String name;
	private String address;
	private String phoneNumber;
	private int age;

	public BuddyInfo() {

	}

	public BuddyInfo(String name, String address, String phoneNumber, int age) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.age = age;
	}

	public static void main(String[] args) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String greeting(String g){
		return g;
	}
	
	public String toString(){
		String newLine = System.getProperty("line.separator");
		String str = name + "$" + address + "$" + phoneNumber + newLine;
		return str;
	}
	
	public BuddyInfo Factory(String str){
		//BuddyInfo bi = new BuddyInfo();
		/*Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();*/
		String[] s = str.split("\\$");
		this.setName(s[0]);
		this.setAddress(s[1]);
		this.setPhoneNumber(s[2]);
		return this;
	}
}
