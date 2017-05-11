package cci;

import java.util.*;

class PondSizes {
  private int[][] area;
  private int rows, columns;
  private final int DSIZE = 9;
  private final int[] dCol = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
  private final int[] dRow = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
  private int[][] mark;

  private void mark(int m, int r, int c) {
    for (int i=0; i < DSIZE; i++) {
      int row = r+dRow[i];
      int col = c+dCol[i];
      if (row >= 0 && row < rows && col >= 0 && col < columns && mark[row][col] == 0 && area[row][col] == 0) {
        mark[row][col] = m;
        mark(m, row, col);
      }
    }
  }

  private int markArea() {
    int m = 1;
    for (int r=0; r < rows; r++)
      for (int c=0; c < columns; c++)
      if (mark[r][c] == 0 && area[r][c] == 0) {
        mark(m, r, c);
        m++;
      }
    return m-1;
  }

  public int[] ponds(int[][] a) {
    area = a;
    rows = a.length;
    columns = (a.length > 0) ? a[0].length : 0;
    if (rows == 0 || columns == 0) return new int[] {0};
    mark = new int[rows][columns];
    int s = markArea();
    if (s == 0) return new int[] {0};
    int[] result = new int[s];
    for (int r=0; r < rows; r++)
      for (int c=0; c < columns; c++)
        if (mark[r][c] > 0) result[mark[r][c]-1]++;
    return result;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int rows = sc.nextInt();
    int columns = sc.nextInt();
    int[][] arr = new int[rows][columns];
    for (int r=0; r < rows; r++)
      for (int c=0; c < columns; c++)
        arr[r][c] = sc.nextInt();
    sc.close();

    PondSizes ps = new PondSizes();
    int[] result = ps.ponds(arr);
    StringBuilder builder = new StringBuilder();
    builder.append(result[0]);
    for (int i=1; i < result.length; i++) builder.append(',').append(result[i]);
    System.out.println(builder.toString());
  }
}

//4 4
//0 2 1 0
//0 1 0 1
//1 1 0 1
//0 1 0 1

//4 4
//1 2 3 4
//5 6 7 8
//1 2 3 4
//5 6 7 8