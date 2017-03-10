package hackerrank;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class FibonacciModified {

	static BigInteger fibonacci(int t1, int t2, int n) {
		BigInteger[] temp = new BigInteger[n];
		temp[0] = new BigInteger(toByteArr(t1));
		temp[1] = new BigInteger(toByteArr(t2));
		
		for (int i = 2; i < n; i++)
			temp[i] = temp[i-2].add(temp[i-1].pow(2));
		
		return temp[n-1];
	}
	
	static byte[] toByteArr(int i) {
		return ByteBuffer.allocate(4).putInt(i).array();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t1 = sc.nextInt();
		int t2 = sc.nextInt();
		int n = sc.nextInt();
		sc.close();
		
		BigInteger result = fibonacci(t1, t2, n);
		System.out.println(result.toString());
	}

}
