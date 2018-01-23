import java.util.Iterator;
import java.util.HashMap;
import java.util.Arrays;

class LongestNonRepeatingStream {
  public static int find(Iterator<Character> it) {
  	int max = 0;
  	int maxl = 0;
  	int processed = 0;
  	int count = 0;
  	HashMap<Character, Integer> last = new HashMap<>();

  	while (it.hasNext()) {
  		Character next = it.next();

  		if (!last.containsKey(next)) {
  			last.put(next, count);
  		} else {
  			if (max < count-processed) {
  				maxl = processed;
  				max = count-processed;
  			}
  			processed = Math.max(processed, last.get(next)+1);
  			last.put(next, count);
  		}

  		count++;
  	}

    if (max < count-processed) {
      maxl = processed;
      max = count-processed;
    }
    
    System.out.printf("Longest substring starts at %d and has length %d.\n", maxl, max);
  	return max;
  }

  public static void main(String[] args) {
    Iterator<Character> it = Arrays.asList(new Character('a'), new Character('b'), new Character('c'),
    new Character('d'),new Character('q'),new Character('s'),new Character('c'),new Character('e'),
    new Character('d'),new Character('f')).iterator();
    find(it);

    Iterator<Character> it1 = Arrays.asList(new Character('a'), new Character('b'), new Character('c'),
    new Character('a'),new Character('f'),new Character('d'),new Character('c'),new Character('b')).iterator();
    find(it1);

    Iterator<Character> it2 = Arrays.asList(new Character('a'), new Character('b'), new Character('c'),
    new Character('d'),new Character('e'),new Character('f'),new Character('g'),new Character('h')).iterator();
    find(it2);
  }
}
