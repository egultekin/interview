package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class WordBreak {
	
	public static boolean canBeBroken(String str, Set<String> dictionary) {
		if (dictionary.contains(str)) return true;
		for (int i = 1; i < str.length(); i++) {
			String w = str.substring(i);
			if (canBeBroken(w, dictionary) && dictionary.contains(str.substring(0, i)))
				return true;
		}
		return false;
	}
	
	private static void breakIntoWords(String str, Set<String> dictionary, List<String> broken) {
		if (dictionary.contains(str)) broken.add(str);
		for (int i=1; i < str.length(); i++) {
			String w = str.substring(i);
			List<String> found = new Vector<String>();
			breakIntoWords(w, dictionary, found);
			if (found.size() > 0 && dictionary.contains(str.substring(0, i)))
				for (String s : found) broken.add(str.substring(0, i)+" "+s);
		}
	}
	
	public static int howManyWaysCanItBeBroken(String str, Set<String> dictionary) {
		List<String> broken = new Vector<>();
		breakIntoWords(str, dictionary, broken);
		if (broken.size() == 0) System.out.println("No way found.");
		else for (String s : broken) System.out.println(s);
		return broken.size();
	}

	public static void main(String[] args) {
		String str = "takebathandcome";
		Set<String> dictionary = new HashSet<String>(
				Arrays.asList(new String[] {"take", "bat", "bath", "come", "and", "hand"}));
		System.out.printf("Can %s be broken into words using %s? Result: %s\n", 
				str, dictionary, canBeBroken(str,dictionary));
		
		System.out.printf("%s can be broken in %d different ways.\n", str, howManyWaysCanItBeBroken(str, dictionary));
	}

}
