import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Comparator;
import org.junit.Test;

/**
 * Unit tests for the PointTest class.
 */

public class PointTest {

  /* TEST STRATEGY:

      slopeTo
         positive zero slope
         negative zero slope
         vertical line
         two points with equal coordinates
         slope of 2
         slope of -2

      compareTo
         invoking point less than argument point y0 < y1
         invoking point less than argument point y0 == y1 x0 < x1
         invoking point equal to argument point
         invoking point greater than argument point

      slopeOrder
         slope point 0, point 1 < slope point 0, point 2
         slope point 0, point 1 > slope point 0, point 2
         slope point 0, point 1 == slope point 0, point 2
         TODO horizontal, vertical, coinciding points?





 */

  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }


      /* slopeTo  ----------------------------------------------------------------------------------------------- */

  @Test
  public void testSlopeTo_positiveZeroSlope() {
    Point pointZero = new Point(1, 3);
    Point pointOne = new Point(5, 3);
    Double expectedSlope = +0.0;

    Double actualSlope = pointZero.slopeTo(pointOne);

    assertTrue(expectedSlope.equals(actualSlope));
  }

  @Test
  public void testSlopeTo_negativeZeroSlope() {
    Point pointZero = new Point(5, 3);
    Point pointOne = new Point(1, 3);
    Double expectedSlope = -0.0;

    Double actualSlope = pointZero.slopeTo(pointOne);

    assertFalse(expectedSlope.equals(actualSlope));
  }


  @Test
  public void testSlopeTo_verticalLine() {
    Point pointZero = new Point(5, 1);
    Point pointOne = new Point(5, 6);
    double expectedSlope = Double.POSITIVE_INFINITY;

    double actualSlope = pointZero.slopeTo(pointOne);

    assertTrue(expectedSlope == actualSlope);
  }

  @Test
  public void testSlopeTo_coincidingPoints() {
    Point pointZero = new Point(1, 3);
    Point pointOne = new Point(1, 3);
    double expectedSlope = Double.NEGATIVE_INFINITY;

    double actualSlope = pointZero.slopeTo(pointOne);

    assertTrue(expectedSlope == actualSlope);
  }

  @Test
  public void testSlopeTo_slopeOfTwo() {
    Point pointZero = new Point(2, 6);
    Point pointOne = new Point(4, 10);
    double expectedSlope = 2.0;

    double actualSlope = pointZero.slopeTo(pointOne);

    assertTrue(expectedSlope == actualSlope);
  }

  @Test
  public void testSlopeTo_slopeOfMinusTwo() {
    Point pointZero = new Point(4, 6);
    Point pointOne = new Point(2, 10);
    double expectedSlope = -2.0;

    double actualSlope = pointZero.slopeTo(pointOne);

    assertTrue(expectedSlope == actualSlope);
  }


      /* slopeTo  ----------------------------------------------------------------------------------------------- */

  @Test
  public void testCompareTo_invokingPointLessThanArgumentPoint_1() {
    Point pointZero = new Point(4, 6);
    Point pointOne = new Point(2, 10);
    int expected = -1;

    int actual = pointZero.compareTo(pointOne);

    assertTrue(expected == actual);

  }

  @Test
  public void testCompareTo_invokingPointLessThanArgumentPoint_2() {
    Point pointZero = new Point(2, 10);
    Point pointOne = new Point(4, 10);
    int expected = -1;

    int actual = pointZero.compareTo(pointOne);

    assertTrue(expected == actual);

  }

  @Test
  public void testCompareTo_invokingPointEqualToArgumentPoint() {
    Point pointZero = new Point(4, 6);
    Point pointOne = new Point(4, 6);
    int expected = 0;

    int actual = pointZero.compareTo(pointOne);

    assertTrue(expected == actual);

  }

  @Test
  public void testCompareTo_invokingPointGreaterThanArgumentPoint() {
    Point pointZero = new Point(2, 10);
    Point pointOne = new Point(4, 6);
    int expected = 1;

    int actual = pointZero.compareTo(pointOne);

    assertTrue(expected == actual);
  }

      /* slopeTo  ----------------------------------------------------------------------------------------------- */

  /* slope point 0, point 1 < slope point 0, point 2 */
  @Test
  public void testSlopeOrder_1() {
    Point pointZero = new Point(2, 2);
    Point pointOne = new Point(4, 3);
    Point pointTwo = new Point(5, 4);
    Comparator<Point> slopeOrder = pointZero.slopeOrder();
    int expected = -1;

    int result = slopeOrder.compare(pointOne, pointTwo);

    assertTrue(expected == result);
  }

/* slope point 0, point 1 > slope point 0, point 2 */
@Test
  public void testSlopeOrder_2(){
  Point pointZero = new Point(2, 2);
  Point pointOne = new Point(5, 4);
  Point pointTwo = new Point(4, 3);
  Comparator<Point> slopeOrder = pointZero.slopeOrder();
  int expected = 1;

  int result = slopeOrder.compare(pointOne, pointTwo);

  assertTrue(expected == result);
}

/* slope point 0, point 1 == slope point 0, point 2 */
@Test
public void testSlopeOrder_3(){
  Point pointZero = new Point(2, 2);
  Point pointOne = new Point(4, 3);
  Point pointTwo = new Point(6, 4);
  Comparator<Point> slopeOrder = pointZero.slopeOrder();
  int expected = 0;

  int result = slopeOrder.compare(pointOne, pointTwo);

  assertTrue(expected == result);
}



}




































