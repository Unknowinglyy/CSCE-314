
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2024, Assignment 5 Problem 2

   Student Name: Blake Dejohn
   UIN: 531002472
   Acknowledgements:
*/

import java.lang.Math;

class Circle extends Shape {
  private double radius;
  //gets position and area from shape class

  // constructor that accepts a Point (for position) and a double
  // (for the radius).
  public Circle(Point p0, double r)
  {  // implement the constructor
    this.position = p0;
    this.radius = r;
    this.area = this.area();
  }

  // implement equals(), hashCode(), area(), and toString()
  @Override
  public boolean equals(Object o)
  {  // implement this method and explain your implementation
  }

  @Override
  public int hashCode()
  {  // implement this method and explain your implementation
  }

  @Override
  public double area()
  {  // implement this method 
    //Pi * r^2 equals the area of a circle
    return (Math.PI * Math.pow(this.radius, 2));
  }

  @Override
  public String toString()
  {  // implement this method and explain your implementation
  }
} // end of class Circle

