package cci;

import java.io.*;
import java.util.HashMap;

/*
 * Implement an algorithm to check how many times does a given word occurs
 * in a given book. Optimize the algorithm for successive queries.
 * 
 * Assuming that the book is given in a text file, read the whole file and
 * store each identified word in a map. Each time a word repeated, increase
 * the number of occurrences counter in the map.
 * 
 * Time complexity: O(size of file)
 * Space complexity: O(2*size of file)
 */
class WordsInBook {
  private HashMap<String, Integer> cache;
  private String filename;
  public static final int BUFFER_SIZE = 50;

  public WordsInBook(String filename) {
	  this.filename = filename;
  }
  
  private HashMap<String, Integer> index(String filename) throws IOException {
    if (filename.length() == 0) throw new IllegalArgumentException("Invalid filename specified.");
    BufferedReader br = new BufferedReader(new FileReader(filename));
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    char[] buf = new char[BUFFER_SIZE], incomplete = null;
    // word starts at ws and ends at we
    int ws, we;
    // number of characters read
    int read;
    while ( (read = br.read(buf, 0, BUFFER_SIZE)) != -1 ) {
      ws = we = -1;
      for (int i = 0; i < read; i++)
        if (buf[i] != ' ' && buf[i] != '\n') {
        	if (ws == -1) ws = we = i;
        	we++;
        }
        else {
          int start = 0;
          int size = we-ws;
          if (size == 0 && null == incomplete) {
        	  ws = we = -1;
        	  continue;
          }
          char[] word;
          if (null != incomplete) {
            word = new char[size+incomplete.length];
            System.arraycopy(incomplete, 0, word, 0, incomplete.length);
            start = incomplete.length;
            incomplete = null;
          } else word = new char[size];
          System.arraycopy(buf, ws, word, start, size);
          String key = String.valueOf(word);
          word = null;
          if (!map.containsKey(key)) map.put(key, 1);
          else {
            int count = map.get(key);
            map.put(key, count+1);
          }
          ws = we = -1;
        }
      if (ws > -1) we = read;
      if (we > ws) {
    	  incomplete = new char[we-ws];
    	  System.arraycopy(buf, ws, incomplete, 0, we-ws);
      }
    }
    
    if (null != incomplete) {
        String key = String.valueOf(incomplete);
        if (!map.containsKey(key)) map.put(key, 1);
        else {
          int count = map.get(key);
          map.put(key, count+1);
        }
    }

    br.close();
    return map;
  }
  
  public int count(String key) throws IOException {
	  if (null == cache) cache = index(filename);
	  if (!cache.containsKey(key)) return 0;
	  return cache.get(key);
  }
  
  public static void main(String[] args) {
	  WordsInBook book = new WordsInBook("resources/book.txt");
	  
	  try {
		  System.out.printf("%s is used in the given book %d times.\n", "dog", book.count("dog"));
		  System.out.printf("%s is used in the given book %d times.\n", "forest", book.count("forest"));
		  System.out.printf("%s is used in the given book %d times.\n", "tree", book.count("tree"));
		  System.out.printf("%s is used in the given book %d times.\n", "pen", book.count("pen"));
	  } catch (IOException io) {
		  System.out.println("Error reading file.");
	  }
  }
}
