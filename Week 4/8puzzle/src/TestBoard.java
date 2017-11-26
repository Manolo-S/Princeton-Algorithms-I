import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Queue;
import org.junit.Test;

public class TestBoard {


 /* TEST STRATEGY:

      dimension
        3 by 3 board
        5 by 5 board

      hamming
        0 blocks out of place
        1 block out of place
        3 blocks out of place

      manhattan
        distance of 0
        distance of 4
        distance of 6

      isGoal
        true
        false

      twin
        goal board

      neighbors
        two neighbors
        four neighbors

      equals
        two equal boards
        two unequal boards

 */


  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false; // make sure assertions are enabled with VM argument: -ea
  }


    /* dimension ----------------------------------------------------------------------------------------------- */

  @Test
  public void testDimension_3by3() {
    int[][] blocks = new int[3][3];
    Board board = new Board(blocks);

    assertTrue(board.dimension() == 3);
  }


  @Test
  public void testDimension_5by5() {
    int[][] blocks = new int[5][5];
    Board board = new Board(blocks);

    assertTrue(board.dimension() == 5);
  }

    /* hamming   ----------------------------------------------------------------------------------------------- */

  @Test
  public void testHamming_0_BlocksOutOfPlace() {
    int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board board = new Board(blocks);

    assertTrue(board.hamming() == 0);
  }

  @Test
  public void testHamming_4_BlockOutOfPlace() {
    int[][] blocks = {{0, 1, 2}, {4, 5, 3}, {7, 8, 6}};
    Board board = new Board(blocks);

    assertTrue(board.hamming() == 4);
  }

  @Test
  public void testHamming_6_BlocksOutOfPlace() {
    int[][] blocks = {{4, 1, 2}, {7, 5, 3}, {0, 8, 6}};
    Board board = new Board(blocks);

    assertTrue(board.hamming() == 6);
  }

  /* manhattan ------------------------------------------------------------------------------------------------- */

  @Test
  public void testManhattan_DistanceOfZero() {
    int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board board = new Board(blocks);

    assertTrue(board.manhattan() == 0);
  }

  @Test
  public void testManhattan_DistanceOfFour() {
    int[][] blocks = {{0, 1, 2}, {4, 5, 3}, {7, 8, 6}};
    Board board = new Board(blocks);

    assertTrue(board.manhattan() == 4);
  }

  @Test
  public void testManhattan_DistanceOfSix() {
    int[][] blocks = {{4, 1, 2}, {7, 5, 3}, {0, 8, 6}};
    Board board = new Board(blocks);

    assertTrue(board.manhattan() == 6);
  }

  /* isGoal  ------------------------------------------------------------------------------------------------- */

  @Test
  public void testIsGoal_true() {
    int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board board = new Board(blocks);

    assertTrue(board.isGoal());
  }

  @Test
  public void testIsGoal_false() {
    int[][] blocks = {{2, 1, 3}, {4, 5, 6}, {7, 8, 0}};
    Board board = new Board(blocks);

    assertFalse(board.isGoal());
  }

  /* twin  ------------------------------------------------------------------------------------------------- */
  @Test
  public void testTwin_goalBoard() {
    int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board board = new Board(blocks);

    Board twinBoard = board.twin();
    System.out.println(Arrays.toString(twinBoard.blocks[0]));

    assertTrue(twinBoard.hamming() == 2);
  }

  /* equals  ------------------------------------------------------------------------------------------------- */
  @Test
  public void testEquals_equalBoards() {
    int[][] blocks1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board board1 = new Board(blocks1);
    int[][] blocks2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board board2 = new Board(blocks2);

    assertTrue(board1.equals(board2));
  }

  @Test
  public void testEquals_differentBoards() {
    int[][] blocks1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board board1 = new Board(blocks1);
    int[][] blocks2 = {{2, 1, 3}, {4, 5, 6}, {7, 8, 0}};
    Board board2 = new Board(blocks2);

    assertFalse(board1.equals(board2));
  }

  /* neighbors  ------------------------------------------------------------------------------------------------- */
  @Test
  public void testNeighbors_2Neighbors() {
    int[][] blocks = {{0, 1, 2}, {4, 5, 3}, {7, 8, 6}};
    Board board = new Board(blocks);
    int[][] blocksNeighborOne = {{4, 1, 2}, {0, 5, 3}, {7, 8, 6}};
    Board expectedNeighborOne = new Board(blocksNeighborOne);
    int[][] blocksNeighborTwo = {{1, 0, 2}, {4, 5, 3}, {7, 8, 6}};
    Board expectedNeighborTwo = new Board(blocksNeighborTwo);

    Queue<Board> neighbors = (Queue) board.neighbors();

    Board neighborOne = neighbors.remove();
    Board neighborTwo = neighbors.remove();

    assertTrue(expectedNeighborOne.equals(neighborOne));
    assertTrue(expectedNeighborTwo.equals(neighborTwo));
  }

  @Test
  public void testNeighbors_4Neighbors() {
    int[][] blocks = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
    Board board = new Board(blocks);

    int[][] blocksNeighborOne = {{1, 0, 3}, {4, 2, 5}, {6, 7, 8}};
    Board expectedNeighborOne = new Board(blocksNeighborOne);
    int[][] blocksNeighborTwo = {{1, 2, 3}, {4, 7, 5}, {6, 0, 8}};
    Board expectedNeighborTwo = new Board(blocksNeighborTwo);
    int[][] blocksNeighborThree = {{1, 2, 3}, {0, 4, 5}, {6, 7, 8}};
    Board expectedNeighborThree = new Board(blocksNeighborThree);
    int[][] blocksNeighborFour = {{1, 2, 3}, {4, 5, 0}, {6, 7, 8}};
    Board expectedNeighborFour = new Board(blocksNeighborFour);

    Queue<Board> neighbors = (Queue) board.neighbors();

    Board neighborOne = neighbors.remove();
    Board neighborTwo = neighbors.remove();
    Board neighborThree = neighbors.remove();
    Board neighborFour = neighbors.remove();

    assertTrue(expectedNeighborOne.equals(neighborOne));
    assertTrue(expectedNeighborTwo.equals(neighborTwo));
    assertTrue(expectedNeighborThree.equals(neighborThree));
    assertTrue(expectedNeighborFour.equals(neighborFour));
  }

    /* toString  ------------------------------------------------------------------------------------------------- */



}




















































