package hackerrank;
 
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
 
public class ShortPalindromeOld {
 
       public static void main(String[] args) {
             Scanner sc = new Scanner(System.in);
             String s = sc.next();
             int N = s.length();
             sc.close();
            
             int[] str = new int[N];
             int mod = 1000000007;
             HashMap<Integer, Integer> indices = new HashMap<>();
            
             long total = 0;
             int map = 0;
             HashMap<Integer, Integer> chars = new HashMap<>();
             for (int i = 0; i < N; i++) {
            	 str[i] = s.charAt(i)-'a';
            	 if (!indices.containsKey(str[i])) {
            		 chars.put(map, str[i]);
            		 indices.put(str[i], map++);
            	 }
             }
             
             // count of char j before the index i (excluding the one at index)
             int[][] fc = new int[N][map];
             // count of char j after the index i (excluding the one at index)
             int[][] bc = new int[N][map];
             // index of next char j after the index i
             int[][] next = new int[N][map];
             // count of 2-char substrings (str.charAt(i), j)
             int[][] subs = new int[N][map*map];
             
             for (int i = 0; i < N; i++)
            	 Arrays.fill(next[i], Integer.MAX_VALUE);

            
             for (int i = 1; i < N; i++) {
            	 int chNi = str[N-i];
            	 int chI = str[i-1];
            	 for (int j = 0; j < indices.size(); j++) {
            		 fc[i][j] = fc[i-1][j]+((chI == chars.get(j)) ? 1 : 0);
            		 bc[N-i-1][j] = bc[N-i][j]+((chNi == chars.get(j)) ? 1 : 0);
            		 next[N-i-1][j] = ((chNi == chars.get(j)) ? N-i : next[N-i][j]);
            	 }                  
             }
            
             for (int i = 1; i < N; i++) {
                    //subs[N-i] = new int[26*26];
                    subs[N-i-1] = Arrays.copyOf(subs[N-i], subs[N-i].length);
                    int indexBase = indices.get(str[N-i-1])*map;              
                    for (int j = 0; j < indices.size(); j++)
                           subs[N-i-1][indexBase+j] = (bc[N-i-1][j]+subs[N-i][indexBase+j])%mod;
             }
            
             for (int i = 1; i < N; i++) {
            	 long cc = 0;
            	 int ch = indices.get(str[i]);
            	 int nextIndex = next[i][ch];
            	 if (nextIndex != Integer.MAX_VALUE)
            		 for (int j = 0; j < indices.size(); j++)
            			 cc = (cc%mod + ((fc[i][j]%mod)*(subs[nextIndex][map*ch+j]%mod)%mod))%mod;
            	 total = (total%mod + cc%mod)%mod;
             }

             System.out.println(total);
             System.out.println(N);
       }
 
}