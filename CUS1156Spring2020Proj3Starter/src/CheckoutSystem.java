



import java.time.LocalDate;
import java.util.ArrayList;

/**
 * the checkout and return system. The checkout and return system can checkout
 * books, return books, and list the books checked out by a given user
 * 
 * @author Bonnie MacKellar
 *
 */
public class CheckoutSystem {
	private ArrayList<UserCheckoutList> userLists;
	private LocalDate currentDate;
	private static final int CHECKOUT_PERIOD = 14;
	private BookCollection books;

	/**
	 * Constructs a CheckoutSystem object
	 */
	public CheckoutSystem(LocalDate currentDate) {
		userLists = new ArrayList<UserCheckoutList>();
		this.setCurrentDate(currentDate);
	}

	/**
	 * checkout a book by adding it to the user's list of checked out books.
	 * If the user does not exist in the system, return false
	 * 
	 * @param book
	 *            the book to be checked out
	 * @param userName
	 *            the name of the user who is checking out the book
	 */
//	public Boolean checkout(Book book, String userName) {
//		UserCheckoutList list = find(userName);
//		if (list == null) 
//			return false;
//		LocalDate dueDate = currentDate.plusDays(CHECKOUT_PERIOD);
//		book.setDueDate(dueDate);
//		list.addBook(book);
//		return true;
//	}
	
	public BookTransactionStatus checkout(String callNum, String userName) {
		UserCheckoutList list = find(userName);
		if (list == null)
			return BookTransactionStatus.NO_SUCH_USER;
		if (!books.contains(callNum))
			return BookTransactionStatus.NOT_IN_LIBRARY;
		if (list.hasBook(callNum))
			return BookTransactionStatus.IS_CHECKEDOUT;
		if (books.book(callNum).getBookTransactionStatus() == false)
		{
			list.addBook(books.book(callNum));
			books.book(callNum).setBookTransactionStatus(true);
			books.book(callNum).setDueDate(LocalDate.now().plusDays(14));
		}
		else
			return BookTransactionStatus.IS_CHECKEDOUT;
		return BookTransactionStatus.SUCCESS;
	}

	private UserCheckoutList addUserList(String userName) {
		UserCheckoutList newList = new UserCheckoutList(userName);
		userLists.add(newList);
		return newList;
	}

	/**
	 * this removes a returned book from the user's list
	 * 
	 * @param cnum
	 *            the call number
	 * @param userName
	 *            the name of the user
	 * @return 0 if success, -1 if either the user or the book is not found
	 */
//	public Boolean returnBook(String cnum, String userName) {
//		UserCheckoutList list = find(userName);
//		if (list == null) // user does not have a list so return -1
//			return false;
//
//		return (list.removeBook(cnum));
//	}
	
	public BookTransactionStatus returnBook(String callNum, String userName) {
		UserCheckoutList list = find(userName);
		if (list == null)
			return BookTransactionStatus.NO_SUCH_USER;
		if (!books.contains(callNum))
			return BookTransactionStatus.NOT_IN_LIBRARY;
		if (!list.hasBook(callNum))
			return BookTransactionStatus.NOT_CHECKEDOUT;
		books.book(callNum).setDueDate(null);
		list.removeBook(callNum);
		books.book(callNum).setBookTransactionStatus(false);
		return BookTransactionStatus.SUCCESS;
	}

	/**
	 * Finds a list corresponding to the user or returns null if not found
	 * 
	 * @param user
	 *            the user
	 * @return the list for the user
	 */
	public UserCheckoutList find(String user) {
		for (UserCheckoutList list : userLists)
			if (list.getUser().equals(user))
				return list;

		return null;
	}

	/**
	 * return a string which is a list of all books checked out for a given user
	 * 
	 */
	public String listBooksForUser(String userName) {
		UserCheckoutList list = find(userName);
		if (list == null) // user does not have a list so return -1
			return "This user is not in our system";

		if (list.isEmpty())
			return userName + "has no checked out books";
		return (list.toString());

	}

	public Boolean createUser(String userName) {
		UserCheckoutList list = find(userName);
		if (list == null) {// user does not have a list yet, so create one
			list = addUserList(userName);
			return true;
		}
		else {
			return false;
		}

	}
	
//	public StandardBook processLine(String line) {
//		Scanner scan = new Scanner(line);
//		scan.useDelimiter(",");
//		
//		StandardBook book = new StandardBook(null, null);
//		
//		String cnum = scan.next();
//		String title = scan.next();
//		String dueDate = scan.next();
//		String author = scan.next();
//		String publisher = scan.next();
//		String pubDate = scan.next();
//		book.setCallNumber(cnum);
//		book.setTitle(title);
//		book.setDueDate(currentDate);
//		book.setAuthor(author);
//		book.setPublisher(publisher);
//		book.setPublicationDate(pubDate);
//		scan.close();
//		return book;
//	}
	
	public void load(String type, String filepath)
	{
		books = BookCollectionFactory.getBookCollection(type);
		books.load(filepath);
	}
//	public void load(String filepath) throws FileNotFoundException {
//		
//		File file = new File(filepath);
//		Scanner read = new Scanner(file);
//		
//		while (read.hasNextLine()) {
//			String line = read.nextLine();
//			StandardBook book = processLine(line);
//			books.addBook(book.getCallNumber(), book);
//			
//		}
//		read.close();
//		
//	}

	public String listBooksForUserSortByCallNumber(String userName) {
		UserCheckoutList list = find(userName);
		if (list == null) // user does not have a list so return -1
			return "This user is not in our system";

		if (list.isEmpty())
			return userName + "has no checked out books";
		
		list = list.sortByCallNumber();
		return (list.toString());
	}
		
		

	public String listBooksForUserSortByTitle(String userName) {
		UserCheckoutList list = find(userName);
		if (list == null) // user does not have a list so return -1
			return "This user is not in our system";

		if (list.isEmpty())
			return userName + "has no checked out books";
		
		list = list.sortByTitle();
		return (list.toString());
		
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public static int getCheckoutPeriod() {
		return CHECKOUT_PERIOD;
	}
}
