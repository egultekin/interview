package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class BeautifulPairs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) A[i] = sc.nextInt();
        for (int i = 0; i < N; i++) B[i] = sc.nextInt();
        sc.close();
        
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0, j = 0;
        int count = 0;
        int b1 = -1, a1 = -1;
        while (i < N && j < N) {
            if (A[i] == B[j]) {
                count++;
                i++;
                j++;
            } else if (A[i] > B[j]) b1 = j++;
            else a1 = i++;
        }
        
        if (a1 == -1 && b1 == -1) count--;
        else count++;

        System.out.println(count);
    }

}
