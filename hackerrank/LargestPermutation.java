package hackerrank;

import java.util.Scanner;

public class LargestPermutation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr = new int[N];
        int[] ind = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            ind[arr[i]-1] = i;
        }
        
        sc.close();
        
        int swapped = 0;
        int max = N;
        for (int i = 0; i < N && swapped < K; i++)
        	if (arr[i] <= max && ind[max-1] >= i) {
        		int temp = arr[i];
        		arr[i] = max;
        		arr[ind[max-1]] = temp;
        		ind[temp-1] = ind[max-1];
        		ind[max-1] = i;
        		if (temp < max) swapped++;
        		max--;
        	}
        
        StringBuilder build = new StringBuilder();
        for (int j = 0; j < N; j++)
            if (j < N-1) build.append(arr[j]).append(" ");
            else build.append(arr[j]);
            
        System.out.println(build.toString());

    }
    
}
