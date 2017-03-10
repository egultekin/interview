package hackerrank;

import java.math.BigInteger;
import java.util.Scanner;

public class SubsetComponent {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        BigInteger[] d = new BigInteger[N];
        for (int i = 0; i < N; i++) d[i] = sc.nextBigInteger();
        sc.close();
        
        int[] histogram = new int[64];
        int notComponent = 0;
        for (int i = 0; i < N; i++) {
            if (d[i].compareTo(BigInteger.ZERO) == 0
            		|| d[i].subtract(BigInteger.ONE).and(d[i]).compareTo(BigInteger.ZERO) == 0) notComponent++;
            else
            for (int j = 0; j < 64; j++)
                histogram[j] += d[i].and(BigInteger.ONE.shiftLeft(j)).shiftRight(j).intValue();
        }
        
        BigInteger components = BigInteger.ZERO;
        BigInteger allSubsets = BigInteger.ONE.shiftLeft(N);
        for (int i=0; i < 64; i++)
            components = components.add(BigInteger.ONE.shiftLeft(N-histogram[i]));
        components = components.add(allSubsets.subtract(BigInteger.ONE.shiftLeft(notComponent)));
        
        System.out.println(components.toString());
    }

}
