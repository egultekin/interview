package hackerrank;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class MaximumPerimeterTriangle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        Vector<Long> vec = new Vector<>();
        for (int i = 0; i < n; i++) vec.add((long)sc.nextInt());
        sc.close();
        
        Collections.sort(vec);
        long max = -1;
        long[] result = new long[3];
        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                for(int k = j+1; k < n; k++)
                    if ((vec.elementAt(i)+vec.elementAt(j)) > vec.elementAt(k) && (vec.elementAt(i)+vec.elementAt(j)+vec.elementAt(k)) > max) {
            max = vec.elementAt(i)+vec.elementAt(j)+vec.elementAt(k);
            result[0] = vec.elementAt(i);
            result[1] = vec.elementAt(j);
            result[2] = vec.elementAt(k);
        }
        
        if (max == -1) System.out.println(-1);
        else {
            System.out.printf("%d %d %d\n", result[0], result[1], result[2]);
        }
    }

}
