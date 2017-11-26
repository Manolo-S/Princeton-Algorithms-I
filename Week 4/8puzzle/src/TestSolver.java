import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TestSolver {

/* TEST STRATEGY:

      isSolvable

 */


  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false; // make sure assertions are enabled with VM argument: -ea
  }


    /* isSolvable  ----------------------------------------------------------------------------------------------- */

  /**
   * Initial board is goal board
   */
  @Test
  public void testFindGoalBoard_3by3_goalBoard() {
    int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board initialBoard = new Board(blocks);

    Solver solver = new Solver(initialBoard);

    assertTrue(solver.isSolvable());
  }

  /**
   * Initial board is goal board
   */
  @Test
  public void testFindGoalBoard_2by2_goalBoard() {
    int[][] blocks = {{1, 2}, {3, 0}};
    Board initialBoard = new Board(blocks);

    Solver solver = new Solver(initialBoard);

    assertTrue(solver.isSolvable());
  }

  /**
   * Initial board is twin of goal board
   */
  @Test
  public void testFindGoalBoard_2() {
    int[][] blocks = {{2, 1, 3}, {4, 5, 6}, {7, 8, 0}};
    Board initialBoard = new Board(blocks);

    Solver solver = new Solver(initialBoard);

    assertFalse(solver.isSolvable());
  }

  /**
   * Initial board is solvable
   */
  @Test
  public void testFindGoalBoard_3() {
    int[][] blocks = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
    Board initialBoard = new Board(blocks);

    Solver solver = new Solver(initialBoard);

    assertTrue(solver.moves() == 4);
  }

  /**
   * Initial board is solvable
   */
  @Test
  public void testFindGoalBoard_4() {
    int[][] blocks = {{1, 2, 3}, {0, 7, 6}, {5, 4, 8}};
    Board initialBoard = new Board(blocks);

    Solver solver = new Solver(initialBoard);

    assertTrue(solver.moves() == 7);
  }


  /**
   * Initial board is unsolvable
   */
  @Test
  public void testFindGoalBoard_5() {
    int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};
    Board initialBoard = new Board(blocks);

    Solver solver = new Solver(initialBoard);

    assertFalse(solver.isSolvable());
  }
}



























