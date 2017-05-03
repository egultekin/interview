package cci;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

class t9 {
  private static final int ALPHABET = 26;
  private static final char NIL = '\0';
  static class Node {
    Node[] n;
    char v;

    public Node(char val, boolean nil) {
      v = val;
      if (nil) n = null;
      else n = new Node[ALPHABET];
    }

    public Node(char val) {
    	this(val, false);
    }

    public boolean isNil() {
      return n == null;
    }
  }

  private void add(String word) {
    char[] ca = word.toCharArray();
    // assumption: all letters are in ['a', 'z']
    int i = 0;
    Node n = root;
    while (i < ca.length) {
      int o = ca[i]-'a';
      if (null == n.n[o])
        if (i == ca.length - 1) n.n[o] = new Node(ca[i], true);
        else n.n[o] = new Node(ca[i]);
      n = n.n[o];
      i++;
    }
  }

  private void print(Node n, String key, int pos, String str) {
	  if (null == n) return;
	  if (n.isNil() && pos == key.length()) {
		  System.out.println(str);
		  return;
	  } else if (n.isNil()) return;
	  char c = key.charAt(pos);
	  if (!hm.containsKey(c)) throw new IllegalArgumentException("T9 characters should be in range 2-9");
	  char[] ca = hm.get(c);
	  for (int i = 0; i < ca.length; i++) print(n.n[ca[i]-'a'], key, pos+1, str+ca[i]);
  }

  public void print(String key) {
    print(root, key, 0, "");
  }

  public t9(String filename) throws IOException {
	  root = new Node(NIL);
	  BufferedReader r = null;
	  try {
		  r = new BufferedReader(new FileReader(filename));
		  String line;
		  while ( (line = r.readLine()) != null) add(line);
		  hm = new HashMap<>();
		  char[] a2 = new char[] {'a', 'b', 'c'};
		  char[] a3 = new char[] {'d', 'e', 'f'};
		  char[] a4 = new char[] {'g', 'h', 'i'};
		  char[] a5 = new char[] {'j', 'k', 'l'};
		  char[] a6 = new char[] {'m', 'n', 'o'};
		  char[] a7 = new char[] {'p', 'q', 'r', 's'};
		  char[] a8 = new char[] {'t', 'u', 'v'};
		  char[] a9 = new char[] {'w', 'x', 'y', 'z'};
		  hm.put('2', a2);
		  hm.put('3', a3);
		  hm.put('4', a4);
		  hm.put('5', a5);
		  hm.put('6', a6);
		  hm.put('7', a7);
		  hm.put('8', a8);
		  hm.put('9', a9);
	  } finally {
    	if (null != r) r.close();
	  }
  }

  private Node root;
  private HashMap<Character, char[]> hm;
  
  public static void main(String[] args) {
	  try {
		  t9 keyboard = new t9("resources/t9.txt");
		  Scanner sc = new Scanner(System.in);
		  int samples = sc.nextInt();
		  while (samples-- > 0) {
			  keyboard.print(sc.next());
		  }
		  sc.close();
	  } catch (IOException e) {
		  System.out.println("I/O error occurred.");
	  }
  }
}
