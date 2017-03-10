package greedy;

public class Unblur {
	
	int[] deltaX = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
	int[] deltaY = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	
	public String[] original(String[] blurred) {
		if (null == blurred || blurred.length <= 0) throw new IllegalArgumentException("Invalid input");
		
		String[] original = new String[blurred.length];
		int[][] temp = new int[blurred.length][blurred[0].length()];
		for (int i=0; i < blurred.length; i++)
			for (int j=0; j < blurred[i].length(); j++)
				if (i==0 || i == blurred.length-1 || j == 0 || j == blurred[0].length()-1)
					temp[i][j] = 0;
				else temp[i][j] = -1;
		
		for (int i=0; i < blurred.length; i++)
			for (int j=0; j < blurred[i].length(); j++)
				replace(blurred, temp, i, j);
		
		for (int i=0; i < blurred.length; i++) {
			StringBuilder str = new StringBuilder();
			for (int j=0; j < blurred[i].length(); j++) {
				if (temp[i][j] == 0) str.append(".");
				else str.append("#");
				//if (j < blurred[i].length() - 1) str.append(",");
				original[i] = str.toString();
			}
		}
			
		return original;
	}
	
	private void replace(String[] blurred, int[][] temp, int i, int j) {
		if (!valid(temp, i-1, j-1)) return;
		char c = blurred[i-1].charAt(j-1);
		int v = c - '0';
		for (int delta = 0; delta < deltaX.length; delta++)
			if (valid(temp, i-1+deltaX[delta], j-1+deltaY[delta]) 
					&& temp[i-1+deltaX[delta]][j-1+deltaY[delta]] > 0)
				v--;
		if (v == 1) temp[i][j] = 1;
		else temp[i][j] = 0;
	}
	
	private boolean valid(int[][] temp, int i, int j) {
		if (i < 0 || i >= temp.length || j < 0 || j >= temp[i].length) return false;
		return true;
	}

	public static void main(String[] args) {
		String[] blurred = new String[] { "1233321000000000123332100000000000000000000",
				  "1244422233222332334343323322232112332223321",
				  "1255523344343443545343434434343233432334432",
				  "0033303455465775633011445546454355753457753",
				  "0033303333364543533011433336333364521346542",
				  "0033303455464532445343545546454355753446542",
				  "0022202344342200234343434434343233432323221",
				  "0011101233221100123332223322232112332211111" };
		
		String[] original = new Unblur().original(blurred);
		for (int i=0; i < original.length; i++)
			for (int j = 0; j < original[i].length(); j++)
			{
				System.out.print(original[i].charAt(j));
				if (j == original[i].length() - 1) System.out.println();
			}

	}

}
