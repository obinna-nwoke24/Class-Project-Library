import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class StandardBookCollection  extends BookCollection {
	private HashMap<String, StandardBook> books;
	private int size;
	
	public StandardBookCollection() {
		books = new HashMap<String, StandardBook>();
		size = 0;
	}
	
	public void addBook(String cnum, StandardBook book) {
		books.put(cnum, book);
	}
	
	public void removeBook(String cnum) {
		books.remove(cnum);
	}
	
	public boolean contains(String callnumber) {
		if (books.containsKey(callnumber))
			return true;
		return false;
	}
	
	public StandardBook book(String callnumber) {
		return books.get(callnumber);
	}

	public StandardBook processLine(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		
		StandardBook book = new StandardBook(null, null);
		
		String cnum = scan.next();
		String title = scan.next();
		@SuppressWarnings("unused")
		String dueDate = scan.next();
		String author = scan.next();
		String publisher = scan.next();
		String pubDate = scan.next();
		book.setCallNumber(cnum);
		book.setTitle(title);
		book.setDueDate(LocalDate.now().plusDays(14));
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPublicationDate(pubDate);
		scan.close();
		return book;
	}
	@Override
	public void load(String filepath) {
		System.err.println("Type: standard");
		System.err.println("The correct number of books is 9");
		File file = new File(filepath);
		try
		{
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) {
				String line = read.nextLine();
				StandardBook book = processLine(line);
				books.put(book.getCallNumber(), book);
				size += 1;
				
			}
			System.err.println("after loading Number of books: " + size);
			read.close();
		} catch (FileNotFoundException exc) {
			System.out.println("File not found!");
			System.exit(0);
		}
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
