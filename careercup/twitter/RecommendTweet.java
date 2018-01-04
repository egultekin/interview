import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class RecommendTweet {
  static class IntegerPair implements Comparable<IntegerPair> {
    int first, last;
    public IntegerPair(int f, int l) {
      first = f;
      last = l;
    }

    @Override
    public int compareTo(IntegerPair to) {
      if (last < to.last) return 1;
      else if (last > to.last) return -1;
      return 0;
    }
  }

  // person id, list of tweet id
  static Map<Integer, Set<Integer>> likes;
  // person id, list of person id
  static Map<Integer, Set<Integer>> follows;
  // tweet id, hit
  static Map<Integer, IntegerPair> tweetHits;

  public static List<Integer> getRecommendedTweets(int[][] followGraph,
    int[][] likeGraph, int targetUser, int minLikeThreshold) {
    List<Integer> recommended = new ArrayList<>();
    int dimF = followGraph.length;
    int dimL = likeGraph.length;
    if (dimF < 1 && dimL < 1) return recommended;
    follows = new HashMap<>();
    likes = new HashMap<>();
    tweetHits = new HashMap<>();
    // init maps
    for (int i=0; i < dimF; i++) {
      Set<Integer> followList = follows.get(followGraph[i][0]);
      if (null == followList) followList = new HashSet<>();
      followList.add(followGraph[i][1]);
      follows.put(followGraph[i][0], followList);
    }

    for (int i=0; i < dimL; i++) {
      Set<Integer> likeList = likes.get(likeGraph[i][0]);
      if (null == likeList) likeList = new HashSet<>();
      likeList.add(likeGraph[i][1]);
      likes.put(likeGraph[i][0], likeList);
    }
    // check if targetUser exists
    if (!follows.containsKey(targetUser)) return recommended;

    // process each user followed by targetUser
    Set<Integer> followList = follows.get(targetUser);
    Iterator<Integer> it = followList.iterator();
    while (it.hasNext()) processFollow(it.next());
    // sort the hits
    Object[] hits = tweetHits.values().toArray();
    Arrays.sort(hits);

    // filter the tweets to be recommended based on threshold
    for (int i=0; i < hits.length; i++) {
      IntegerPair hit = (IntegerPair) hits[i];
      if (hit.last < minLikeThreshold) break;
      recommended.add(hit.first);
      System.out.printf("Recommendation #%d: {%d} with count %d\n", recommended.size(), hit.first, hit.last);
    }
    return recommended;
  }

  private static void processFollow(int follow) {
    if (!likes.containsKey(follow)) return;
    Set<Integer> likeList = likes.get(follow);
    Iterator<Integer> it = likeList.iterator();
    while (it.hasNext()) {
      int tweet = it.next();
      IntegerPair hit = tweetHits.get(tweet);
      if (null == hit) hit = new IntegerPair(tweet, 1);
      else hit.last += 1;
      tweetHits.put(tweet, hit);
    }
  }

  public static void main(String[] args) {
    int[][] followGraph = new int[][] {
      new int[] {4, 5},
      new int[] {3, 5},
      new int[] {2, 5},
      new int[] {2, 3},
      new int[] {4, 2},
      new int[] {1, 3}
    };

    int[][] likeGraph = new int[][] {
      new int[] {1, 100},
      new int[] {1, 105},
      new int[] {2, 100},
      new int[] {2, 103},
      new int[] {2, 105},
      new int[] {3, 101},
      new int[] {4, 101},
      new int[] {5, 101},
      new int[] {5, 103},
      new int[] {5, 105}
    };

    getRecommendedTweets(followGraph, likeGraph, 4, 1);

  }
}
