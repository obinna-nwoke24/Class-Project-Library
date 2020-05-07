
public class BookCollectionFactory {
	
	static public BookCollection getBookCollection(String type) {
		BookCollection bookCollection;
		if (type.equals("standard"))
		{
			bookCollection = new StandardBookCollection();
			return bookCollection;
		}
		else if (type.equals("NYTimes"))
		{
			bookCollection = NYTimesBookCollection.getInstance();
			return bookCollection;
		}
		else
		{
			try
			{
				throw new IllegalArgumentException();
			} catch (IllegalArgumentException exception) {
				System.out.println("There is no " + type + " book collection.");
			}
			return null;
		}
	}

}
