import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BuddyInfoTest {
	private BuddyInfo a;
	private BuddyInfo b;
	private String g;

	@Before
	public void setUp() throws Exception {
		a = new BuddyInfo("a", "riverside", "123456", 15);
		b = new BuddyInfo("b", "riverside", "123456", 18);
		g = a.greeting("Hello!");
	}

	@Test
	public void sameConstructor() {
		Boolean testSameConstructor = false;
		if (a.getName().equals(b.getName())
				&& a.getAddress().equals(b.getAddress())
				&& a.getPhoneNumber().equals(b.getPhoneNumber())) {
			testSameConstructor = true;
		}
		assertFalse("Same Constructor", testSameConstructor);
	}
	
	@Test
	public void greeting(){
		assertEquals(a.greeting(g), "Hello!");
	}
	
	@Test
	public void isOver18(){
		Boolean age = false;
		if(a.getAge() >= 18){
			age = true;
		}
		assertFalse("Not over 18", age);
	}

}
