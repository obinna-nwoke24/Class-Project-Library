

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * CheckoutMenu handles all details of user interaction It displays a menu and
 * reads the user command input. It also handles all interaction with the user
 * when input is needed to handle a command, such as entering the user name or
 * entering book information
 * 
 * @author Bonnie MacKellar
 *
 */
public class CheckoutMenu {
	private Scanner in;
	private CheckoutSystem system = null;
	private LocalDate currentDate;

	/**
	 * Constructs an CheckoutMenu object.
	 */
	public CheckoutMenu(LocalDate currentDate) {
		in = new Scanner(System.in);

		this.currentDate = currentDate;
	}

	/**
	 * Runs the system. This method kicks off the menu, and determines what to do
	 * based on user command
	 * 
	 * @param system the Checkout System
	 */
	public void run(CheckoutSystem sys) {
		system = sys;
		System.out.println("Welcome to the Very Nice Checkout System");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("LL/dd/yyyy");
		String dateStr = currentDate.format(format);
		System.out.println("Today's date is " + dateStr);
		String command = readCommand();
		while (!command.equals("Q")) {
			if (command.contentEquals("U")) {
				createUser();
			}
			if (command.equals("C")) {
				checkout();
			}

			if (command.equals("R")) {
				returnBook();
			}

			if (command.equals("LC")) {
				listBooksByCallNumber();
			}

			if (command.equals("LT")) {
				listBooksByTitle();
			}

			command = readCommand();
		}
		System.out.println("Bye!");
	}

	// these are private command handlers
	private void createUser() {
		String userName = getUserName();
		Boolean  retVal= system.createUser(userName);
		if (retVal) {
			System.out.println("User " + userName + " was added to the system");
		}
		else {
			System.out.println("User " + userName+ " is already in the system");
		}
		
	}
	
	private void listBooksByCallNumber() {
		String userName = getUserName();
		String list = system.listBooksForUserSortByCallNumber(userName);
		System.out.println(list);
	}

	private void listBooksByTitle() {
		String userName = getUserName();
		String list = system.listBooksForUserSortByTitle(userName);
		System.out.println(list);
	}

	@SuppressWarnings("incomplete-switch")
	private void returnBook() {
		String userName = getUserName();
		String callNum = getCallNumber();
		BookTransactionStatus ret = system.returnBook(callNum, userName);
		switch (ret) {
		case NO_SUCH_USER: {
			System.out.println("This user is not in the system");
			break;
		}
		case NOT_IN_LIBRARY: {
			System.out.println("This book is not in the library");
		}
		case NOT_CHECKEDOUT: {
			System.out.println("The book is not checked out by the user");
			break;
		}
		case SUCCESS: {
			System.out.println("Book was returned");
		}
		}
	}

	private String getCallNumber() {
		System.out.println("Enter the call number");
		String callNumber = in.nextLine();
		return callNumber;
	}

	private void checkout() {
		String userName = getUserName();

		String callNum = getCallNumber();
		BookTransactionStatus retVal = system.checkout(callNum, userName);
		switch (retVal) {
		case NOT_IN_LIBRARY: {
			System.out.println("The book is not in the library and cannot be checked out");
			break;
		}
		case IS_CHECKEDOUT: {
			System.out.println("The book is already checked out");
			break;
		}
		case NO_SUCH_USER: {
			System.out.println("The user is not in the system");
			break;
		}
		default:
			System.out.println("The book was checked out");
			break;
		}
	}

	private String getUserName() {
		System.out.println("Enter the user name");
		String userName = in.nextLine();
		return userName;
	}

	@SuppressWarnings("unused")
	private Book getBookInfo() {
		System.out.println("Enter the book title");
		String title = in.nextLine();

		String cnum = getCallNumber();
		Book book = new StandardBook(title, cnum);
		return book;
	}

	private String readCommand() {
		System.out.println("Please enter a command");
		System.out.println("U: create a user");
		System.out.println("C: check out a book");
		System.out.println("R: return a book");
		System.out.println("LC: list the books checked out by a user, sorted by call number");
		System.out.println("LT: list the books checked out by a user, sorted by title");
		System.out.println("Q: quit");
		System.out.println(">>>>>>");
		String command = in.nextLine().toUpperCase();
		return command;
	}

}