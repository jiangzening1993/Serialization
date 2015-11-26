import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddressBookIOTest {
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
		aBook.export("test.txt");
		bBook.importAddressBook("test.txt");
	}

	@Test
	public void test() {
		assertEquals(aBook.toString(), bBook.toString());
	}

}
