
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2024, Assignment 5 Problem 2

   Student Name: Blake Dejohn
   UIN: 531002472
   Acknowledgements:
*/

import java.lang.Math;

class Square extends Shape {
  private double side; // side is the side length
  //gets position and area from shape class

  // constructor that accepts a Point (for position) and a double
  // (for the side length).
  public Square(Point p0, double side){
    //implement the constructor
    this.position = p0;
    this.side = side;
    this.area = this.area();
  }

  // implement equals(), hashCode(), area(), and toString()
  @Override
  public boolean equals(Object o)
  {  // implement this method and explain your implementation
    //checking if the object is an instance of Square
    if(o instanceof Square){
      //casting the object to a Square object
      Square s = (Square) o;
      //returning true if the positions and side lengths are equal
      return (this.position == s.position && this.side == s.side);
    }
    //not a square so we it can return false
    else{
      return false;
    }
  }

  @Override
  public int hashCode()
  {  // implement this method and explain your implementation
    //first find the hash of the point object
    int pointHash = this.position.hashCode();
    //then find the hash of the side length and the previous hash
    return Object.hash(pointHash, side);
  }

  @Override
  public double area()
  {  // implement this method
    //returning the area of the square by raising the side length to the power of 2
    return Math.pow(this.side, 2); 
  }

  @Override
  public String toString()
  {  // implement this method and explain your implementation
    //returning a string representation of the Square object
    //TODO: add the area to the string representation?
    return "The square is at position " + this.position + " with a side length of " + this.side;
  }
} // end of class Square

