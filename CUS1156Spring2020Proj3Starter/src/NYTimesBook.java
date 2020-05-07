import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NYTimesBook implements Book {
	
	private String title;
	private String description;
	private String author;
	private String publisher;
	private String isbn13;
	private String isbn10;
	private String callNumber;
	private long rank;
	@SuppressWarnings("unused")
	private String last_week_rank;
	@SuppressWarnings("unused")
	private String weeks_on_bestseller_list;
	private Boolean status = false;
	LocalDate dueDate;

	public NYTimesBook() {
		super();
		this.title = "";
		this.callNumber = "";
		this.status = false;
	}
	
	public NYTimesBook(String title, String callNumber) {
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

	public long getRank() {
		return rank;
	}
	
	public void setRank(long rank) {
		this.rank = rank;
		
	}

	public String getIsbn13() {
		return isbn13;
	}
	
	public void setIsbn13(String string) {
		this.isbn13 = string;
		
	}

	public String getIsbn10() {
		return isbn10;
	}
	
	public void setIsbn10(String string) {
		this.isbn10 = string;
		
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

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
		
	}
	// this is the toString method that might need changing
//	public String toString() {
//
//		String newline = System.getProperty("line.separator");
//		StringBuffer buf = new StringBuffer();
//		buf.append(title);
//		buf.append(newline);
//		buf.append(callNumber);
//		buf.append(newline);
//		if (dueDate == null)
//			buf.append("not checked out");
//		else {
//			DateTimeFormatter format = DateTimeFormatter
//					.ofPattern("MMM d yyyy");
//			String dateStr = dueDate.format(format);
//			buf.append("Due on " + dateStr);
//		}
//		buf.append(newline);
//		String str = buf.toString();
//		return str;
//	}
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
		buf.append("ISBN13: " + isbn13);
		buf.append(newline);
		buf.append("ISBN10: " + isbn10);
		buf.append(newline);
		buf.append(description);
		buf.append(newline);
		buf.append("Publisher: " + publisher);
		buf.append(newline);
		buf.append("Book Rank: " + rank + " on the null bestseller list");
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
