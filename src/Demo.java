import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Demo {
	
	// Merge contents of arrays S1 and S2 into properly sized array S.
	public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
		int i = 0;
		int j = 0;

		while (i+j < S.length) {
			if (j == S2.length || (i <S1.length && comp.compare(S1[i], S2[j]) < 0)) {
				S[i+j] = S1[i++];
			} else {
				S[i+j] = S2[j++];
			}
		}
	}
	
	// Sort contents of array S
	public static <K> void mergeSort(K[ ] S, Comparator<K> comp) {
		int n = S.length;

		// Make sure n is larger than 2 before creating mid variable
		if (n < 2) return;
		int mid = n/2;

		K[] S1 = Arrays.copyOfRange(S, 0, mid);
		K[] S2 = Arrays.copyOfRange(S, mid, n);

		//sort with recursion
		mergeSort(S1, comp);
		mergeSort(S2, comp);

		//merge results
		merge(S1, S2, S, comp);
	}

	public static void main(String[] args) {

		Comparator<String> natural = Comparator.naturalOrder();
		Comparator<String> length = new LengthComparator();
		Comparator<String> alpha = new AlphaComparator();

		// here's a small demo to test your implementations
		System.out.println("=====Part 1=====");
		String[] a = {"Allison", "Sally", "Bob"};
		System.out.println("Original Array -> " + Arrays.toString(a));
		System.out.println("Alpha Sort");
		mergeSort(a, alpha);
		System.out.println(Arrays.toString(a));

		String[] b = {"Allison", "Sally", "Bob"};
		System.out.println("\nOriginal Array -> " + Arrays.toString(b));
		System.out.println("Length Sort");
		mergeSort(b, length);
		System.out.println(Arrays.toString(b));

		// now do part 2 of the lab: read in file Housman.txt
		System.out.println("\n\n=====Part 2=====");

		System.out.println("annana".compareTo("bapple")); // 1 = right comes b4 left && -1 = left comes b4 right

		// sort its words alphabetically
		// sort its words by length
		// write both to files

		/**
		 * Take a look at this example and experiment to see how to use IO for file handling.
		 *
		 * Notice, running this program will create a file called vacation.txt, in your workspace.
		 * If you don't see it in your IDE, you can use your file system to navigate to it.
		 */
		String token;
		LinkedList<String> list = new LinkedList<>();
		int size = 0;

		//line to get current path directory
		System.out.println(new File(".").getAbsoluteFile());

		// Open and read from a file
		try {
			File file = new File("src/Housman.txt");
			// Scanner object is constructed with new File() for file input
			Scanner fileInput = new Scanner(file);
			while (fileInput.hasNext())    // check  for next token (String)
			{
				token = fileInput.next();    // now get that String into the destination variable
				list.add(token);        // and get that int into the year variable
				size++;
			}
			fileInput.close();
		} catch (FileNotFoundException exc) {
			System.out.println("There was a problem opening the input file");
		}

		String[] array = new String[size];
		for (int i = 0; i < size; i++) {
			array[i] = list.get(i);
		}

		// Create and write to a file for alpha
		try {
			// Create a variable of type PrintWriter to store userInput to a file
			PrintWriter outputFile = new PrintWriter(new FileWriter("src/sortedByAlpha.txt"));

			//sort array by alhpa
			mergeSort(array, alpha);

			// Loop a couple of times, and write some lines of text to the new file.
			for (int i = 0; i < size; i++) {
				// Use the output stream as you would use System.out to display to the console
				outputFile.println(array[i]);
			}

			outputFile.close();
		} catch (IOException exc) {
			System.out.println("There was a problem opening the file for output");
		}

		// Create and write to a file for alpha
		try {
			// Create a variable of type PrintWriter to store userInput to a file
			PrintWriter outputFile = new PrintWriter(new FileWriter("src/sortedByLength.txt"));

			//sort array by length
			mergeSort(array, length);

			// Loop a couple of times, and write some lines of text to the new file.
			for (int i = 0; i < size; i++) {
				// Use the output stream as you would use System.out to display to the console
				outputFile.println(array[i]);
			}

			outputFile.close();
		} catch (IOException exc) {
			System.out.println("There was a problem opening the file for output");
		}
	}

}
