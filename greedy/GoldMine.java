package greedy;

public class GoldMine {
	private static final int MAXMINERS = 6;
	private static final String SEPARATOR = ",";
	
	public int[] getAllocation(String[] mines, int miners) {
		return indices(mineAllocation(p(mines)), miners);
	}
	
	private float[][] p(String[] mines) {
		if (null == mines || mines.length == 0) throw new IllegalArgumentException("Invalid argument");
		float[][] result = new float[mines.length][MAXMINERS+1];
		for (int i = 0; i < mines.length; i++) {
			String[] p = mines[i].replace(" ", "").split(SEPARATOR);
			for (int j=0; j < MAXMINERS+1; j++)
				result[i][j] = (float)Integer.parseInt(p[j])/100;
		}
		return result;
	}
	
	private int[][] mineAllocation(float[][] p) {
		int[][] result = new int[p.length][MAXMINERS];
		for (int mine = 0; mine < p.length; mine++)
			for (int i = 1; i < MAXMINERS+1; i++)
				result[mine][i-1] = gain(p, mine, i);
		return result;
	}
	
	private int gain(float[][] p, int mine, int miner) {
		if (miner <= 0) return 0;
		return value(p, mine, miner) - value(p, mine, miner-1);
	}
	
	private int value(float[][] p, int mine, int miners) {
		if (mine < 0 || mine > p.length - 1 || miners > MAXMINERS) throw new IllegalArgumentException("Illegal argument");
		if (miners <= 0) return 0;
		int result = 0;
		for (int i = 0; i < MAXMINERS+1; i++) {
			if (i < miners) result += p[mine][i]*(i*50-(miners-i)*20);
			else if (i == miners) result += p[mine][i]*i*50;
			else result += p[mine][i]*miners*60;
		}
		
		return result;
	}
	
	private int[] indices(int[][] mineAllocation, int miners) {
		int[] result = new int[mineAllocation.length];
		
		for (int i = 0; i < miners; i++) {
			int max = Integer.MIN_VALUE;
			int maxIndex = -1;
			for (int mine = 0; mine < mineAllocation.length; mine++) {
				if (result[mine] == MAXMINERS) continue;
				if (mineAllocation[mine][result[mine]] > max) {
					max = mineAllocation[mine][result[mine]];
					maxIndex = mine;
				}
			}
			
			result[maxIndex]++;			
		}
		
		return result;
	}

	public static void main(String[] args) {
		String[] mines = {"026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", 
				"026, 012, 005, 013, 038, 002, 004"};
		int miners = 56;
		
		int[] result = new GoldMine().getAllocation(mines, miners);
		System.out.println("Result:");
		for (int i=0; i < result.length; i++)
			if (result[i] >=0) System.out.println(result[i]);

	}

}
