
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2024, Assignment 5 Problem 2

   Student Name: Blake Dejohn
   UIN: 531002472
   Acknowledgements:
*/

import java.util.Objects;

public final class Point {
  public double x;
  public double y;

  // constructor that sets the values of x and y
  public Point(double x, double y)
  {  // implement the constructor
    this.x = x;
    this.y = y;
  }

  // implement equals, hashCode(), toString()
  @Override
  public boolean equals(Object s)
  {  // implement the method and explain your implementation
    //first checking if the object is an instance of Point, if it is then we can check additional conditions 
    if(s instanceof Point){
      //casting the object to a Point object
      Point p = (Point) s;
      //returning if the x and y values of the first object is equal to the 2nd object's x and y values
      return this.x == p.x && this.y == p.y;
    }
    //if the object is not an instance of Point then we can return false
    else{
      return false;
    }
  }

  @Override
  public int hashCode()
  {  // implement the method and explain your implementation
    //using the java.util.Objects.hash() method that is explicity designed to hash multiple values contained within an object
    return Objects.hash(x,y);
  }

  @Override
  public String toString()
  {  // implement the method and explain your implementation 
    //Making a string representation of the Point object
    return "Point: (" + x + ", " + y + ")";
  }
} // end of class Point

