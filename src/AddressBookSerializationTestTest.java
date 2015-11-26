import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class AddressBookSerializationTestTest {
	BuddyInfo a = null;
	BuddyInfo b = null;
	AddressBook aBook = null;
	AddressBook bBook = null;

	@Before
	public void setUp() throws Exception {
		a = new BuddyInfo("a", "riverside", "123456", 15);
		b = new BuddyInfo("b", "riverside", "123456", 18);
		aBook = new AddressBook();
		bBook = new AddressBook();
		aBook.addBuddy(a);
		aBook.addBuddy(b);
	}

	@Test
	public void test() throws IOException, ClassNotFoundException {
		aBook.writeObjects();
		bBook.readObjects();
		System.out.println(aBook.toString());
		assertEquals(aBook.toString(), bBook.toString());
	}

}
