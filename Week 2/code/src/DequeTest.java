import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * Unit tests for the Deque<Item> class.
 */


public class DequeTest {


 /* TEST STRATEGY:

      size
        Empty deque.
        Deque with one node.
        Deque with three nodes.

      isEmpty
        Empty deque.
        Deque with one node.

      addFirst
        Add one node.
        Add two nodes.
        Add null.

      addLast
        Add one node.
        Add null.

      removeFirst
        Deque with one node, remove one node.
        Deque with two nodes, remove one node.
        Deque with two nodes, remove two nodes.
        Empty deque, try to remove node.

      removeLast
        Deque with one node, remove one node.
        Deque with two nodes, remove one node.
        Empty deque, try to remove a node.

      iterator test
        Deque with two nodes, iterate twice.
        Empty deque, check if it has a next element.
        empty deque, try to get the next element.


 */


  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false; // make sure assertions are enabled with VM argument: -ea
  }


  /* size    ------------------------------------------------------------------------------------------- */
  @Test
  public void testSize_zeroNodes() {
    Deque<String> deque = new Deque<>();

    assertTrue(deque.size() == 0);
  }

  @Test
  public void testSize_oneNode() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");

    assertTrue(deque.size() == 1);
  }

  @Test
  public void testSize_threeNodes() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");
    deque.addFirst("pear");
    deque.addFirst("apple");

    assertTrue(deque.size() == 3);
  }


  /* isEmpty ------------------------------------------------------------------------------------------- */

  @Test
  public void testIsEmpty_zeroNodes() {
    Deque<String> deque = new Deque<>();

    assertTrue(deque.isEmpty());
  }

  @Test
  public void testIsEmpty_oneNode() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");

    assertFalse(deque.isEmpty());
  }

  /* addFirst ------------------------------------------------------------------------------------------- */

  @Test
  public void testAddFirst_OneNode() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");

    assertTrue(deque.getFirstItem().equals("orange"));
  }

  @Test
  public void testAddFirst_twoNodes() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");
    deque.addFirst("apple");

    assertTrue(deque.getFirstItem().equals("apple"));
    assertTrue(deque.getLastItem().equals("orange"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddFirst_nullArgument() {
    Deque<String> deque = new Deque<>();
    deque.addFirst(null);
  }


  /* addLast --------------------------------------------------------------------------------------------- */

  @Test
  public void testAddLast_oneNode() {
    Deque<String> deque = new Deque<>();
    deque.addLast("orange");
    deque.addLast("pear");

    assertTrue(deque.getFirstItem().equals("orange"));
    assertTrue(deque.getLastItem().equals("pear"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddLast_nullArgument() {
    Deque<String> deque = new Deque<>();
    deque.addLast(null);
  }


  /* removeFirst --------------------------------------------------------------------------------------------- */

  @Test
  public void testRemoveFirst_removeOneFromOneNode() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");

    String item = deque.removeFirst();

    assertTrue(item.equals("orange"));
    assertTrue(deque.getFirstItem() == null);
    assertTrue(deque.getLastItem() == null);
  }

  @Test
  public void testRemoveFirst_removeOneFromTwoNodes() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");
    deque.addFirst("apple");

    String oldFirstItem = deque.removeFirst();

    assertTrue(oldFirstItem.equals("apple"));
    assertTrue(deque.getFirstItem().equals("orange"));
  }

  @Test
  public void testRemoveFirst_removeTwoFromTwoNodes() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");
    deque.addFirst("apple");

    deque.removeFirst();
    deque.removeFirst();

    assertTrue(deque.getFirstItem() == null);
    assertTrue(deque.getLastItem() == null);
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveFirst_emptyDeque() {
    Deque<String> deque = new Deque<>();

    deque.removeFirst();
  }

  /* removeLast --------------------------------------------------------------------------------------------- */

  @Test
  public void testRemoveLast_removeOneFromOneNode() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");

    String item = deque.removeLast();

    assertTrue(item.equals("orange"));
    assertTrue(deque.getFirstItem() == null);
    assertTrue(deque.getLastItem() == null);
  }

  @Test
  public void testRemoveLast_removeOneFromTwoNodes() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");
    deque.addFirst("apple");

    String item = deque.removeLast();

    assertTrue(item.equals("orange"));
    assertTrue(deque.getLastItem().equals("apple"));
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveLast_emptyDeque() {
    Deque<String> deque = new Deque<>();

    deque.removeLast();
  }

  /* iterator ------------------------------------------------------------------------------------------------- */

  @Test
  public void testIterator_IterateTwice() {
    Deque<String> deque = new Deque<>();
    deque.addFirst("orange");
    deque.addFirst("apple");

    Iterator<String> iterator = deque.iterator();
    String firstItem = iterator.next();
    String secondItem = iterator.next();
    assertTrue(firstItem.equals("apple"));
    assertTrue(secondItem.equals("orange"));
  }

  @Test
  public void testIterator_emptyDeque_testHasNextMethod() {
    Deque<String> deque = new Deque<>();

    Iterator<String> iterator = deque.iterator();

    assertFalse(iterator.hasNext());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testIterator_remove() {
    Deque<String> deque = new Deque<>();
    Iterator<String> iterator = deque.iterator();

    iterator.remove();
  }

  @Test(expected = NoSuchElementException.class)
  public void testIterator_emptyDeque_testNextMethod() {
    Deque<String> deque = new Deque<>();

    Iterator<String> iterator = deque.iterator();

    iterator.next();
  }
}
























