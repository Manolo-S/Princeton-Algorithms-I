import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] array;
  private int numberOfItems;

  // Abstraction function
  //  Represents a randomized queue.
  //  The queue is randomized in the sense that an item is chosen randomly when
  //  invoking a remove or read action on the queue.

  // Representation invariants
  //  numberOfItems >= 0.
  //  array  != null.
  //  array length >= 2.

  // Safety from exposure
  //  array and numberOfItems are private and can only be changed through methods on this.
  //  No reference to array is returned by any of the methods on this.

  private boolean checkRep() {
    return numberOfItems >= 0 && array != null && array.length >= 2;
  }

  /**
   * Constructor
   */
  public RandomizedQueue() {
    array = (Item[]) new Object[2];
    numberOfItems = 0;
    assert checkRep();
  }

  /**
   * Test if queue is empty.
   *
   * @return true if que is empty else false
   */
  public boolean isEmpty() {
    return numberOfItems == 0;
  }

  /**
   * @return the number of items in the queue
   */
  public int size() {
    return numberOfItems;
  }

  /**
   * Resizes the queue to the supplied capacity.
   *
   * @param capacity the number of items the queue can contain
   */
  private void resize(int capacity) {
    assert capacity > numberOfItems;
    array = Arrays.copyOf(array, capacity);
    assert checkRep();
  }

  /**
   * Places an item at the end of the queue.
   *
   * @param item to be placed at the end of the queue.
   * @throws IllegalArgumentException if item == null
   */
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    if (numberOfItems == array.length) {
      resize(numberOfItems * 2);
    }
    array[numberOfItems++] = item;
    assert checkRep();
  }

  /**
   * Removes a random item from the queue and fills its place with the last item in the queue
   * to prevent empty spots in the queue.
   *
   * @return a random item from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public Item dequeue() {
    if (numberOfItems == 0) {
      throw new NoSuchElementException();
    }
    int randomIndex = StdRandom.uniform(0, numberOfItems);
    Item randomItem = array[randomIndex];
    array[randomIndex] = array[numberOfItems - 1];
    array[numberOfItems - 1] = null;
    numberOfItems--;
    assert checkRep();
    return randomItem;
  }

  /**
   * Gets a random item from the queue.
   *
   * @return a random item from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public Item sample() {
    if (numberOfItems == 0) {
      throw new NoSuchElementException();
    }
    int randomIndex = StdRandom.uniform(0, numberOfItems);
    assert checkRep();
    return array[randomIndex];
  }


  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {

    private Item[] iteratorArray;
    private int itemCount;

    RandomizedQueueIterator() {
      iteratorArray = Arrays.copyOf(array, numberOfItems);
      itemCount = numberOfItems;
    }

    public boolean hasNext() {
      return itemCount > 0;
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      int randomIndex = StdRandom.uniform(0, itemCount);
      Item item = iteratorArray[randomIndex];
      iteratorArray[randomIndex] = iteratorArray[itemCount - 1];
      iteratorArray[itemCount - 1] = null;
      itemCount--;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

  }


  public static void main(String[] args) {
  }
}
