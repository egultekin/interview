package hackerrank;

import java.util.LinkedList;
import java.util.Scanner;

public class GoodlandElectricity {

	public static boolean hasViolation(LinkedList<Integer> stack, int i, int k) {
		return (!stack.isEmpty() && i >= stack.peek()+2*k) || (stack.isEmpty() && i >= k);
	}
	
	public static boolean better(int[] input, int i, LinkedList<Integer> s, int k, int n) {
		boolean result = false;
		if (input[i] <= 0) return false;
		if (s.isEmpty()) return i < k;
		int challenge = s.pop();
		if (s.isEmpty() && i < k) result = true;
		if (!s.isEmpty() && i < s.peek()+2*k) result = true;
		if (challenge+k >= n) result = false;
		s.push(challenge);
		return result;
	}
	
	public static boolean violates(int[] input, int i, LinkedList<Integer> s, int k, int n) {
		if (s.isEmpty()) 
			return i >= k;
		if (i == n-1 && input[n-1] != 1 && s.peek()+k <= n-1) return true;
		if (s.peek() + 2*k <= i) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int k = sc.nextInt();
		int[] input = new int[N];
		for (int i = 0; i < N; i++) input[i] = sc.nextInt();
		sc.close();

		LinkedList<Integer> stack = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && better(input, i, stack, k, N)) 
				stack.pop();
			
			if (violates(input, i, stack, k, N)) {
				stack.clear();
				break;
			}
			else if (input[i] > 0) {
				stack.push(i);
				if (i + k > N-1) break;
			}
		}
		
		if (stack.isEmpty()) System.out.println("-1");
		else System.out.println(stack.size());
	}

}
