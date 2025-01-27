
/**
 * Class for a Link that provides the nodes that a Linked List is built upon
 * Copyright 2016 by OpenDSA Project Contributors and distributed under
 * an MIT license
 * 
 * @author Charlie Kelley (charlk21)
 * @version 2020.08.05
 *
 */
public class Link {
    /**
     * Value for this node
     */
    private Buffer buff;

    /**
     * Pointer to the next node in the List
     */
    private Link ptr;

    /**
     * Creates a node with a given value that also points to another node
     * 
     * @param newBuff
     *            value of node
     * @param newPtr
     *            pointer to next node
     */
    Link(Buffer newBuff, Link newPtr) {
        buff = newBuff;
        ptr = newPtr;
    }


    /**
     * Creates a node that points to another node
     * 
     * @param newPtr
     *            pointer to the next node
     */
    Link(Link newPtr) {
        ptr = newPtr;
    }


    /**
     * Gets the value of the node
     * 
     * @return value stored in the node
     */
    Buffer element() {
        return buff;
    }


    /**
     * Modifies the value of the node
     * 
     * @param newBuff
     *            new value of the node
     * @return modified value
     */
    Buffer setElement(Buffer newBuff) {
        buff = newBuff;
        return buff;
    }


    /**
     * Provides a pointer the the next node
     * 
     * @return pointer to the next node
     */
    Link next() {
        return ptr;
    }


    /**
     * Modifies the next node that is pointed to
     * 
     * @param newPtr
     *            new pointer
     * @return pointer to modified next node
     */
    Link setNext(Link newPtr) {
        ptr = newPtr;
        return ptr;
    }

}
