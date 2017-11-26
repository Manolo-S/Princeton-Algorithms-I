import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private int size;
  private Node first;
  private Node last;

  /**
   * A generic Node.
   */
  private class Node {

    private Item item;
    private Node next;
    private Node previous;
  }

  // Abstraction function
  //  Represents a generic double-ended queue.

  // Representation invariants
  //  size >= 0
  //  if size == 0 then first == last == null
  //  if size == 1 then first != null && last != null && first == last
  //  if size >= 2 then first != null and last != null

  // Safety from exposure
  //  All instance variables are private but can be mutated only by Deque methods.

  /**
   * Constructs a double-ended queue
   */
  public Deque() {
    first = null;
    last = null;
    size = 0;
    assert checkRep();
  }

  /**
   * Checks if deque is empty.
   *
   * @return true if deque contains no nodes.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * @return the number of nodes in this deque
   */
  public int size() {
    return size;
  }

  /**
   * Add an Ttem object to the front of this deque.
   */
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    Node newNode = new Node();
    newNode.item = item;
    if (size == 0) {
      first = newNode;
      first.previous = null;
      first.next = null;
      last = first;
    } else {
      Node oldFirst = first;
      first = newNode;
      first.previous = null;
      first.next = oldFirst;
      oldFirst.previous = first;
    }
    size++;
    assert checkRep();
  }

  /**
   * Add an Item object to the end of this deque.
   */
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    Node newNode = new Node();
    newNode.item = item;
    if (size == 0) {
      last = newNode;
      last.next = null;
      last.previous = null;
      first = last;
    } else {
      Node oldLast = last;
      last = newNode;
      last.next = null;
      oldLast.next = last;
      last.previous = oldLast;
    }
    size++;
    assert checkRep();
  }

  /**
   * Remove and return the item in the front of this deque.
   *
   * @return the item in the front of the queue.
   */
  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item oldFirstItem = first.item;
    if (size == 1) {
      first = last = null;
      size--;
    } else {
      first = first.next;
      first.previous = null;
      size--;
    }
    assert checkRep();
    return oldFirstItem;
  }

  /**
   * Remove and return the item in the back of this deque.
   *
   * @return the item in the back of the queue.
   */
  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item oldLastItem = last.item;
    if (size == 1) {
      Item lastItem = last.item;
      first = last = null;
      size--;
      return lastItem;
    } else {
      last = last.previous;
      last.next = null;
      size--;
    }
    assert checkRep();
    return oldLastItem;
  }

  /**
   * Returns an iterator to this deque that iterates through from front to end.
   *
   * @return an iterator to this deque that iterates through from front to end.
   */
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {

    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = current.item;
      current = current.next;
      assert checkRep();
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }


  /**
   * Check if the representation variants hold.
   *
   * @return true in case the representation variants hold
   */
  private boolean checkRep() {
    return checkNodes() && checkSize();
  }

  /**
   * Check if the representation variants hold for the first and last node.
   *
   * @return true if the above mentioned variant hold else false
   */
  private boolean checkNodes() {
    if (size < 0) {
      return false;
    }
    if (size == 0) {
      if (first != null || last != null) {
        return false;
      }
    } else if (size == 1) {
      if (first == null || last == null || !(first == last)) {
        return false;
      }
    } else {
      if (first == null || last == null) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check of the representation variant holds for the size of this deque.
   *
   * @return true if the above mentioned variant holds else false.
   */
  private boolean checkSize() {
    int numberOfNodes = 0;
    for (Node node = first; node != null && numberOfNodes <= size; node = node.next) {
      numberOfNodes++;
    }
    if (numberOfNodes != size) {
      return false;
    }
    return true;
  }

  /**
   * Test helper method to get first item.
   *
   * @return first item
   */
  Item getFirstItem() {
    return size > 0 ? first.item : null;
  }

  /**
   * Test helper method to get last item.
   *
   * @return last item
   */
  Item getLastItem() {
    return size > 0 ? last.item : null;
  }

  public static void main(String[] args) {
  }
}

