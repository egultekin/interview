package brutefbacktrack;

public class AllPermutations {

	public void print(String input, int swap) {
		if (null == input || input.length() == 0 || swap < 0 || swap > input.length()-1) return;
		if (swap == input.length()-1) System.out.println(input);
		for (int i=swap; i < input.length(); i++) print(swap(input, swap, i), swap+1);
	}
	
	private String swap(String input, int replace, int with) {
		if (null == input || replace < 0 || with < 0 || replace > with || with > input.length()-1) 
			throw new IllegalArgumentException("Invalid argument.");
		StringBuilder build = new StringBuilder();
		char temp = input.charAt(replace);
		if (replace > 0) build.append(input.substring(0, replace));
		build.append(input.charAt(with));
		if (replace < with) {
			build.append(input.substring(replace+1, with));
			build.append(temp);
		}
		build.append(input.substring(with+1));
		return build.toString();
	}
	
	public static void main(String[] args) {
		String input = "DEFG";
		new AllPermutations().print(input, 0);
	}

}
