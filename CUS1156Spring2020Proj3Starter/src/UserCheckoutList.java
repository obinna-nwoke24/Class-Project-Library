



import java.util.ArrayList;
import java.util.Collections;

/**
 * this class represents a list of books checked out by a library user it
 * contains the name of the user and a list of Book objects
 * 
 * @author Bonnie MacKellar
 *
 */
public class UserCheckoutList {
	private String user;
	private ArrayList<Book> checkoutBooks;

	/**
	 * Constructs a user checkout list object.
	 * 
	 * @param person
	 *            the owner
	 */
	public UserCheckoutList(String person) {
		user = person;
		checkoutBooks = new ArrayList<Book>();
	}

	/**
	 * Gets the user name
	 * 
	 * @returns the name of the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Adds a book to the list.
	 * 
	 * @param book
	 *            a Book object
	 */
	public void addBook(Book book) {
		checkoutBooks.add(book);
	}

	/**
	 * tests if the list is empty
	 * 
	 * @return true if the list contains no books, false otherwise
	 */
	public boolean isEmpty() {
		if (checkoutBooks.size() == 0)
			return true;
		else
			return false;
	}

	/**
	 * finds a particular book by its call number
	 * 
	 * @return the matching Book object or null if not found
	 */

	public Book findByCallNumber(String cnum) {

		for (Book curr : checkoutBooks) {
			if (curr.getCallNumber().equals(cnum))
				return curr;
		}
		return null;
	}

	/**
	 * removes a book from the list, given its call number
	 * 
	 * @return true if the book is found and removed, false if the book is not on the
	 *         list
	 */
	public Boolean removeBook(String cnum) {
		Book book = findByCallNumber(cnum);
		if (book == null) // not found
			return false;
		checkoutBooks.remove(book);
		return true;
	}
	
	public UserCheckoutList sortByCallNumber() {
		ArrayList<Book> sort = new ArrayList<Book>(checkoutBooks);
		UserCheckoutList sortedList = new UserCheckoutList(this.user);
		Collections.sort(sort, (book1, book2) -> {
			return book1.getCallNumber().compareTo(book2.getCallNumber());
		});
		
		for (Book book: sort)
		{
			sortedList.addBook(book);
		}
		return sortedList;
		
	}
	
	public UserCheckoutList sortByTitle() {
		ArrayList<Book> sort = new ArrayList<Book>(checkoutBooks);
		UserCheckoutList sortedList = new UserCheckoutList(this.user);
		Collections.sort(sort, (book1, book2) -> {
			return book1.getTitle().compareTo(book2.getTitle());
		});
		for (Book book: sort)
		{
			sortedList.addBook(book);
		}
		return sortedList;
	}
	
	public Boolean hasBook(String callnumber) {
		for (Book book: checkoutBooks)
			if (book.getCallNumber().equals(callnumber))
				return true;
		return false;
	}

	/**
	 * return a formatted string, suitable for display, that contains
	 * information about the UserCheckoutList object, in particular, a list of
	 * books checked out by the user
	 */
	public String toString() {
		String newline = System.getProperty("line.separator");
		StringBuffer buf = new StringBuffer();
		buf.append("Checked out books for " + user + newline);
		for (Book book : checkoutBooks)
			buf.append(book.toString());

		return buf.toString();
	}
}
