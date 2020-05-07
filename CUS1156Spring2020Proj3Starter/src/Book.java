import java.time.LocalDate;

public interface Book {

	String getTitle();

	void setTitle(String title);

	String getCallNumber();

	void setCallNumber(String callNumber);

	LocalDate getDueDate();

	void setDueDate(LocalDate dueDate);

	Boolean getBookTransactionStatus();

	void setBookTransactionStatus(Boolean val);

	/**
	 * return a formatted string, suitable for display, that contains
	 * information about the Book object
	 */
	String toString();

}