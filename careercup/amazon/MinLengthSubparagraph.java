import java.util.*;

class MinLengthSubParagraph {
  public static void findMin(Set<String> words, String par) {
    Map<String, Integer> wordCount = new HashMap<>();
    Set<String> missing = new HashSet<>(words);
    String[] parWords = par.split(" ");
    int n = parWords.length;
    int slow, fast, minDist, minSlow, minFast;
    slow = minSlow = 0;
    fast = 0;
    minDist = n+1;
    minFast = n;

    while (fast < n) {
      if (!missing.isEmpty()) {
        String check = parWords[fast];
        int count = 1;
        if (words.contains(check)) {
          if (wordCount.containsKey(check)) count += wordCount.get(check);
          wordCount.put(check, count);
          if (missing.contains(check)) {
            //System.out.printf("Found missing word %s\n", check);
            missing.remove(check);
            //if (missing.isEmpty()) System.out.println("Missing list is empty.");
            //else fast++;
            if (!missing.isEmpty()) fast++;
          } else fast++;
        } else fast++;
      }

      if (missing.isEmpty()) {
        String check = parWords[slow];
        if (words.contains(check)) {
          int count = wordCount.get(check);
          count--;
          wordCount.put(check, count);
          if (count == 0) {
            if (fast-slow < minDist) {
              minDist = fast-slow;
              minSlow = slow;
              minFast = fast;
            }
            missing.add(check);
            fast++;
          }
        }
        slow++;
      }
    }

    if (minDist == n+1) System.out.println("No solution exists.");
    else System.out.printf("Min length subparagraph is found between indices %d and %d\n", minSlow, minFast);
  }

  public static void main(String[] args) {
    Set<String> set = new HashSet<>(Arrays.asList("ali", "veli", "kirkdokuz", "elli"));
    findMin(set, "kemal mustafa ali kirkdokuz ali yas elli veli veli");
    findMin(set, "kemal mustafa ali kirkdokuz ali yas elli veli kirkdokuz veli ali");
    findMin(set, "kemal mustafa ali kirkdokuz ali yas elli veli kirkdokuz ali");
    findMin(set, "");
    findMin(set, "ali veli kirkdokuz elli osman mehmet erhan harun");
  }
}
