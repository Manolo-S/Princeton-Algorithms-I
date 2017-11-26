import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayDeque;
import java.util.Optional;


public class Solver {

  final private MinPQ<SearchNode> minPriorityQueue;
  final private MinPQ<SearchNode> twinMinPriorityQueue;
  final Board goalBoard;
  private Optional<SearchNode> searchResultNode;

  private class SearchNode implements Comparable<SearchNode> {

    Board board;
    Optional<SearchNode> previousSearchNode;
    int numberOfMoves;
    int manhattanPriority;

    SearchNode(Board board, Optional<SearchNode> previousSearchNode) {
      this.board = board;
      this.previousSearchNode = previousSearchNode;
      if (previousSearchNode.isPresent()) {
        numberOfMoves = previousSearchNode.get().getNumberOfMoves() + 1;
      } else {
        numberOfMoves = 0;
      }
      manhattanPriority = board.manhattan() + numberOfMoves;
    }

    @Override
    public int compareTo(SearchNode searchNode) {
      return manhattanPriority - searchNode.getManhattanPriority();
    }

    public int getNumberOfMoves() {
      return numberOfMoves;
    }

    public int getManhattanPriority() {
      return manhattanPriority;
    }
  }

  private int[][] makeGoalBoardBlocks(int dimension) {
    int[][] goalBoardBlocks = new int[dimension][dimension];
    for (int i = 0, blockValue = 1; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (i == dimension - 1 && j == dimension - 1) {
          goalBoardBlocks[i][j] = 0;
        } else {
          goalBoardBlocks[i][j] = blockValue;
          blockValue++;
        }
      }
    }
    return goalBoardBlocks;
  }

  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException();
    }
    minPriorityQueue = new MinPQ<>();
    twinMinPriorityQueue = new MinPQ<>();
    minPriorityQueue.insert(new SearchNode(initial, Optional.empty()));
    twinMinPriorityQueue.insert(new SearchNode(initial.twin(), Optional.empty()));
    int[][] goalBoardBlocks = makeGoalBoardBlocks(initial.dimension());
    goalBoard = new Board(goalBoardBlocks);
    searchResultNode = Optional.empty();
  }


  private Optional<SearchNode> findGoalBoard() {
    while (true) {
      SearchNode minPriorityNode = minPriorityQueue.delMin();
      if (minPriorityNode.board.equals(goalBoard)) {
        searchResultNode = Optional.of(minPriorityNode);
        return searchResultNode;
      } else {
        for (Board board : minPriorityNode.board.neighbors()) {
          if (minPriorityNode.previousSearchNode.isPresent()) {
            if (!board.equals(minPriorityNode.previousSearchNode.get().board)) {
              minPriorityQueue.insert(new SearchNode(board, Optional.of(minPriorityNode)));
            }
          } else {
            minPriorityQueue.insert(new SearchNode(board, Optional.of(minPriorityNode)));
          }
        }
      }
      SearchNode twinMinPriorityNode = twinMinPriorityQueue.delMin();
      if (twinMinPriorityNode.board.equals(goalBoard)) {
        searchResultNode = Optional.empty();
        return searchResultNode;
      } else {
        for (Board board : twinMinPriorityNode.board.neighbors()) {
          if (twinMinPriorityNode.previousSearchNode.isPresent()) {
            if (!board.equals(twinMinPriorityNode.previousSearchNode.get().board)) {
              twinMinPriorityQueue.insert(new SearchNode(board, Optional.of(twinMinPriorityNode)));
            }
          } else {
            twinMinPriorityQueue.insert(new SearchNode(board, Optional.of(twinMinPriorityNode)));
          }
        }
      }
    }
  }

  public boolean isSolvable() {
    return searchResultNode.isPresent() ? true : findGoalBoard().isPresent();
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (searchResultNode.isPresent()) {
      return searchResultNode.get().numberOfMoves;
    }
    Optional<SearchNode> searchNode = findGoalBoard();
    return searchNode.isPresent() ? searchNode.get().getNumberOfMoves() : -1;
  }

  private Iterable<Board> getBoardSequence(Optional<SearchNode> searchNode) {
    int moves = searchNode.get().numberOfMoves;
    ArrayDeque<Board> boardSequence = new ArrayDeque<>();
    Board previousBoard;
    boardSequence.addFirst(searchNode.get().board);
    for (int i = 0; i < moves; i++) {
      previousBoard = searchNode.get().previousSearchNode.get().board;
      boardSequence.addFirst(previousBoard);
      searchNode = searchNode.get().previousSearchNode;
    }
    return boardSequence;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (searchResultNode.isPresent()) {
      return getBoardSequence(searchResultNode);
    } else {
      searchResultNode = findGoalBoard();
      if (searchResultNode.isPresent()) {
        return getBoardSequence(searchResultNode);
      } else {
        return null;
      }
    }
  }

  // solve a slider puzzle (given below)
  public static void main(String[] args) {
  }

}
