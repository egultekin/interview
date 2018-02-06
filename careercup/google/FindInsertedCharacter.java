/*
* https://careercup.com/question?id=5727640316018688
* Find the only differing character in the given strings
*/

class FindInsertedCharacter {
	public static int binarySearch(String s1, String s2) {
		String shorter = s1;
		String longer = s2;
		if (s1.length() > s2.length()) {
			shorter = s2;
			longer = s1;
		}
		int l = 0;
		int r = shorter.length()-1;
		while (l < r) {
			int mid = (l+r)/2;
			if (shorter.charAt(mid) != longer.charAt(mid)) r = mid;
			else l = mid+1;
		}

		if (shorter.charAt(l) == longer.charAt(l)) 
			System.out.printf("\"%s\" and \"%s\" differ at index %d\n", s1, s2, shorter.length());
		else 
			System.out.printf("\"%s\" and \"%s\" differ at index %d\n", s1, s2, l);
		return l;
	}

	public static void main(String[] args) {
		binarySearch("abc", "abec");
		binarySearch("abc", "kabc");
		binarySearch("abcd", "abecd");
		binarySearch("a", "b");
		binarySearch("aaaaaaa", "aaaaaaab");
	}
}