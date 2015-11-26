import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class AddressBook implements Serializable {

	private Map<String, BuddyInfo> addressBook;

	public AddressBook() {
		addressBook = new HashMap<String, BuddyInfo>();
	}

	public void addBuddy(BuddyInfo buddy) {
		addressBook.put(buddy.getName(), buddy);
	}

	public void removeBuddy(BuddyInfo buddy) {
		addressBook.remove(buddy);
	}

	public int size() {
		return addressBook.size();
	}

	public void clear() {
		addressBook.clear();
	}

	public void export(String fileName) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(fileName));
			String str = "";
			for (String key : addressBook.keySet()) {
				str += addressBook.get(key).toString();
			}
			out.write(str);
			out.close();
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public AddressBook importAddressBook(String fileName) {
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(fileName));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				BuddyInfo bi = new BuddyInfo().Factory(line);
				this.addBuddy(bi);
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			System.out.println(everything);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	public String toString() {
		String str = "";
		for (BuddyInfo bi : addressBook.values()) {
			str += bi.toString();
		}
		return str;
	}

	public void writeObjects() throws IOException {
		FileOutputStream fos = new FileOutputStream("serizlizationTest.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(this.toString());

		oos.close();
	}

	public void readObjects() throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream("serizlizationTest.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);

		String str = (String)ois.readObject();
		String[] split = str.split(System.getProperty("line.separator"));
		for(int i = 0; i < split.length; i++){
			this.addBuddy(new BuddyInfo().Factory(split[i]));
		}
		ois.close();
		
	}

	public static void main(String[] args) {
		/*
		 * BuddyInfo bi1 = new BuddyInfo(); bi1.setAddress("Carleton");
		 * bi1.setName("Tom"); bi1.setPhoneNumber("613"); BuddyInfo bi2 = new
		 * BuddyInfo(); bi2.setAddress("111 Fake Street");
		 * bi2.setName("Mr.Buddy"); bi2.setPhoneNumber("613-555-5555");
		 * AddressBook ab = new AddressBook(); ab.addBuddy(bi1);
		 * ab.addBuddy(bi2); ab.export(); // ab.removeBuddy(bi); BuddyInfo bi3 =
		 * bi1.Factory(); ab.addBuddy(bi3); ab.export();
		 */
		// AddressBook ab = new
		// AddressBook().importAddressBook("AddressBook.txt");
		// ab.export("AddressBook.txt");
	}
}
