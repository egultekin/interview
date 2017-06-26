package leetcode;

import java.util.PriorityQueue;

public class TrappingRainWaterII {

    static class Node implements Comparable<Node> {
        int r, c, cap;
        
        public Node(int row, int col, int capacity) {
            r = row;
            c = col;
            cap = capacity;
        }
        
        @Override
        public int compareTo(Node t) {
            if (cap > t.cap) return 1;
            else if (cap < t.cap) return -1;
            return 0;
        }
    }
    
    public int trapRainWater(int[][] heightMap) {
        int[] dr = new int[] {-1, 1, 0, 0};
        int[] dc = new int[] {0, 0, -1, 1};
        int h = heightMap.length;
        if (h < 2) return 0;
        int w = heightMap[0].length;
        if (w < 2) return 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        boolean[][] v = new boolean[h][w];
        
        for (int c=0; c < w; c++) {
            pq.add(new Node(0, c, heightMap[0][c]));
            pq.add(new Node(h-1, c, heightMap[h-1][c]));
            v[0][c] = true;
            v[h-1][c] = true;
        }
        
        for (int r=1; r < h-1; r++) {
            pq.add(new Node(r, 0, heightMap[r][0]));
            pq.add(new Node(r, w-1, heightMap[r][w-1]));
            v[r][0] = true;
            v[r][w-1] = true;
        }
        
        int sum = 0;
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            for (int i=0; i < 4; i++) {
                int pr = n.r+dr[i];
                int pc = n.c+dc[i];
                if (pr >= 0 && pr < h && pc >= 0 && pc < w && !v[pr][pc]) {
                    v[pr][pc] = true;
                    int nh = heightMap[pr][pc];
                    if (nh < n.cap) {
                        sum += n.cap-nh;
                        nh = n.cap;
                    }
                    pq.add(new Node(pr, pc, nh));
                }
            }
        }
        
        return sum;
    }
	
	public static void main(String[] args) {
		TrappingRainWaterII tw = new TrappingRainWaterII();
		int[][] heightMap = new int[][] {
				new int[] {1, 4, 3, 1, 3, 2},
				new int[] {3, 2, 1, 3, 2, 4},
				new int[] {2, 3, 3, 2, 3, 1}
		};
		System.out.println(tw.trapRainWater(heightMap));
	}

}
