package leetcode;

public class ReverseWordOrder {

	public char[] reverse(final char[] w) {
		int s = w.length-1, e = w.length-1;
		char[] r = new char[w.length];
		int i = 0;
		while (s >= 0) {
			if (s == 0 || w[s-1] == ' ') {
				while (s <= e) {
					r[i++] = w[s];
					s++;
				}
				if (i < w.length-1) r[i++] = ' ';
				e = w.length-i-1;
				s = e;
			} else s--;
		}
		return r;
	}
	
	public void print(char[] c) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < c.length; i++) sb.append(c[i]);
		System.out.println(sb.toString());
	}
 	public static void main(String[] args) {
		ReverseWordOrder rwo = new ReverseWordOrder();
		rwo.print(rwo.reverse(new String("perfect makes practice").toCharArray()));
		rwo.print(rwo.reverse(new String("movie a is story toy").toCharArray()));
	}

}
