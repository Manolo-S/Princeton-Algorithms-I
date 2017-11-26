import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;

public class Percolation {

  private int openSitesCount;
  private final int[][] grid;
  private final WeightedQuickUnionUF wquf;
  private final int gridSize;
  private final int virtualBottomSite;
  private final int virtualTopSite;

  public Percolation(int n) {
    gridSize = n;
    openSitesCount = 0;
    grid = new int[n + 1][n + 1];
    initializeGrid();
    wquf = new WeightedQuickUnionUF(n * n + 2);
    virtualTopSite = 0;
    virtualBottomSite = n * n + 1;
  }

  public void open(int row, int col) {
    if (isOpen(row, col)) {
      return;
    }
    grid[row][col] = 1;
    openSitesCount++;
    if (row == 1) {
      connectToVirtualTop(row, col);
    }
    if (row == gridSize) {
      connectToVirtualBottom(row, col);
    }
    connectToOpenNeighbors(row, col);
  }

  private void connectToVirtualTop(int row, int col) {
    int site = rowColTo1D(row, col);
    wquf.union(site, virtualTopSite);
  }


  private void connectToVirtualBottom(int row, int col) {
    int site = rowColTo1D(row, col);
    wquf.union(site, virtualBottomSite);
  }

  private void connectToOpenNeighbors(int row, int col) {
    int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int site = rowColTo1D(row, col);
    for (int[] neighbor : neighbors) {
      int r = row + neighbor[0];
      int c = col + neighbor[1];
      if (r > 0 && r <= gridSize && c > 0 && c <= gridSize && isOpen(r, c)) {
        int neighborSite = rowColTo1D(r, c);
        wquf.union(site, neighborSite);
      }
    }
  }

  public boolean isOpen(int row, int col) {
    return grid[row][col] == 0 ? false : true;
  }

  public boolean isFull(int row, int col) {
    int site = rowColTo1D(row, col);
    return wquf.connected(site, virtualTopSite);
  }

  public int numberOfOpenSites() {
    return openSitesCount;
  }

  public boolean percolates() {
    return wquf.connected(virtualTopSite, virtualBottomSite);
  }

  private void initializeGrid() {
    for (int[] row : grid) {
      Arrays.fill(row, 0);
    }
  }

  private int rowColTo1D(int row, int col) {
    validateCoordinates(col, row);
    return (row - 1) * gridSize + col;
  }

  private void validateCoordinates(int row, int col) {
    if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
      throw new IllegalArgumentException();
    }
  }

  private boolean connected(int site1, int site2) {
    return wquf.connected(site1, site2);
  }

  public static void main(String[] args) {
  }
}
