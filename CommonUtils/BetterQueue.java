package CommonUtils;

import java.awt.*;

/**
 * @param <E> the type of object this queue will be holding
 * @implNote implement a queue using a circular array with initial capacity 8.
 * <p>
 * Implement BetterQueueInterface and add a constructor
 * <p>
 * You are explicitly forbidden from using java.util.Queue and any subclass
 * (including LinkedList, for example) and any other java.util.* library EXCEPT java.util.Objects.
 * Write your own implementation of a Queue.
 * <p>
 * Another great example of why we are implementing our own queue here is that
 * our queue is actually FASTER than Java's LinkedList (our solution is 2x faster!). This is due
 * to a few reasons, the biggest of which are 1. the overhead associated with standard library
 * classes, 2. the fact that Java's LinkedList doesn't store elements next to each other, which
 * increases memory overhead for the system, and 3. LinkedList stores 2 pointers with each element,
 * which matters when you store classes that aren't massive (because it increases the size of each
 * element, making more work for the system).
 */
public class BetterQueue<E> implements BetterQueueInterface<E> {

    /**
     * Initial size of queue.  Do not decrease capacity below this value.
     */
    private final int INIT_CAPACITY = 8;
    private int front = 0;
    private int back = 0;
    private int size = 0;


    /**
     * If the array needs to increase in size, it should be increased to
     * old capacity * INCREASE_FACTOR.
     * <p>
     * If it cannot increase by that much (old capacity * INCREASE_FACTOR > max int),
     * it should increase by CONSTANT_INCREMENT.
     * <p>
     * If that can't be done either throw OutOfMemoryError()
     */
    private final int INCREASE_FACTOR = 2;
    private final int CONSTANT_INCREMENT = 1 << 5; // 32


    /**
     * If the number of elements stored is < capacity * DECREASE_FACTOR, it should decrease
     * the capacity of the UDS to max(capacity * DECREASE_FACTOR, initial capacity).
     */
    private final double DECREASE_FACTOR = 0.5;


    /**
     * Array to store elements in (according to the implementation
     * note in the class header comment).
     * <p>
     * Circular arrays work as follows:
     * <p>
     * 1. Removing an element increments the "first" index
     * 2. Adding an element inserts it into the next available slot. Incrementing
     * the "last" index WRAPS to the front of the array, if there are spots available
     * there (if we have removed some elements, for example).
     * 3. The only way to know if the array is full is if the "last" index
     * is right in front of the "first" index.
     * 4. If you need to increase the size of the array, put the elements back into
     * the array starting with "first" (i.e. "first" is at index 0 in the new array).
     * 5. No other implementation details will be given, but a good piece of advice
     * is to draw out what should be happening in each operation before you code it.
     * <p>
     * hint: modulus might be helpful
     */
    private E[] queue;


    /**
     * Constructs an empty queue
     */
    @SuppressWarnings("unchecked")
    public BetterQueue() {

        queue = (E[]) new Object[INIT_CAPACITY];

    }

    /**
     * Add an item to the back of the queue
     *
     * @param item item to push
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public void add(E item) {
        E[] temp;
        int resize_inc = queue.length * INCREASE_FACTOR;
        int resize_const = queue.length + CONSTANT_INCREMENT;
        if (item == null) {
            throw new NullPointerException();
        }
        if (size >= queue.length) {
            if (resize_inc > Integer.MAX_VALUE) {
                if (resize_const > Integer.MAX_VALUE) {
                    throw new OutOfMemoryError();
                } else {
                    temp = (E[]) new Object[resize_const];
                }
            } else {
                temp = (E[]) new Object[resize_inc];
            }
            for (int i = 0; i < queue.length; i++) {
                int j = (front + i) % queue.length;
                temp[i] = queue[j];
            }
            queue = temp;
            front = 0;
            back = size;
        }
        queue[back] = item;
        size++;
        back = (back + 1) % queue.length;

    }

    /**
     * Returns the front of the queue (does not remove it) or <code>null</code> if the queue is empty
     *
     * @return front of the queue or <code>null</code> if the queue is empty
     */
    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }

        return queue[front];
    }

    /**
     * Returns and removes the front of the queue
     *
     * @return the head of the queue, or <code>null</code> if this queue is empty
     */
    @Override
    public E remove() {
        //todo
        int resize_dec = (int) (queue.length * DECREASE_FACTOR);
        if (size == 0) {
            return null;
        }
        if (size < resize_dec && resize_dec >= INIT_CAPACITY) {
            E[] temp = (E[]) new Object[Integer.max(resize_dec, INIT_CAPACITY)];
            for (int i = 0; i < size; i++) {
                int j = (front + i) % queue.length;
                temp[i] = queue[j];
            }
            queue = temp;
            front = 0;
            back = size;
        }
        E result = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        size--;
        return result;
    }

    /**
     * Returns the number of elements in the queue
     *
     * @return integer representing the number of elements in the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the queue is empty
     *
     * @return true if the queue is empty, false otherwise
     */
    public int length() {
        return queue.length;
    }

    public E getBack() {
        assert(size > 0);
        if(back == 0) {
            return queue[queue.length - 1];
        }
        return queue[back-1];
    }
    @Override
    public boolean isEmpty() {
        //todo
        return size == 0;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < queue.length; i++) {
            result += queue[i] + " ";
        }
        return result;
    }
    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */


    @Override
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if (g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the queue how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
