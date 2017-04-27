package cci;

import java.util.*;

class Mastermind {
  public static int[] findHits(String solution, String guess) {
    int h = 0, ph = 0;
    char[] s = solution.toCharArray(), g = guess.toCharArray();
    if (s.length != g.length) throw new IllegalArgumentException("Solution and guess should be same length.");
    Map<Character, Integer> ind = new HashMap<>();
    ind.put('G',0); ind.put('Y',1); ind.put('R',2); ind.put('B',3);
    int[] gyrb = new int[4];
    for (int i=0; i<s.length; i++)
    	if (g[i] != s[i]) gyrb[ind.get(s[i])]++;
    	else h++;
    	
    for (int i=0; i<g.length; i++)
      if (g[i] != s[i] && gyrb[ind.get(g[i])] > 0) {
        gyrb[ind.get(g[i])]--;
        ph++;
      }
    return new int[] {h, ph};
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int samples = sc.nextInt();
    while (samples-- > 0) {
      String solution = sc.next();
      String guess = sc.next();
      try {
      int[] h = Mastermind.findHits(solution, guess);
      System.out.printf("Solution: %s and Guess: %s has %d hits and %d pseudo-hits.\n", solution, guess, h[0], h[1]);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
    sc.close();
  }
}

//2
//RYGG GYBR
//RBRR GBYR
//// 1,2	2,1

//8
//RYBY GGGG
//RYBY GBGG
//RYBY GGBG
//RYBG GGBG
//RYBG GRBG
//RYBG RGBG
//GGGG YYYY
//RYBG RYBG


