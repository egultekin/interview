package hackerrank;

import java.util.Scanner;

public class SherlockAndTheBeast {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();           
            int three = 0;
 
            while (n % 3 > 0 && n >= 5) {
                three+=5;
                n -= 5;
            }
            
            if (n % 3 > 0) System.out.println(-1);
            else {
                StringBuilder b = new StringBuilder();
                while (n-- > 0) b.append("5");
                while (three-- > 0) b.append("3");
                System.out.println(b.toString());
            }
        }
    }

}
