import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

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
	
	public String toXML(){
		String newLine = System.getProperty("line.separator");
		String str = "<AddressBook>\n";
		for (BuddyInfo bi : addressBook.values()) {
			str += "<Buddy>" + newLine; 
			str += "<Name>" + bi.getName() + "</Name>" + newLine;
			str += "<Address>" + bi.getAddress() + "</Address>" + newLine;
			str += "<PhoneNumber>" + bi.getPhoneNumber() + "</PhoneNumber>" + newLine;
			str += "</Buddy>" + newLine;
		} 
		str += "</AddressBook>";
		return str;
	}
	
	public void ExportToXmlFile(String fileName) throws IOException {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(fileName));
			String str = this.toXML();
			out.write(str);
			out.close();
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void importFromXmlFileDOM(File f) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder d = factory.newDocumentBuilder();
		Document doc = d.parse(f);

		System.out.println("Root: " + doc.getDocumentElement().getNodeName());

		NodeList lst = doc.getDocumentElement().getChildNodes();
		for (int ii = 1; ii < lst.getLength(); ii = ii + 2) {
			BuddyInfo b = new BuddyInfo();
			Node n = lst.item(ii);
			System.out.println("Child: " + n.getNodeName() + "-->" + n.getTextContent());
			NodeList lst2 = n.getChildNodes();
			String str = "";
			for(int i = 1; i < lst2.getLength(); i = i + 2){
				Node n2 = lst2.item(i);
				str += n2.getTextContent() + "$";
				System.out.println("Child: " + n2.getNodeName() + "-->" + n2.getTextContent());
				//System.out.println(str);
			}
			System.out.println(b.Factory(str).toString());
			this.addBuddy(b.Factory(str));
		}
	}
	
	public void readSAX(File f) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser s = spf.newSAXParser();
		
		DefaultHandler dh = new DefaultHandler(){
			public void startElement(String u, String ln, String qName, Attributes a){
				System.out.println("START: " + qName);
			}
			public void endElement(String uri, String localName, String qName){
				System.out.println("END: " + qName);
			}
			public void characters(char[] ch, int start, int length){
				System.out.println("CHARS: " + new String(ch, start, length));
			}
		};
		s.parse(f, dh);
	}

	public static void main(String[] args) {
		
		File file = new File("import.xml");
		AddressBook ab = new AddressBook();
		//ab.importAddressBook("test.txt").ExportToXmlFile("export.xml");
		try {
			ab.importFromXmlFileDOM(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ab.readSAX(file);
		System.out.println(ab.toString());
		
	}
}
