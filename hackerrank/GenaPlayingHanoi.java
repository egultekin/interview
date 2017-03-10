package hackerrank;

import java.util.Scanner;

public class GenaPlayingHanoi {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int a[] = new int[N];
        for(int a_i=0; a_i < N; a_i++){
            a[a_i] = in.nextInt();
        }
        
        in.close();
    }

}
