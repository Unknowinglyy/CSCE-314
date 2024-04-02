
/* Cell.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2024, Assignment 5 Problem 3 

   Student Name: Blake Dejohn
   UIN: 531002472
   Acknowledgements:
*/

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

// class Cell: 10 points
// give correct class header - given in the problem statement
public final class Cell<E> implements Iterable<E>{ // modify this header
  // private fields
		private E elem; // stores a value of type E
		private Cell<E> next; // link to the next Cell

  // constructor
  public Cell (E elem, Cell<E> next) {
		// implement this constructor
    this.elem = elem;
    this.next = next;
		} 

  // iterator() returns a CellIterator object for this object
		@Override
  public CellIterator<E> iterator() {
		// implement this method and explain
    return new CellIterator<E>(this);
		}

  // getter and setter methods for the private fields
  public E getVal() {
  // implement this method
    return this.elem;
		} 
  public void setVal(E v) {
  // implement this methodn
    this.elem = v;
		} 
  public Cell<E> getNext() {
  // implement this method
    return this.next;
		} 
  public void setNext(Cell<E> node) {
		// implement this method
    this.next = node;
		} 

  // CellIterator: 20 points
  // Having CellIterator as an inner class of Cell makes sense...
  // (2 points) correct class header - given in the problem statement
  class CellIterator<E> implements Iterator<E>{ // modify this header
    private Cell<E> p;  // given

    // (3 points) constructor
    public CellIterator (Cell<E> n) {
				// implement this constructor
				}

    // (5+10=15 points) two methods to implement the Iterator interface
    // (5 points) hasNext()
				@Override
    public boolean hasNext() {
				// implement this method and explain
        return true;
				} 

    // (10 points) next()
				@Override
    public E next() {
				// implement this method and explain
        return this.p.getVal();
				}    

  } // end of CellIterator
} // end of Cell




