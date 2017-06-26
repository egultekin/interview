package leetcode;

import java.util.Scanner;

public class KthSmallestLexicographically {
    public int check(int head, int c, int k, int n, int size) {
        c++;
        if (n >= head*10 && c < k) return check(head*10, c, k, n, size*10);
        if (k-c < size && head+k-c<=n) return (head+k-c)*-1;
        else return c+Math.min(size-1, n-head);
    }
    
    public int findKthNumber(int n, int k) {
        int c = 0;
        for (int i=1; i < 10; i++) {
            c++;
            if (n >= 10*i)
            for (int j=0; j < 10 && c < k && i*10+j <= n; j++) {
                int sub = check(i*10+j, c, k, n, 1);
                if (sub < 0) return sub*-1;
                c = sub;
            }
            if (c == k) return i;
        }
        
        return -1;
    }
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		sc.close();
		
		System.out.println(new KthSmallestLexicographically().findKthNumber(n, k));

	}

}
