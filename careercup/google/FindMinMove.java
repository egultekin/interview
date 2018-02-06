import java.util.Set;
import java.util.HashSet;

class FindMinMove {
	static int minFound;

	private static void dfs(String org, String targ, Set<String> visited, int level) {
		if (org.equals(targ)) {
			if (level < minFound) minFound = level;
			return;
		}

		visited.add(org);
		for (int i=1; i<org.length(); i++) {
			String variation = moveFront(org, i);
			if (!visited.contains(variation)) {
				for (int k=0; k<level;k++) System.out.print(" ");
				System.out.printf("%s is not found in visited set.\n", variation);
				dfs(variation, targ, visited, level+1);
			} else {
				for (int k=0; k<level;k++) System.out.print(" ");
				System.out.printf("Skipping %s\n", variation);
			}
		}
		visited.remove(org);
	}

	public static int find(String org, String targ) {
		minFound = Integer.MAX_VALUE;
		dfs(org, targ, new HashSet<String>(), 0);
		System.out.printf("Can be found moving %d steps\n", minFound);
		return minFound;
	}

	private static String moveFront(String s, int t) {
		StringBuilder sb = new StringBuilder();
		int len = s.length();
		int i=0;
		sb.append(s.charAt(t));
		while (i < len) {
			if (i != t) sb.append(s.charAt(i));
			i++;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		FindMinMove.find("abc", "cba");
	}
}