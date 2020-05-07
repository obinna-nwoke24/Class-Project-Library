



import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

public class CheckoutSystemTest {
    CheckoutSystem system;
    CheckoutSystem system2;
    StandardBook book;
    BookCollection books;
    BookCollection books2;
    static final String FILEPATH1="books.txt";
    static final String TYPE1 = "standard";
    static final String FILEPATH2 = "nonfiction.json";
    static final String TYPE2 = "NYTimes";
    static final String CALL_NUM = "452";
    static final String CALL_NUM2 = "9780525563488";
    static final String TITLE = "Captain Underpants";
    static final String USER_NAME = "Joe";
    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

	@Before
	public void setUp() throws Exception {
		system = new CheckoutSystem(LocalDate.now());
		system2 = new CheckoutSystem(LocalDate.now());
		book = new StandardBook(TITLE, CALL_NUM);
		books = BookCollectionFactory.getBookCollection(TYPE1);
		books2 = BookCollectionFactory.getBookCollection(TYPE2);
	}
	
	@Test
	public void testCreateUser() {
		system.createUser(USER_NAME);
		UserCheckoutList list = system.find(USER_NAME);
		assertNotNull(list);
	}

	@Test
	public void testCheckout() throws FileNotFoundException {
		// testing for standard book type
		system.load(TYPE1, FILEPATH1);
		system.createUser(USER_NAME);
		BookTransactionStatus status = system.checkout(CALL_NUM, USER_NAME);
		assertEquals(BookTransactionStatus.SUCCESS, status);
		BookTransactionStatus status2 = system.checkout("333", USER_NAME);
		assertEquals(BookTransactionStatus.SUCCESS, status2);
		
		// testing for NYTimes book type
		system2.load(TYPE2, FILEPATH2);
		system2.createUser(USER_NAME);
		BookTransactionStatus status3 = system2.checkout(CALL_NUM2, USER_NAME);
		assertEquals(BookTransactionStatus.SUCCESS, status3);
	}
	
	
	@Test
	public void testReturnBook() {
		//testing for standard book type
		system.load(TYPE1, FILEPATH1);
		system.createUser(USER_NAME);
		system.checkout(CALL_NUM, USER_NAME);
		BookTransactionStatus status = system.returnBook(CALL_NUM, USER_NAME);
		assertEquals(BookTransactionStatus.SUCCESS, status);
		
		//testing for NYTimes book type
		system2.load(TYPE2, FILEPATH2);
		system2.createUser(USER_NAME);
		system2.checkout(CALL_NUM2, USER_NAME);
		BookTransactionStatus status2 = system2.returnBook(CALL_NUM2, USER_NAME);
		assertEquals(BookTransactionStatus.SUCCESS, status2);
	}
	
	@Test
	public void testReturnBookNotCheckedout() throws FileNotFoundException {
		//testing for standard book type
		system.load(TYPE1, FILEPATH1);
		system.createUser(USER_NAME);
		system.checkout(CALL_NUM, USER_NAME);
		BookTransactionStatus status = system.returnBook("777", USER_NAME);
		assertEquals(BookTransactionStatus.NOT_CHECKEDOUT, status);
		
		//testing for NYTimes book type
		system2.load(TYPE2, FILEPATH2);
		system2.createUser(USER_NAME);
		system2.checkout(CALL_NUM2, USER_NAME);
		BookTransactionStatus status2 = system2.returnBook("9780812984965", USER_NAME);
		assertEquals(BookTransactionStatus.NOT_CHECKEDOUT, status2);
	}
	
	@Test
	public void testReturnBookNoSuchUser() {
		//testing for standard book type
		system.load(TYPE1, FILEPATH1);
		system.createUser(USER_NAME);
		system.checkout(CALL_NUM, USER_NAME);
		BookTransactionStatus status = system.returnBook(CALL_NUM, "Mary");
		assertEquals(BookTransactionStatus.NO_SUCH_USER, status);
		
		//testing for NYTimes book type
		system2.load(TYPE2, FILEPATH2);
		system2.createUser(USER_NAME);
		system2.checkout(CALL_NUM2, USER_NAME);
		BookTransactionStatus status2 = system.returnBook(CALL_NUM2, "Mary");
		assertEquals(BookTransactionStatus.NO_SUCH_USER, status2);
	}

	@Test
	public void testFind() {
		//testing for standard book type
		system.load(TYPE1, FILEPATH1);
		system.createUser(USER_NAME);
		system.checkout(CALL_NUM, USER_NAME);
	    UserCheckoutList list = system.find(USER_NAME);
	    assertEquals(USER_NAME, list.getUser());
	    
	  //testing for NYTimes book type
	  	system2.load(TYPE2, FILEPATH2);
	  	system2.createUser(USER_NAME);
	  	system2.checkout(CALL_NUM2, USER_NAME);
	  	UserCheckoutList list2 = system2.find(USER_NAME);
	  	assertEquals(USER_NAME, list2.getUser());
	}
	
	/*
	 * The following test cases are my own test cases
	 */
	
	@Test
	public void testLoad() throws FileNotFoundException {
		//testing for standard book type
		system.load(TYPE1, FILEPATH1);
		system.createUser(USER_NAME);
		assertEquals(BookTransactionStatus.SUCCESS, system.checkout("333", USER_NAME));
		
		//testing for NYTimes book type
		system2.load(TYPE2, FILEPATH2);
		system2.createUser(USER_NAME);
		assertEquals(BookTransactionStatus.SUCCESS, system2.checkout(CALL_NUM2, USER_NAME));
	}
	
