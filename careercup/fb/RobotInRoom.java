package careercup.fb;

import java.util.Scanner;

public class RobotInRoom {
	
	static class Node {
		Node l, r, u, d;
		boolean marked;
	}
	
	int count = 0;
	int sr = 0, sc = 0, rr = 0, rc = 0;
	
	int[] dr = { -1, 0, 1, 0 };
	int[] dc = { 0, -1, 0, 1 };
	
	int[][] robot = new int[][] {
			new int[] { 1, 1, 0, 0 },
			new int[] { 1, 0, 1, 1 },
			new int[] { 1, 1, 1, 0 },
			new int[] { 1, 0, 1, 0 }
	};
	
	public RobotInRoom(int r, int c) {
		sr = r;
		sc = c;
		rr = r;
		rc = c;
	}
	
	private Node find(Node n, int d) {
		if (n == null) return null;
		switch(d) {
		case 0: return n.u;
		case 1: return n.l;
		case 2: return n.d;
		default: return n.r;
		}
	}
	
	private Node navigate(Node f, int d) {
		if (null == f) return null;
		switch (d) {
		case 0: if (null == f.u) {
			f.u = newNode();
			f.u.d = f;
		}
		return f.u;
		
		case 1: if (null == f.l) {
			f.l = newNode();
			f.l.r = f;
		}
		return f.l;
		
		case 2: if (null == f.d) {
			f.d = newNode();
			f.d.u = f;
		}
		return f.d;
		
		default: if (null == f.r) {
			f.r = newNode();
			f.r.l = f;
		}
		return f.r;
		}
	}
	
	private void discover(Node n, int d) {
		if (!n.marked) {
			int rd = (d+2)%4;
			for (int i=0; i < 4; i++) {
				if (d > -1 && i == rd) continue;
				Node to = find(n, i);
				if (null != to && to.marked) continue;
				if (move(i)) {
					to = navigate(n, i);
					discover(to, i);
				}
			}
			n.marked = true;			
			if (move(rd)) navigate(n, rd);
		}
	}
	
	private Node newNode() {
		count++;
		return new Node();
	}
	
	public int area() {
		Node root = newNode();
		discover(root, -1);
		return count;
	}
	
	public  boolean move(int d) {
		int nr = rr+dr[d], nc = rc+dc[d];
		if (nr < 0 || nr >= robot.length) return false;
		if (nc < 0 || nc >= robot[0].length) return false;
		boolean res = (robot[nr][nc] == 1);
		if (res) {
			rr = nr;
			rc = nc;
		}
		return res;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		sc.close();
		
		RobotInRoom rir = new RobotInRoom(r, c);
		System.out.printf("Robot moves in a space of %d squares.\n", rir.area());
	}

}
