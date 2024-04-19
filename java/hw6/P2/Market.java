
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 6 Problem 2 

   Student Name: Blake Dejohn
   UIN: 531002472
   Acknowledgements: 
   Oracle Java Documentation on wildcards: https://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html 
   Lecture vidoes on Java Generics created by Dr. Hyunyoung Lee (Videos 11.1 - 11.4 on Canvas)

*/

import java.util.*;

public class Market<T> {
  List<T> stock; // stock of the market

  public Market() { stock = new java.util.LinkedList<T>(); }
  //adds to the stock
  void sell(T item) {
    stock.add(item);
  }
  //removes from the stock
  public T buy() {
    //buys the first item in the stock
    T item = stock.remove(0);
    return item;
  }
  //e.g. a Market that contains Fruit can sell any collection that contains a type that extends Fruit!
  void sell(Collection<? extends T> items) {
    // implement this method
    for (T item : items) {
      stock.add(item);
    }
  }
  void buy(int n, List<T> items) { // modify the parameter type
    //takes the first n items from the stock
    //adds them to the items list
    for (int i = 0; i < n; i++) {
      items.add(stock.remove(0)); //remove from stock then add to items
    }
  }
} 
// end of class Market


// Study class Main. You don't need to modify class Main
class Main {
  // three static nested classes expressing example subclass hierarchy
  // Gala <: Apple <: Fruit
  static class Fruit { public String toString () { return "Fruit"; } }
  static class Apple extends Fruit {
                       public String toString () { return "Apple"; }
  }
  static class Gala extends Apple {
                       public String toString () { return "Gala"; }
  }

  public static void main(String args[]) {
    Market<Fruit> farmersmarket = new Market<Fruit> ();
    //double ended queue
    Deque<Fruit> fruits = new ArrayDeque<Fruit>();
    fruits.addFirst(new Gala());
    fruits.addFirst(new Apple());
    //Fruit a = fruits.remove(); //removes the first element of the deque 
    //if (a instanceof Apple) System.out.println("a is Apple");

    Vector<Apple> apples = new Vector<Apple>();
    apples.addElement(new Apple());
    apples.addElement(new Apple());
    apples.addElement(new Gala());

    //add test of buy method (buy one item)

    farmersmarket.sell(fruits);
    farmersmarket.sell(apples);
    farmersmarket.sell(new Fruit());
    farmersmarket.sell(new Gala());

    ArrayList<Fruit> mybasket = new ArrayList<Fruit>();

    farmersmarket.buy(6, mybasket);

    // print out what you bought
    System.out.println("Here's what I bought");
    for (Fruit e : mybasket) System.out.println(e);
    System.out.println("Enjoy!");
  } // end of main
} // end of class Main

