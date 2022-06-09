import java.util.ArrayList;
import java.util.List;

/**
 * Class implementing a bounded queue with a list.
 * 
 * @param <T>
 */
public class BoundedQueue<T> {
  private final int capacity ;
  private List<T> buffer ;
  private int nextItem ;
  private int nextFreeSlot ;
  private int numberOfItems ;

  public BoundedQueue(int capacity) {
    this.capacity = capacity ;
    buffer = new ArrayList<>(capacity);

    nextItem = 0 ;
    nextFreeSlot = 0 ;
    numberOfItems = 0 ;
  }

  public void put(T element) {
    if (this.isFull()) {
      throw new FullQueueException() ;
    }

    if(buffer.size() < capacity)
      buffer.add(nextFreeSlot,element);
    else
      buffer.set(nextFreeSlot, element);

    nextFreeSlot = (nextFreeSlot + 1) % capacity ;
    numberOfItems ++ ;
  }

  public T get()  {
    if (this.isEmpty()) {
      throw new EmptyQueueException() ;
    }

    T result = buffer.get(nextItem) ;
    nextItem = (nextItem + 1) % capacity  ;
    numberOfItems -- ;

    return result;
  };

  public boolean isFull() {
    return capacity == numberOfItems ;
  }

  public boolean isEmpty() {
    return numberOfItems == 0 ;
  }

  public int getNumberOfItems() {
    return numberOfItems ;
  }
}
