package careercup.fb;

import java.util.List;
import java.util.Vector;

class AllCombinations {
	private boolean isLC(char ch) {
		return ch >= 'a' && ch <= 'z';
	}
	
	private boolean isUC(char ch) {
		return ch >= 'A' && ch <= 'Z';
	}
	
	private char toUpper(char ch) {
		char d = 'a'-'A';
		return (char) (ch-d);
	}
	
	private char toLower(char ch) {
		char d = 'a'-'A';
		return (char) (ch+d);
	}
	
	public static void print(List<String> list) {
		for (String s : list)
			System.out.println(s);
		System.out.println();
	}
	
	public List<String> generate(String text) {
		List<String> subList = new Vector<String>();
		generate(text, subList);
		return subList;
	}
	
	private void generate(String text, List<String> found) {	
		char ch = text.charAt(0);
		if (text.length() == 1) appendChar(found, ch, "");
		else {
			Vector<String> subList = new Vector<>();
			String next = text.substring(1);
			generate(next, subList);
			for (String s : subList) appendChar(found, ch, s);
		}
	}
	
	private void appendChar(List<String> list, char ch, String s) {
		list.add(ch + s);
		if (isLC(ch)) list.add(toUpper(ch) + s);
		else if (isUC(ch)) list.add(toLower(ch) + s);
	}
	
	public static void main(String[] args) {
		AllCombinations ac = new AllCombinations();
		print(ac.generate("ab"));
		print(ac.generate("a1b"));
		print(ac.generate("a1b2c3d4e"));
	}
}
