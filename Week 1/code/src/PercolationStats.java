import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private final int gridSize;
  private final int trials;
  private final double[] thresholds;

  public PercolationStats(int n, int trials) {
    this.gridSize = n;
    this.trials = trials;
    this.thresholds = runTrials();
  }

  private double[] runTrials() {
    int row;
    int column;
    boolean gridPercolates;
    double threshold;
    double thresholds[] = new double[trials];
    for (int i = 0; i < trials; i++) {
      int openSitesCount = 0;
      Percolation grid = new Percolation(gridSize);
      gridPercolates = false;
      while (!gridPercolates) {
        row = StdRandom.uniform(1, gridSize + 1);
        column = StdRandom.uniform(1, gridSize + 1);
        if (!grid.isOpen(row, column)) {
          grid.open(row, column);
          openSitesCount++;
          gridPercolates = grid.percolates();
        }
      }
      threshold = openSitesCount / (double) (gridSize * gridSize);
      thresholds[i] = threshold;
    }
    return thresholds;
  }

  public double mean() {
    return StdStats.mean(thresholds);
  }

  public double stddev() {
    return StdStats.stddev(thresholds);
  }

  public double confidenceLo() {
    double mean = mean();
    return mean - (1.96 * stddev()) / Math.sqrt(trials);
  }

  public double confidenceHi() {
    double mean = mean();
    return mean + (1.96 * stddev()) / Math.sqrt(trials);
  }

  public static void main(String[] args) {
    PercolationStats pstats = new PercolationStats(20, 10);
    System.out.println(pstats.mean());
    System.out.println(pstats.confidenceLo());
    System.out.println(pstats.confidenceHi());
  }
}
