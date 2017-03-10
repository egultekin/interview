package bfs;

import java.util.LinkedList;

import commontypes.TablePosition;

public class CaptureThemAll {
	
	private LinkedList<TablePosition> queue;
	private boolean[][] marked;
	private static final int[] nextRow = { -2, -2, -1, -1, 1, 1, 2, 2};
	private static final int[] nextCol = { -1, 1, -2, 2, -2, 2, -1, 1};
	
	public int fastKnight(String knight, String rook, String queen) {
		queue = new LinkedList<TablePosition>();
		marked = new boolean[8][8];
		int steps = 0, beaten = 0;
		
		TablePosition k = asTablePosition(knight);
		TablePosition r = asTablePosition(rook);
		TablePosition q = asTablePosition(queen);
		
		queue.addLast(k);
		marked[k.R()][k.C()] = true;
		int popsForNextStep = queue.size();
		
		while (!queue.isEmpty() && beaten < 2) {
			if (popsForNextStep == 0) {
				steps++;
				popsForNextStep = queue.size();
			}
			
			TablePosition now = queue.poll();
			popsForNextStep--;
			
			if (now.equals(r) || now.equals(q)) {
				if (beaten == 0) {
					queue.clear();
					popsForNextStep=0;
					marked = new boolean[8][8];
					marked[now.R()][now.C()]=true;
				}
				
				beaten++;
			}
			
			for (TablePosition next : nextPositions(now)) {
				if (valid(next) && !marked[next.R()][next.C()]) {
					queue.addLast(next);
					marked[next.R()][next.C()] = true;
				}
			}
		
		}
	
		return steps;
	}
	
	private boolean valid(TablePosition check) {
		if (null != check && check.C() >= 0 && check.C() < 8 && check.R() >= 0 && check.R() < 8) return true;
		return false;
	}
	
	private Iterable<TablePosition> nextPositions(TablePosition current) {
		LinkedList<TablePosition> next = new LinkedList<TablePosition>();
		
		for (int i = 0; i < nextRow.length; i++) {
			next.add(new TablePosition(current.R()+nextRow[i], current.C()+nextCol[i]));
		}
		
		return next;
	}
	
	private TablePosition asTablePosition(String chessPosition) {
		TablePosition result = null;
		
		try {
			int column = chessPosition.charAt(0) - 'a';
			int row = Integer.parseInt(chessPosition.substring(1)) - 1;
			
			result = new TablePosition(row, column);
			
		} catch (NumberFormatException nfe) {
			result = null;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String knight = "b1", rook = "c3", queen = "a3";
		System.out.println(new CaptureThemAll().fastKnight(knight, rook, queen));
	}

}
