import java.util.Arrays;
import java.util.LinkedList;

class RobotWalk {
  static int minWalk(int[][] grid) {
    int h = grid.length;
    if (h < 1) return 0;
    int w = grid[0].length;
    if (w < 1) return 0;

    int[][] min = new int[h][w];
    int[][] back = new int[h][w];
    for (int i=0; i < h; i++) Arrays.fill(min[i], Integer.MAX_VALUE);
    min[0][0] = 0;

    for (int r=0; r < h; r++)
    for (int c=0; c < w; c++) {
      if (r > 0) {
        int compareTo = (grid[r][c] > grid[r-1][c]) ? (grid[r][c]-grid[r-1][c]) : 0;
        compareTo += min[r-1][c];
        if (compareTo < min[r][c]) {
          min[r][c] = compareTo;
          back[r][c] = (r-1)*w+c;
        }
      }

      if (c > 0) {
        int compareTo = (grid[r][c] > grid[r][c-1]) ? (grid[r][c]-grid[r][c-1]) : 0;
        compareTo += min[r][c-1];
        if (compareTo < min[r][c]) {
          min[r][c] = compareTo;
          back[r][c] = r*w+c-1;
        }
      }
    }

    int r = h-1;
    int c = w-1;
    LinkedList<String> stack = new LinkedList<>();
    stack.addFirst(String.format("(%d,%d)", r, c));
    while (back[r][c] != 0) {
      int ir = back[r][c]/w;
      int ic = back[r][c]%w;
      stack.addFirst(String.format("(%d,%d)", ir, ic));
      r = ir;
      c = ic;
    }
    System.out.printf("(0,0)");
    while (!stack.isEmpty()) {
      System.out.printf("-%s", stack.pollFirst());
    }
    System.out.println();
    return min[h-1][w-1];
  }

  public static void main(String[] args) {
    int[][] grid = new int[][] {
      new int[] { 3, 5, 2 },
      new int[] { 4, 2, 8 },
      new int[] { 1, 5, 9 }
    };

    int result = minWalk(grid);
    System.out.printf("Min path costs %d.\n", result);

    grid = new int[][] {
      new int[] { 5, 6, 2 },
      new int[] { 4, 4, 8 },
      new int[] { 1, 3, 1 }
    };

    result = minWalk(grid);
    System.out.printf("Min path costs %d.\n", result);
  }
}
