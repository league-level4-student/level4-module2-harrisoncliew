package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return s1;
		} else {
			return s2;
		}

	}

	// if String s contains the word "underscores", change all of the spaces to
	// underscores
	public static String formatSpaces(String s) {
		if (s.contains("underscores")) {
			s = s.replace(' ', '_');
		}
		return s;
	}

	// Return the name of the person whose LAST name would appear first if they were
	// in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		String higherTempLastName;
		String higherTempName;
		s1 = s1.trim();
		s2 = s2.trim();
		s3 = s3.trim();
		int spaceIndex1 = s1.indexOf(' ');
		String lastName1 = s1.substring(spaceIndex1 + 1);
		int spaceIndex2 = s2.indexOf(' ');
		String lastName2 = s2.substring(spaceIndex2 + 1);
		int spaceIndex3 = s3.indexOf(' ');
		String lastName3 = s3.substring(spaceIndex3 + 1);
		if (lastName1.compareTo(lastName2) < 0) {
			higherTempLastName = lastName1;
			higherTempName = s1;
		} else {
			higherTempLastName = lastName2;
			higherTempName = s2;
		}
		if (lastName3.compareTo(higherTempLastName) < 0) {
			return s3;
		} else {
			return higherTempName;
		}

	}

	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int counter = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				counter += Character.getNumericValue(s.charAt(i));
			}
		}
		return counter;
	}

	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int index = 0;
		int counter = 0;
		while (s.indexOf(substring, index) >= 0) {
			index = s.indexOf(substring, index) + 1;
			counter++;
		}
		return counter;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		byte[] bytes = s.getBytes();

		String hi = Utilities.encrypt(bytes, (byte) key);
		return hi;
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}

	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int index = 0;
		int counter = 0;
		while (s.indexOf(substring, index) >= 0) {
			if (s.charAt(s.indexOf(substring, index) + substring.length()) == ' ') {
				counter++;
			}
			index = s.indexOf(substring, index) + 1;
		}
		return counter;
	}

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int counter = 0;
		int startIndex;
		int endIndex;
		startIndex = s.indexOf(substring, 0) + substring.length();
		endIndex = s.lastIndexOf(substring);
		return endIndex-startIndex;
	}

	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String forwards = "";
		String backwards = "";
		s = s.toLowerCase();
		s.replace(" ", "");
		for (int i = 0; i < s.length(); i++) {
			if(Character.isLetter(s.charAt(i)) == true) {
				forwards += s.charAt(i);
			}
			
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			if(Character.isLetter(s.charAt(i)) == true) {
				backwards += s.charAt(i);
			}
		}
		if (forwards.equals(backwards)) {
			return true;
		} else {
			return false;
		}
	}

}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