	@Test
	public void testListBooksForUserSortByCallNumber() throws FileNotFoundException {
		//testing for standard book type
		system.load(TYPE1, FILEPATH1);
		system.createUser(USER_NAME);
		system.checkout(CALL_NUM, USER_NAME);
		system.checkout("333", USER_NAME);
		String actualOutput = "Checked out books for Joe\n"
				+ "Title: Julius Caesar\n"
				+ "Author: William Shakespeare\n"
				+ "Call Number: 333\n"
				+ "Publisher: Simon & Schuster\n"
				+ "Published in: 1599\n"
				+ "Due on " + LocalDate.now().plusDays(14).format(FORMAT).toString() + "\n"
				+ "Title: Captain Underpants\n"
				+ "Author: Dav Pilkey\n"
				+ "Call Number: 452\n"
				+ "Publisher: Blue Sky and Scholastic\n"
				+ "Published in: 1997\n"
				+ "Due on " + LocalDate.now().plusDays(14).format(FORMAT).toString() + "\n";
		assertEquals(actualOutput, system.listBooksForUserSortByCallNumber(USER_NAME));
		
		//testing for NYTimes book type
		system2.load(TYPE2, FILEPATH2);
		system2.createUser(USER_NAME);
		system2.checkout(CALL_NUM2, USER_NAME);
		system2.checkout("9780812984965", USER_NAME);
		String actualOutput2 = "Checked out books for Joe\n"
				+ "Title: WOW, NO THANK YOU\n"
				+ "Author: Samantha Ir\n"
				+ "Call Number: 9780525563488\n"
				+ "ISBN13: 9780525563488\n"
				+ "ISBN10: 0525563482\n"
				+ "Comedic essays by an author and blogger who splits her time between Hollywood meetings and living in a liberal suburban town in a red state.\n"
				+ "Publisher: Vintage\n"
				+ "Book Rank: 1 on the null bestseller list\n"
				+ "Due on " + LocalDate.now().plusDays(14).format(FORMAT).toString() + "\n"
				+ "Title: JUST MERCY\n"
				+ "Author: Bryan Stevenson\n"
				+ "Call Number: 9780812984965\n"
				+ "ISBN13: 9780812984965\n"
				+ "ISBN10: 081298496X\n"
				+ "A civil rights lawyer and MacArthur grant recipient’s memoir of his decades of work to free innocent people condemned to death.\n"
				+ "Publisher: Spiegel & Grau\n"
				+ "Book Rank: 3 on the null bestseller list\n"
				+ "Due on " + LocalDate.now().plusDays(14).format(FORMAT).toString() + "\n";
		assertEquals(actualOutput2, system2.listBooksForUserSortByCallNumber(USER_NAME));
	}
	
	@Test
	public void testListBooksForUserSortByTitle() throws FileNotFoundException {
		//testing for standard book type
		system.load(TYPE1, FILEPATH1);
		system.createUser(USER_NAME);
		system.checkout(CALL_NUM, USER_NAME);
		system.checkout("333", USER_NAME);
		String actualOutput = "Checked out books for Joe\n"
				+ "Title: Captain Underpants\n"
				+ "Author: Dav Pilkey\n"
				+ "Call Number: 452\n"
				+ "Publisher: Blue Sky and Scholastic\n"
				+ "Published in: 1997\n"
				+ "Due on " + LocalDate.now().plusDays(14).format(FORMAT).toString() + "\n"
				+ "Title: Julius Caesar\n"
				+ "Author: William Shakespeare\n"
				+ "Call Number: 333\n"
				+ "Publisher: Simon & Schuster\n"
				+ "Published in: 1599\n"
				+ "Due on " + LocalDate.now().plusDays(14).format(FORMAT).toString() + "\n";
		assertEquals(actualOutput, system.listBooksForUserSortByTitle(USER_NAME));
		
		//testing for NYTimes book type
		system2.load(TYPE2, FILEPATH2);
		system2.createUser(USER_NAME);
		system2.checkout(CALL_NUM2, USER_NAME);
		system2.checkout("9780812984965", USER_NAME);
		String actualOutput2 = "Checked out books for Joe\n"
				+ "Title: JUST MERCY\n"
				+ "Author: Bryan Stevenson\n"
				+ "Call Number: 9780812984965\n"
				+ "ISBN13: 9780812984965\n"
				+ "ISBN10: 081298496X\n"
				+ "A civil rights lawyer and MacArthur grant recipient’s memoir of his decades of work to free innocent people condemned to death.\n"
				+ "Publisher: Spiegel & Grau\n"
				+ "Book Rank: 3 on the null bestseller list\n"
				+ "Due on " + LocalDate.now().plusDays(14).format(FORMAT).toString() + "\n"
				+ "Title: WOW, NO THANK YOU\n"
				+ "Author: Samantha Ir\n"
				+ "Call Number: 9780525563488\n"
				+ "ISBN13: 9780525563488\n"
				+ "ISBN10: 0525563482\n"
				+ "Comedic essays by an author and blogger who splits her time between Hollywood meetings and living in a liberal suburban town in a red state.\n"
				+ "Publisher: Vintage\n"
				+ "Book Rank: 1 on the null bestseller list\n"
				+ "Due on " + LocalDate.now().plusDays(14).format(FORMAT).toString() + "\n";
		assertEquals(actualOutput2, system2.listBooksForUserSortByTitle(USER_NAME));
	}



}
