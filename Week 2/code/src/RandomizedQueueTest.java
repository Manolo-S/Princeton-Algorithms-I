import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * Unit tests for the Deque<Item> class.
 */
public class RandomizedQueueTest {
 /* TEST STRATEGY:

      size
        empty queue
        queue with 1 item
        queue with 3 items


      isEmpty
        empty queue
        queue with one item

      enqueue
        empty queue, add one item
        empty queue, add two items

      dequeue
        empty queue
        queue with one item
        queue with two items

      sample
        empty queue
        queue with one item
        queue with three items
      iterator test



 */


  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false; // make sure assertions are enabled with VM argument: -ea
  }

    /* size ----------------------------------------------------------------------------------------------- */

  @Test
  public void testSize_EmptyQueue() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();

    assertTrue(queue.size() == 0);
  }

  @Test
  public void testSize_threeItems() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.enqueue("orange");
    queue.enqueue("apple");
    queue.enqueue("cherry");
    assertTrue(queue.size() == 3);
  }


  /* empty ----------------------------------------------------------------------------------------------- */

  @Test
  public void testEmpty_emptyQueue() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();

    assertTrue(queue.isEmpty());
  }

  @Test
  public void testEmpty_oneItem() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.enqueue("orange");

    assertFalse(queue.isEmpty());
  }

  /* enqueue --------------------------------------------------------------------------------------------- */

  @Test
  public void testEnqueue_addOneItem() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.enqueue("orange");

    assertTrue(queue.size() == 1);
  }

  @Test
  public void testEnqueue_addTwoItems() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.enqueue("orange");
    queue.enqueue("apple");

    assertTrue(queue.size() == 2);
  }

  /* dequeue -------------------------------------------------------------------------------------------- */

  @Test(expected = NoSuchElementException.class)
  public void testDequeue_emptyQueue() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();

    queue.dequeue();
  }

  @Test
  public void testDequeue_oneItem() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    String expectedItem = "orange";
    queue.enqueue(expectedItem);
    String randomItem = queue.dequeue();

    assertTrue(expectedItem.equals(randomItem));
    assertTrue(queue.isEmpty());
  }

  @Test
  public void testDequeue_threeItems() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    String expectedItem = "orange";
    queue.enqueue(expectedItem);
    queue.enqueue(expectedItem);
    queue.enqueue(expectedItem);
    int expectedSize = 2;

    String randomItem = queue.dequeue();

    assertTrue(expectedItem.equals(randomItem));
    assertTrue(queue.size() == 2);
  }



    /* sample --------------------------------------------------------------------------------------------- */

  @Test(expected = NoSuchElementException.class)
  public void testSample_emptyQueue() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();

    queue.sample();
  }

  @Test
  public void testSample_oneItem() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.enqueue("orange");
    String expectedItem = "orange";

    String randomItem = queue.sample();

    assertTrue(expectedItem.equals(randomItem));
  }

  @Test
  public void testSample_threeItems() {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.enqueue("orange");
    queue.enqueue("orange");
    queue.enqueue("orange");
    String expectedItem = "orange";

    String randomItem = queue.sample();

    assertTrue(expectedItem.equals(randomItem));
  }

    /* iterator test -------------------------------------------------------------------------------------- */

}































