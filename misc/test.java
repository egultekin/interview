package interview;

public class test {
    static void StairCase(int n) {

        String strDiyez = new String();

        for (int i = 1; i <= n; i++) {
            int numberOfSpace = n - i;
            String str = (n!=i) ? String.format("%" + numberOfSpace + "s", " ") : "";
            strDiyez +="#";
            System.out.println(str + strDiyez);
        }

    }

    public static void main(String[] args) {

        StairCase(6);
    }
}
