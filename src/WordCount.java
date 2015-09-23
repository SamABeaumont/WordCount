import java.io.*;
import java.util.*;


/**
 * Parses a file that the user inputs and prints out various data on that file.
 * <p>
 * Loads a file at the relative URL that the user inputs, then prints the amounts of characters and
 * words, average and median word length, and longest words. Prints an error message to the console
 * if no file is found at the location that the user specifies, or if the user inputs an invalid
 * filepath.
 * 
 * @since November 2014
 * @version 1.0
 * @author Sam Beaumont
 */
public class WordCount {
	// So that passing around a bunch of paramters isn't necessary.
	public static final boolean DEBUG = false;
	public static File f;
	public static Scanner input;
	public static int numWords = 0;
	public static String s;
	public static String[] words;
	
	public static void main (String[] args) {
		try {
			input = getInput();
		} catch (FileNotFoundException nf) {
			System.out.println("Error: File not found.");
			return; // Halt execution
		}
		
		s = fToString(); // Converts the file into a String, to be processed later
		System.out.println("Characters: " + f.length()); // Prints the length of the file
		numWords = countWords();
		words = getWords();
		countLines();
		wordsAvgLength();
		wordsMedLength();
		longestWords();
	}
	
	/**
	 * Prompts the user to input a file, then creates a new {@link Scanner} representing that file.
	 * @return A new {@link Scanner} representing the file that is input.
	 * @throws FileNotFoundException If no file is found at the specified location.
	 */
	public static Scanner getInput () throws FileNotFoundException {
		System.out.print("File to process: ");
		// For some reason, storing this Scanner in a variable causes a bug.
		f = new File(new Scanner(System.in).next());
		return new Scanner(f);
	}
	
	public static String fToString () {
		String s = "";
		while (input.hasNextLine()) {
			s += input.nextLine() + "\n";
		}
		input.close();
		return s;
	}
	
	/**
	 * Calculates the number of words in a {@link String}.
	 * @param s The {@link String} that is processed.
	 * @return The amount of words in the string.
	 */
	public static int countWords () {
		Scanner reader = new Scanner(s);
		int numWords = 0;
		while (reader.hasNext()) {
			numWords++;
			reader.next(); // Move on to the next word without actually doing anything.
		}
		reader.close();
		System.out.println("Words: " + numWords);
		return numWords;
	}
	
	/**
	 * Takes a {@link String} representing a file, returning an array
	 * representing all of the words in that {@link String}.
	 * @param s The {@link String} that is processed.
	 * @param numWords The number of words in that {@link String}.
	 * @returnn An array of {@link String}s representing all of the words in the original {@link String}.
	 */
	public static String[] getWords () {
		String[] words = new String[numWords];
		Scanner reader = new Scanner(s); // Reset the Scanner in order to pass words to the array.
		for (int i = 0; i < numWords; i++) {
			words[i] = reader.next();
		}
		reader.close();
		return words;
	}
	
	/**
	 * Uses a scanner to compute the number of lines in a {@link String}, then prints the result.
	 * @param s The {@link String} that is processed.
	 */
	public static void countLines () {
		Scanner reader = new Scanner(s);
		int numLines = 0;
		while (reader.hasNextLine()) {
			numLines++;
			reader.nextLine(); // Move on to the next line.
		}
		reader.close();
		System.out.println("Lines: " + numLines);
	}
	
	/**
	 * Prints the average length of the words in an array.
	 * @param words An array of words, representing a file.
	 */
	public static void wordsAvgLength () {
		int sum = 0;
		for (String str : words) {
			sum += str.length();
		}
		System.out.println("Average word length: " + (double) sum / (double) words.length);
	}
	
	/**
	 * Computes the median length of the words in an array.
	 * @param words An arrayo of words, representing a file.
	 */
	public static void wordsMedLength () {
		int[] lengths = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			lengths[i] = words[i].length();
		}
		Arrays.sort(lengths);
		System.out.print("Median word length: ");
		System.out.println(((double) lengths[lengths.length - words.length / 2] +
				(double) lengths[0 + lengths.length / 2]) / 2.0);
	}
	
	/**
	 * Prints out the ten longest {@link String}s in an array and their lengths.
	 * @param words The array of {@link String}s that is processed.
	 */
	public static void longestWords () {
		// So that calling longest[0].length() doesn't throw a NullPointerException
		String[] longest = {"", "", "", "", "", "", "", "", "", ""};
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < 10; j++) {
				if (words[i].equals(longest[j])) { // duplicate word
					break;
				} else if (words[i].length() >= longest[j].length()) {
					longest = replace(words[i], longest, j);
					break;
				}
			}
		}
		System.out.println("Longest words:");
		for (int i = 0; i < longest.length && longest[i].length() > 0; i++) {
			System.out.println(longest[i].length() + " - " + longest[i]);
		}
	}
	
	/**
	 * Replaces the specified index in an array of {@link String}s with the specified
	 * {@link String}, shifting every subsequent index to the right except for the last index,
	 * which is overwritten.
	 * @param s The {@link String} that replaces the specified index.
	 * @param a The array that is modified.
	 * @param index The index that is replaced.
	 * @return The modified array.
	 * @throws IndexOutOfBoundsException If {@code index} is outside of the bounds of the array.
	 */
	public static String[] replace (String s, String[] a, int index) {
	    String[] b = new String[a.length];
	    // Copy every element before the index is replaced.
	    for (int i = 0; i < index; i++) {
	    	b[i] = a[i];
	    }
	    b[index] = s; // Replace the specified index.
	    // Shift everything after the index to the right, except for the last index.
	    for (int i = index + 1; i < a.length; i++) {
	    	b[i] = a[i - 1];
	    }
	    return b;
	}
	
	/**
	 * Prints a {@link String} to the console if the class constant {@link DEBUG}
	 * is set to {@code true}.
	 * @param s The string that is printed.
	 */
	public static void log (String s) {
		if (DEBUG) System.out.println(s);
	}
}