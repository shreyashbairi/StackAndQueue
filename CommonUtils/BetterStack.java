package CommonUtils;

import java.util.EmptyStackException;

/**
 * @param <E> the type of object this stack will be holding
 * @implNote Implement a stack using an array with initial capacity 8.
 * <p>
 * Implement BetterStackInterface and add a constructor
 * <p>
 * You are explicitly forbidden from using java.util.Stack and any
 * other java.util.* library EXCEPT java.util.EmptyStackException and java.util.Arrays.
 * Write your own implementation of a Stack.
 */
public class BetterStack<E> implements BetterStackInterface<E> {

    /**
     * Initial size of stack.  Do not decrease capacity below this value.
     */
    private final int INIT_CAPACITY = 8;
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
     */
    private E[] stack;


    /**
     * Constructs an empty stack
     */
    @SuppressWarnings("unchecked")
    public BetterStack() {
        //todo
        stack = (E[]) new Object[INIT_CAPACITY];
    }


    /**
     * Push an item onto the top of the stack
     *
     * @param item item to push
     * @throws OutOfMemoryError if the underlying data structure cannot hold any more elements
     */
    @Override
    public void push(E item) throws OutOfMemoryError {
        //todo
        E[] temp;
        int resize_inc = stack.length * INCREASE_FACTOR;
        int resize_const = stack.length + CONSTANT_INCREMENT;
        if (item == null) {
            throw new NullPointerException();
        }
        if (size >= stack.length) {
            if (resize_inc > Integer.MAX_VALUE) {
                if (resize_const > Integer.MAX_VALUE) {
                    throw new OutOfMemoryError();
                } else {
                    temp = (E[]) new Object[resize_const];
                }
            } else {
                temp = (E[]) new Object[resize_inc];
            }
            for (int i = 0; i < stack.length; i++) {
                temp[i] = stack[i];
            }
            stack = temp;
        }
        stack[size] = item;
        size++;
    }

    /**
     * Remove and return the top item on the stack
     *
     * @return the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    @Override
    public E pop() {
        //todo
        if (size == 0) {
            throw new EmptyStackException();
        }
        int resize_dec = (int) (stack.length * DECREASE_FACTOR);

        if (size < resize_dec && resize_dec >= INIT_CAPACITY) {
            E[] temp = (E[]) new Object[Integer.max(resize_dec, INIT_CAPACITY)];
            for (int i = 0; i < size; i++) {
                temp[i] = stack[i];
            }
            stack = temp;
        }
        size--;
        E temp = stack[size];
        stack[size] = null;
        return temp;
    }

    /**
     * Returns the top of the stack (does not remove it).
     *
     * @return the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    @Override
    public E peek() {
        //todo
        if (size == 0) {
            throw new EmptyStackException();
        }
        return stack[size - 1];
    }

    /**
     * Returns whether the stack is empty
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        //todo
        return size == 0;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return integer representing the number of elements in the stack
     */
    @Override
    public int size() {
        //todo
        return size;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(java.awt.Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if (g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the stack how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
