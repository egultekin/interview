package cci;

class SwapNumbersInPlace {
  public void swap(int a, int b) {
    System.out.printf("Before swap a=%d and b=%d\n", a, b);
    b = b-a;
    a = a+b;
    b = a-b;
    System.out.printf("After swap a=%d and b=%d\n", a, b);
  }

  public static void main(String[] args) {
    SwapNumbersInPlace snip = new SwapNumbersInPlace();
    snip.swap(3,5);
    snip.swap(-2, -7);
    snip.swap(0,1);
    snip.swap(2, -5);
  }
}
