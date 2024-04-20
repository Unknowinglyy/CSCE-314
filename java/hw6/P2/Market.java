
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 6 Problem 2 

   Student Name: Blake Dejohn
   UIN: 531002472
   Acknowledgements: 
   Oracle Java Documentation on wildcards: https://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html 

   Lecture vidoes on Java Generics created by Dr. Hyunyoung Lee (Videos 11.1 - 11.4 on Canvas)

   Collections in Java (useful for finding out exactly how the collection types relate to one another): https://www.javatpoint.com/collections-in-java#:~:text=Java%20Collection%20means%20a%20single,HashSet%2C%20LinkedHashSet%2C%20TreeSet

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
  //basically says that you can have any type of collection as your basket
  //however, the items in your basket must be able to carry any type of item that is contained in the market (stock)
  //T is guaranteed to be a superclass of the items in the stock since the only items that can be added to the stock are of type T or a subclass of T
  void buy(int n, Collection<T> items) {
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
  // Gala is a type of Apple and Apple is a type of Fruit
  static class Fruit { public String toString () { return "Fruit"; } }
  static class Apple extends Fruit {
                       public String toString () { return "Apple"; }
  }
  static class Gala extends Apple {
                       public String toString () { return "Gala"; }
  }

  public static void main(String args[]) {
    Market<Fruit> farmersmarket = new Market<Fruit> ();
    //double ended queue (never seen before, interesting...)
    Deque<Fruit> fruits = new ArrayDeque<Fruit>();
    fruits.addFirst(new Gala());
    fruits.addFirst(new Apple());
    //Fruit a = fruits.remove(); //removes the first element of the deque 
    //if (a instanceof Apple) System.out.println("a is Apple");

    Vector<Apple> apples = new Vector<Apple>();
    apples.addElement(new Apple());
    apples.addElement(new Apple());
    apples.addElement(new Gala());

    //tests single sell and bulk sell (also tests that you can sell with any type of collection with the correct type restrictions)
    farmersmarket.sell(fruits);
    farmersmarket.sell(apples);
    farmersmarket.sell(new Fruit());
    farmersmarket.sell(new Gala());

    //test of buy bulk method
    ArrayList<Fruit> mybasket = new ArrayList<Fruit>();

    farmersmarket.buy(6, mybasket);

    //test of buy single method
    Fruit extra = farmersmarket.buy();

    //also test that you can use any type of collection as your basket (as long as the items in the basket can hold any type of item that is in the stock)
    farmersmarket.sell(new Apple());
    farmersmarket.sell(new Gala());
    farmersmarket.sell(new Fruit());
    Set<Fruit> mybasket2 = new HashSet<Fruit>();
    farmersmarket.buy(3,mybasket2);
    

    // print out what you bought
    System.out.println("Here's what I bought");
    for (Fruit e : mybasket) System.out.println(e);
    //print out my tests
    System.out.println("\nHere's what my friend bought");
    for (Fruit e : mybasket2) System.out.println(e);
    System.out.println("\nI bought this on the way out too: " + extra +"\n");
    System.out.println("Enjoy!");
  } 
  // end of main
} 
// end of class Main

