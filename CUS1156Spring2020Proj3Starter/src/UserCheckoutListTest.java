import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserCheckoutListTest {
	UserCheckoutList list;
	UserCheckoutList list2;
	StandardBook book;
	NYTimesBook book2;
	static final String CALL_NUM = "333";
	static final String CALL_NUM2 = "9780812984965";
	static final String TITLE = "Captain Underpants";
	static final String TITLE2 = "JUST MERCY";
	static final String USER_NAME = "Joe";

	@Before
	public void setUp() throws Exception {
		list = new UserCheckoutList(USER_NAME);
		list2 = new UserCheckoutList(USER_NAME);
		book = new StandardBook(TITLE, CALL_NUM);
		book2 = new NYTimesBook(TITLE2, CALL_NUM2);
	}

	@Test
	public void testGetUser() {
		assertEquals(USER_NAME, list.getUser());
		assertEquals(USER_NAME, list2.getUser());
	}

	@Test
	public void testAddBook() {
		list.addBook(book);
		Book b = list.findByCallNumber(CALL_NUM);
		assertEquals(CALL_NUM, b.getCallNumber());
		assertEquals(TITLE, b.getTitle());
		assertNull(b.getDueDate());  //add book does not set the due date
	}

	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
		list.addBook(book);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testFindByCallNumber() {
		list.addBook(book);
		Book b = list.findByCallNumber(CALL_NUM);
		assertEquals(CALL_NUM, b.getCallNumber());
		assertEquals(TITLE, b.getTitle());

	}

	@Test
	public void testRemoveBook() {
		list.addBook(book);
		assertTrue(list.removeBook(CALL_NUM));
		assertTrue(list.isEmpty());

	}

	@Test
	public void testRemoveBookNotCheckedOut() {
		list.addBook(book);
		assertFalse(list.removeBook("444"));
		assertFalse(list.isEmpty());

	}

}
