package hackerrank;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class LuckBalance {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int maxLuck = 0;
		LinkedList<Integer> luckList = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			int luck = sc.nextInt();
			int importance = sc.nextInt();
			if (importance == 0) maxLuck += luck;
			else luckList.add(luck);
		}
		sc.close();
		
		Collections.sort(luckList, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 == o2) return 0;
				else if (o2 > o1) return 1;
				else return -1;
			}
		});
		
		int importantLost = 0;
		for (Integer next : luckList) {
			if (importantLost < K) maxLuck += next;
			else maxLuck -= next;
			importantLost++;
		}
		
		System.out.println(maxLuck);
	}

}
