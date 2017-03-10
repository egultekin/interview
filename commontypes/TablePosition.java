package commontypes;

public class TablePosition {
	private int row, column;
	
	public TablePosition(int r, int c) {
		this.row = r;
		this.column = c;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof TablePosition 
				&& ((TablePosition)obj).C() == this.column 
				&& ((TablePosition)obj).R() == this.row;
	}
	
	public int R() {
		return this.row;
	}
	
	public int C() {
		return this.column;
	}
}
