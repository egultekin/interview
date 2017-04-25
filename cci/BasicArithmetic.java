package cci;

class BasicArithmetic {
  static int subtract(int a, int b) {
    if (b == 0) return a;
    int increment = -1;
    if (b < 0) increment = 1;
    int result = 0;
    while (b != 0) {
      result = result+increment;
      b = b+increment;
    }
    return a + result;
  }

  static int multiply(int a, int b) {
    if (a == 0 || b == 0) return 0;
    boolean positive = true;
    int increment = a, incB = -1;

    if (a < 0 && b > 0) {
      positive = false;
      increment = subtract(0, a);
    } else if (a > 0 && b < 0) {
      positive = false;
      incB = 1;
    } else if (a < 0 && b < 0) {
      incB = 1;
      increment = subtract(0, a);
    }

    int result = 0;
    while (b != 0) {
      result = result+increment;
      b = b+incB;
    }
    if (!positive) return subtract(0, result);
    return result;
  }

  static int divide(int a, int b) {
    if (b == 0) throw new RuntimeException("Division by zero.");
    if (a == 0) return 0;
    boolean positive = true;
    int increment = b, compare = b;
    if (a < 0 && b > 0) {
      positive = false;
      a = subtract(0, a);
      increment = subtract(0, b);
    } else if (a > 0 && b < 0) {
    	positive = false;
    	compare = subtract(0, b);
    }
    else if (a < 0 && b < 0) {
    	a = subtract(0, a);
    	compare = subtract(0, b);
    }
    else increment = subtract(0, b);

    int result = 0;
    while (a > compare) {
      result = result+1;
      a = a+increment;
    }

    if (!positive) return subtract(0, result);
    return result;
  }

  public static void main(String[] args) {
    System.out.printf("Result of subtract(-5,-2) is %d.\n", subtract(-5, -2));
    System.out.printf("Result of subtract(5,2) is %d.\n", subtract(5, 2));
    System.out.printf("Result of subtract(-4,0) is %d.\n", subtract(-4, 0));
    System.out.printf("Result of subtract(5,-2) is %d.\n", subtract(5, -2));
    System.out.printf("Result of subtract(0,4) is %d.\n", subtract(0, 4));
    System.out.printf("Result of -5*-2 is %d.\n", multiply(-5, -2));
    System.out.printf("Result of -5*2 is %d.\n", multiply(-5, 2));
    System.out.printf("Result of 5*-2 is %d.\n", multiply(5, -2));
    System.out.printf("Result of 5*2 is %d.\n", multiply(5, 2));
    System.out.printf("Result of 0*-2 is %d.\n", multiply(0, -2));
    System.out.printf("Result of 5/2 is %d.\n", divide(5, 2));
    System.out.printf("Result of 0/2 is %d.\n", divide(0, 2));
    System.out.printf("Result of 4/5 is %d.\n", divide(4, 5));
    System.out.printf("Result of -7/-2 is %d.\n", divide(-7, -2));
    System.out.printf("Result of 7/-2 is %d.\n", divide(7, -2));
    System.out.printf("Result of -5/2 is %d.\n", divide(-5, 2));
    try {
      System.out.printf("Result of 5/0 is %d.\n", divide(5, 0));
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

  }
}
