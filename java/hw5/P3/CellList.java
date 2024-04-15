
/*   
   CellList.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2024, Assignment 5 Problem 4

   Student Name: Blake Dejohn
   UIN: 531002472
   Acknowledgements:
*/

import java.util.Arrays;
import java.util.Iterator;


// Total 40 points for the CellList class

public class CellList<E> implements Iterable<E>, Cloneable, Comparable<CellList<E>> {
  //given
  private Cell<E> n; //head of the list?
  private int length;

  @Override
  public Iterator<E> iterator() { return n.iterator(); }

  // Task 1: override clone() (5 points)
  @Override
  public CellList<E> clone() {
		// Implement this method and explain
    CellList<E> clone = new CellList<E>();
    for(E elem: this){
      clone.push(elem);
    }
    return clone;
		}

	 @Override
   //given
  public int compareTo(CellList<E> list) { 
    if (this.length < list.length) return -1;
    if (this.length == list.length) return 0;
    return 1; 
  }

  // Task 2: override equals() (10 points) 
	 @Override
  public boolean equals(Object o) {
		// Implement this method and explain (read the equality criteria in the
		// problem statement carefully!)
    //if object is equal to this, return true
    if(o == this){
      return true;
    }
    //check if object is of type CellList, return false if not
    if(!(o instanceof CellList)){
      return false;
    }
    //check if the two lists are the same length
    if(this.getLength() != ((CellList<?>) o).getLength()){
      return false;
    }
    //create two iterators for the two lists
    Iterator<E> iter1 = this.iterator();
    Iterator<?> iter2 = ((CellList<?>) o).iterator();
    //for each element in the list, check if the elements are equal
    while(iter1.hasNext()){
      if(!iter1.next().equals(iter2.next())){
        return false;
      }
    }
    //if all elements are equal, return true
    return true;
  }


  @Override
  public int hashCode() {
    return length;
  }

  // no-arg constructor - given
  public CellList() { n = null; length = 0; }
    
  // Task 3: one-arg constructor (5 points)
  public CellList(Iterable<E> iterable) {
		// implement this constructor
    //set private fields to starting values
    n = null;
    //for each element in the iterable, push the element to the list
    for(E elem: iterable){
      this.push(elem);
    }
		}

				
  // Task 4: total 20 points for toString(), push() and pop()
  // 8 points
  public String toString() {
		// implement this method
    //create a string that represents the elements of the list
    String list = "";
    if(this.n == null){
      return list;
    }

    //if nothing in the list, return an empty string
    if(this.getLength() == 0){
      return list;
    }
    else{
      //should look like this: [(head: elem1) -> (elem2) -> ... -> (elemN)]
      list += "[(head: " + n.getVal() + ") -> ";
      //create an iterator for the list
      Iterator<E> iter = this.iterator();

      //skip the head element
      if(iter.hasNext()){
        iter.next();
      }
      //for each element in the list, add it to the string
      while(iter.hasNext()){
        list += "(" + iter.next() + ") -> ";
      }
      //remove the last arrow and add the closing bracket
      list = list.substring(0, list.length() - 4) + "]";
      return list;
    }
  }

  // 5 points
  public void push(E item) {
    // implement this method
    //want to push an item to the front of the list
    if(n == null){
      //if the list is empty, create a new cell with the item and set it as the head of the list
      n = new Cell<E>(item, null);
      length++;
      return;
    }
    //create a new cell with the item and set it as the head of the list
    Cell<E> newCell = new Cell<E>(item, n);
    n = newCell;
    //increment the length of the list
    length++;
	}

  // 7 points
  public E pop() {
    // implement this method
    //if the list is empty, return null
    if(this.getLength() == 0){
      return null;
    }
    else{
      //store the value of the head element
      E value = this.peek();
      //set the head to the next cell in the list
      n = n.getNext();
      //decrement the length of the list
      length--;
      //return the value of the head element
      return value;
    }
	}

  // given 
  public E peek() { return n.getVal(); }

  // given 
  public int getLength() { return length; }
}

