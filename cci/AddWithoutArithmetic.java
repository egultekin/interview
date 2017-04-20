package cci;

class AddWithoutArithmetic {
  public int add(int a, int b) {
    int xor = a^b;
    while ((a&b) != 0) {
      int carry = a&b;
      a = xor;
      b = carry<<1;
      xor = a^b;
    }
    return xor;
  }

  public static void main(String[] args) {
    AddWithoutArithmetic aw = new AddWithoutArithmetic();
    System.out.println(aw.add(3,5));
    System.out.println(aw.add(-1,6));
    System.out.println(aw.add(-7,5));
    System.out.println(aw.add(-2,-2));
    System.out.println(aw.add(1,-5));
    System.out.println(aw.add(3,3));
  }
}
