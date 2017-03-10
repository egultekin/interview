package hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class JimAndTheOrders {

    static class Order {
        public Integer ind;
        public Integer finish;
        
        public Order(int i, int f) {
            ind = i;
            finish = f;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<JimAndTheOrders.Order> arrList = new ArrayList<>();
        for (int i=0; i < N; i++)
            arrList.add(new JimAndTheOrders.Order(i+1, sc.nextInt()+sc.nextInt()));
        sc.close();
        
        Collections.sort(arrList, new Comparator<JimAndTheOrders.Order>() {

            @Override
            public int compare(final JimAndTheOrders.Order a, final JimAndTheOrders.Order b) {
                int comp = a.finish.compareTo(b.finish);
                if (comp == 0) comp = a.ind.compareTo(b.ind);
                return comp;
            }
        });
        
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < N; i++)
            if (i < N-1) str.append(arrList.get(i).ind).append(" ");
            else str.append(arrList.get(i).ind);
        System.out.println(str.toString());
    }

}
