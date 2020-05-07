

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents one book. A book has a call number, a title, and a due
 * date.
 * 
 * @author Bonnie MacKellar
 *
 */
public class StandardBook implements Book {
	private String title;
	private String callNumber;
	private String author;
	private String publisher;
	private String publicationDate;
	private Boolean status = false;
	LocalDate dueDate;

	/**
	 * create a book with a title and call number
	 * 
	 * @param title
	 * @param callNumber
	 */
	public StandardBook(String title, String callNumber) {
		super();
		this.title = title;
		this.callNumber = callNumber;
		this.status = false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getPublicationDate() {
		return publicationDate;
	}
	
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public Boolean getBookTransactionStatus() {
		return status;
	}
	
	public void setBookTransactionStatus(Boolean val) {
		this.status = val;
	}

	/**
	 * return a formatted string, suitable for display, that contains
	 * information about the Book object
	 */
	@Override
	public String toString() {

		String newline = System.getProperty("line.separator");
		StringBuffer buf = new StringBuffer();
		buf.append("Title: " + title);
		buf.append(newline);
		buf.append("Author: " + author);
		buf.append(newline);
		buf.append("Call Number: " + callNumber);
		buf.append(newline);
		buf.append("Publisher: " + publisher);
		buf.append(newline);
		buf.append("Published in: " + publicationDate);
		buf.append(newline);
		if (dueDate == null)
			buf.append("not checked out");
		else {
			DateTimeFormatter format = DateTimeFormatter
					.ofPattern("MMM d yyyy");
			String dateStr = dueDate.format(format);
			buf.append("Due on " + dateStr);
		}
		buf.append(newline);
		String str = buf.toString();
		return str;
	}

}
