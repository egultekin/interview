package interview;

import java.util.ArrayList;
import java.util.List;

public class DistinctPathsInRoadBlocksDP {
	class Block {
		int row1;
		int col1;
		int row2;
		int col2;
		
		public Block(int r1, int c1, int r2, int c2) {
			this.row1 = r1;
			this.col1 = c1;
			this.row2 = r2;
			this.col2 = c2;
		}
		
		@Override
		public boolean equals(Object obj) {
			try {
				Block block = (Block) obj;
				
				if (((block.col1 == this.col1 && block.row1 == this.row1) && (block.col2 == this.col2 && block.row2 == this.row2))
						|| ((block.col2 == this.col1 && block.row2 == this.row1) && (block.col1 == this.col2 && block.row1 == this.row2)))
					return true;
			} catch (Exception e) {}
			
			return false;
		}
	}
	
	int w;
	int h;
	List<Block> unavailableBlocks;
	long[][] distinct;
	
	private boolean unavailable(int r1, int c1, int r2, int c2) {
		for (Block block : this.unavailableBlocks) 
			if (block.equals(new Block(r1, c1, r2, c2))) return true;
		return false;
	}
	
	private void initBadList(String[] bad) {
		for (int i=0; i<bad.length; i++) {
			String[] corners = bad[i].split(" ");
			if (corners.length == 4) {
				this.unavailableBlocks.add(
						new Block(
								Integer.parseInt(corners[1]), 
								Integer.parseInt(corners[0]), 
								Integer.parseInt(corners[3]), 
								Integer.parseInt(corners[2])
						));
			}
		}
	}
	
	public DistinctPathsInRoadBlocksDP() {
		this.unavailableBlocks = new ArrayList<Block>();
	}
	
	public long getDistinctPaths(int width, int height, String[] unavailable) {
		this.w = width+1;
		this.h = height+1;
		this.initBadList(unavailable);
		
		this.distinct = new long[this.w][this.h];
		for (int i=0; i<this.h; i++)
			for (int j=0; j<this.w; j++)
				this.distinct[i][j] = 0;
		
		this.distinct[0][0] = 1;
		for (int i=0; i<this.h; i++)
			for (int j=0; j<this.w; j++) {
				if (i > 0 && !unavailable(i, j, i-1, j)) distinct[i][j]+=distinct[i-1][j];
				if (j > 0 && !unavailable(i, j, i, j-1)) distinct[i][j]+=distinct[i][j-1];
			}
		
		return this.distinct[height][width];
	}

	public static void main(String[] args) {
		int width = 5;
		int height = 5;
		DistinctPathsInRoadBlocksDP dpirb = new DistinctPathsInRoadBlocksDP();
		
		long distinctPathCount = dpirb.getDistinctPaths(width, height, 
				new String[] { "0 0 0 1", "5 5 4 5" });
		
		System.out.format("Number of distinct ways from {%d, %d} to {%d, %d} is %d.", 
				0,0,
				width, height,
				distinctPathCount);

	}

}
