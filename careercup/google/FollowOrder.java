import java.util.Map;
import java.util.HashMap;

class FollowOrder {
	public static boolean followsOrder(String order, String str) {
		Map<Character, Integer> indices = new HashMap<>();
		char[] orderArr = order.toCharArray();
		for (int i=0; i<orderArr.length; i++)
			indices.put(orderArr[i], i);
		int monInc = -1;
		for (int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if (indices.containsKey(ch)) {
				if (indices.get(ch) < monInc) return false;
				else monInc = indices.get(ch);
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String order = "abc";
		System.out.println(true == followsOrder(order, "aaa"));
		System.out.println(false == followsOrder(order, "cba"));
		System.out.println(true == followsOrder(order, "aaxyc"));
		System.out.println(true == followsOrder(order, "aaxycb"));
	}
}