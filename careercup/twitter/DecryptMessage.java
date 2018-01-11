/*
* https://www.careercup.com/question?id=5633094794084352
*
*
*/
class DecryptMessage {
  static String key = "";
  static final String known = "Your friend, Alice";

  static void findKey(String cipherText) {
    String decodeText = cipherText.substring(cipherText.length()-known.length());
    char[] decodeArr = decodeText.toCharArray();
    char[] knownArr = known.toCharArray();
    StringBuilder keyBuilder = new StringBuilder();

    for (int i=0; i < decodeArr.length; i++) {
      if ((decodeArr[i] >= 'A' && decodeArr[i] <= 'Z') || (decodeArr[i] >= 'a' && decodeArr[i] <= 'z')) {
        int rotation = decodeArr[i]-knownArr[i];
        if (rotation < 0) rotation+=26;
        keyBuilder.append((char)rotation+'0');
      }
    }
    char[] multipleKeys = keyBuilder.toString().toCharArray();
    int i=multipleKeys.length-1;
    while (i > 1) {
      boolean check = true;
      for (int j=0; j < i; j++) {
        if (j+i >= multipleKeys.length || multipleKeys[j] != multipleKeys[j+i]) {
          check=false;
          break;
        }
      }
      if (check) break;
      i--;
    }
    for (int k=0; k<i; k++) key += multipleKeys[k];
    System.out.println(key);
  }

  public static void main(String[] args) {
    findKey("elwepuqyez. -Atvt hrqgse, Cnikg");
  }
}
