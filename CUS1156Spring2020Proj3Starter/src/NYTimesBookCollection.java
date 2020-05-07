import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NYTimesBookCollection extends BookCollection {
	private HashMap<String, NYTimesBook> books;
	private static NYTimesBookCollection instance;
	private int size;
	
	private NYTimesBookCollection() {
		books = new HashMap<String, NYTimesBook>();
		size = 0;
		
	}
	public static NYTimesBookCollection getInstance() {
		if (instance == null)
			return new NYTimesBookCollection();
		return instance;
	}
	
	public void addBook(String cnum, NYTimesBook book) {
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
	
	public NYTimesBook book(String callnumber) {
		return books.get(callnumber);
	}
	
	public void acquire(NYTimesBook book) {
		books.put(book.getCallNumber(), book);
	}

	public void load(String filePath) {

		try {
			// read the json file
			FileReader reader = new FileReader(filePath);

			JSONParser jsonParser = new JSONParser();
			
			// the JSONObject represents everything in the file
			JSONObject overviewList = (JSONObject) jsonParser.parse(reader);
			long numResults = (long) overviewList.get("num_results");
			System.err.println("Type: NYTimes");
			System.err.println("The number of books is " + numResults);
			
			JSONObject results = (JSONObject) overviewList.get("results");
			
			// get the list of books
			// get an array from the JSON object
			JSONArray books = (JSONArray) results.get("books");
			
			@SuppressWarnings("rawtypes")
			Iterator bookIter = books.iterator();

			// take each value from the json array separately
			while (bookIter.hasNext()) {
				JSONObject jsonBook = (JSONObject) bookIter.next();
				
				NYTimesBook book = new NYTimesBook();
		
				loadBookDetails(jsonBook, book);  
				
				// not checked out
				book.setDueDate(null);
				this.acquire(book);
				size += 1;
				}
			System.err.println("after loading Number of books: " + size);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			} catch (ParseException e) { 
				e.printStackTrace();
			} 
		}
	private void loadBookDetails(JSONObject jsonBook, NYTimesBook book) {
		long rank = (long)jsonBook.get("rank");
		book.setRank(rank);
		book.setCallNumber((String)jsonBook.get("primary_isbn13"));
		book.setIsbn13((String)jsonBook.get("primary_isbn13"));
		book.setIsbn10((String)jsonBook.get("primary_isbn10"));
		book.setAuthor((String) jsonBook.get("author"));
		book.setTitle((String)jsonBook.get("title"));
		book.setPublisher((String)jsonBook.get("publisher"));
		book.setDescription((String) jsonBook.get("description"));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
