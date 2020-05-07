import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class TestLibrary {

	private static String collectionType;
	private static String filepath;

	public static void main(String[] args) throws IOException {
		LocalDate currentDate = LocalDate.now();
		CheckoutSystem system = new CheckoutSystem(currentDate);
		loadProps();
		system.load(collectionType, filepath);
		CheckoutMenu menu = new CheckoutMenu(currentDate);
		menu.run(system);
	}

	public static void loadProps() {
      // code to load the filetype (NYTimes or standard) and the file name 
     // from the props.txt file
		try {
			Scanner read = new Scanner(new File("props.txt"));
			collectionType = read.nextLine();
			filepath = read.nextLine();
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
