import java.util.LinkedList;
import java.util.Queue;

public class Board {

  final int[][] blocks;
  private final int dimension;


  /**
   * Constructor
   *
   * @param blocks represents all the fields of a board
   */
  public Board(int[][] blocks) {
    //TODO copy blocks like in twin()
    this.blocks = blocks;
    this.dimension = blocks[0].length;
  }


  /**
   * @return the number of board rows
   */
  public int dimension() {
    return dimension;
  }


  /**
   * @return number of blocks out of place
   */
  public int hamming() {
    int blocksOutOfPlace = 0;
    int blockNumber = 1;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++, blockNumber++) {
        if (!(blocks[i][j] == blockNumber)) {
          blocksOutOfPlace++;
        }
        if (i == dimension - 1 && j == dimension - 2) {
          break;
        }
      }
    }
    return blocksOutOfPlace;
  }

  /**
   * Calculates the sum of the vertical and horizontal distance from a block to its goal position,
   *
   * @param row block row
   * @param column block column
   * @return the sum of the vertical and horizontal distance from a block to its goal position
   */
  private int getDistance(int row, int column) {
    int block = blocks[row][column];
    int normalRow;
    int normalColumn;
    if (block == 0) {
      return 0;
    }
    if (block % dimension == 0) {
      normalRow = block / dimension - 1;
      normalColumn = dimension - 1;
    } else {
      normalRow = block / dimension;
      normalColumn = block % dimension - 1;
    }
    //use of Math.abs is not allowed in this exercise, hence the next two lines
    int rowDifference = row - normalRow < 0 ? normalRow - row : row - normalRow;
    int columnDifference =
        column - normalColumn < 0 ? normalColumn - column : column - normalColumn;
    return rowDifference + columnDifference;
  }

  /**
   * Calculate the sum of the Manhattan distances (sum of the vertical and horizontal distance)
   * from the blocks to their goal positions
   *
   * @return the sum of the Manhattan distances from the blocks to their goal positions
   */
  public int manhattan() {
    int totalDistance = 0;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        totalDistance += getDistance(i, j);
      }
    }
    return totalDistance;
  }

  /**
   * Check if the board is the goal board
   *
   * @return true if the board is the goal board
   */
  public boolean isGoal() {
    return hamming() == 0;
  }


  /**
   * A board that is obtained by creating a new board equal to this and then exchanging the first
   * and second block
   *
   * @return a new board that is equal to this except that the first and second block are exchanged
   */
  public Board twin() {
    int[][] twinBlocks = copyBlocks();
    int firstBlockRow = 0;
    int firstBlockColumn = 0;
    boolean firstBlockFound = false;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (twinBlocks[i][j] != 0 && !firstBlockFound) {
          firstBlockRow = i;
          firstBlockColumn = j;
          firstBlockFound = true;
        } else if (firstBlockFound && twinBlocks[i][j] != 0) {
          int secondBlock = twinBlocks[i][j];
          twinBlocks[i][j] = twinBlocks[firstBlockRow][firstBlockColumn];
          twinBlocks[firstBlockRow][firstBlockColumn] = secondBlock;
          return new Board(twinBlocks);
        }
      }
    }
    throw new RuntimeException("This method should never get down here.");
  }


  public boolean equals(Object otherBoard) {
    //TODO throw nullpointer exception if y = null? Review rules for equals
    //TODO write hashCode

    if (otherBoard == null) {
      return false;
    }
    boolean boardsEqual = true;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (blocks[i][j] != ((Board) otherBoard).blocks[i][j]) {
          boardsEqual = false;
        }
      }
    }
    return boardsEqual;
  }

  /**
   * Get the coordinates (row, column) of the blank square
   *
   * @return the coordinates (row, column) of the blank square
   */
  private int[] getBlankSquareCoordinates() {
    int rowBlankSquare = 0;
    int columnBlankSquare = 0;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (blocks[i][j] == 0) {
          rowBlankSquare = i;
          columnBlankSquare = j;
        }
      }
    }
    int[] blankSquareCoordinates = {rowBlankSquare, columnBlankSquare};
    return blankSquareCoordinates;
  }

  /**
   * @return a copy of the blocks of this
   */
  private int[][] copyBlocks() {
    int[][] copiedBlocks = new int[dimension][dimension];
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        copiedBlocks[i][j] = blocks[i][j];
      }
    }
    return copiedBlocks;
  }

  /**
   * Get all neighbors
   *
   * @return an Iterable object containing all neighbors of this
   */
  public Iterable<Board> neighbors() {
    //TODO why a queue?
    Queue<Board> neighborBoards = new LinkedList<>();
    int[][] neighborOffset = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[] blankSquareCoordinates = getBlankSquareCoordinates();
    int blankSquareRow = blankSquareCoordinates[0];
    int blankSquareColumn = blankSquareCoordinates[1];
    for (int i = 0; i < 4; i++) {
      int[][] blocksCopy = copyBlocks();
      int neighborRow = blankSquareRow + neighborOffset[i][0];
      int neighborColumn = blankSquareColumn + neighborOffset[i][1];
      if (neighborRow >= 0 && neighborRow < dimension &&
          neighborColumn >= 0 && neighborColumn < dimension) {
        blocksCopy[blankSquareRow][blankSquareColumn] = blocksCopy[neighborRow][neighborColumn];
        blocksCopy[neighborRow][neighborColumn] = 0;
        neighborBoards.add(new Board(blocksCopy));
      }
    }
    return neighborBoards;
  }

  // string representation of this board (in the output format specified below)
  public String toString() {
    StringBuilder board = new StringBuilder();
    board.append(dimension).append("\n");
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        board.append(blocks[i][j]);
        if (j < dimension - 1) {
          board.append(" ");
        }
      }
      board.append("\n");
    }
    return board.toString();
  }

  public static void main(String[] args) {
    int[][] blocks = {{0, 1, 2}, {4, 5, 3}, {7, 8, 6}};
    Board board = new Board(blocks);
    System.out.println(board);
  }

}





















