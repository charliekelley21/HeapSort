import java.util.NoSuchElementException;

/**
 * List class ADT to store integer values.
 * Copyright 2016 by OpenDSA Project Contributors and distributed under
 * an MIT license
 * 
 * @author Charlie Kelley (charlk21)
 * @version 2020.08.05
 *
 */
public interface List {
    /**
     * Remove all contents from the list, so it is once again empty
     */
    public void clear();


    /**
     * Insert "it" at the current location. The client must ensure that the
     * list's capacity is not exceeded
     * 
     * @param newBuffer
     *            buffer to be inserted
     * @return successful insert
     */
    public boolean insert(Buffer newBuffer);


    /**
     * Append "it" at the end of the list. The client must ensure that the
     * list's capacity is not exceeded
     * 
     * @param newBuffer
     *            buffer appended
     * @return successful append
     */
    public boolean append(Buffer newBuffer);


    /**
     * Remove and return the current element
     * 
     * @return Buffer value removed
     * @throws NoSuchElementException
     */
    public Buffer remove() throws NoSuchElementException;


    /**
     * Set the current position to the start of the list
     */
    public void moveToStart();


    /**
     * Set the current position to the end of the list
     */
    public void moveToEnd();


    /**
     * Move the current position one step left, no change if already at
     * beginning
     */
    public void prev();


    /**
     * Move the current position one step right, no change if already at end
     */
    public void next();


    /**
     * Return the number of elements in the list
     * 
     * @return length of List
     */
    public int length();


    /**
     * Return the position of the current element
     * 
     * @return current position
     */
    public int currPos();


    /**
     * Set the current position to "pos"
     * 
     * @param pos
     *            position to move to
     * @return successful move
     */
    public boolean moveToPos(int pos);


    /**
     * Return true if current position is at end of the list
     * 
     * @return true if at the end of the list
     */
    public boolean isAtEnd();


    /**
     * Return the current element
     * 
     * @return Buffer current value
     * @throws NoSuchElementException
     */
    public Buffer getValue() throws NoSuchElementException;


    /**
     * Returns true if the List is currently empty
     * 
     * @return true if the list is empty
     */
    public boolean isEmpty();
}
