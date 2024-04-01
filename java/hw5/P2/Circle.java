
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2024, Assignment 5 Problem 2

   Student Name: Blake Dejohn
   UIN: 531002472
   Acknowledgements:
*/

import java.lang.Math;
import java.util.Objects;

class Circle extends Shape {
  private double radius;
  //gets position and area from shape class

  // constructor that accepts a Point (for position) and a double
  // (for the radius).
  public Circle(Point p0, double r)
  {  // implement the constructor
    super(p0);
    this.radius = r;
    this.area = this.area();
  }

  // implement equals(), hashCode(), area(), and toString()
  @Override
  public boolean equals(Object o)
  {  // implement this method and explain your implementation
    //checking if the object is an instance of Circle
    if(o instanceof Circle){ 
      //casting the object to a Circle object
      Circle c = (Circle) o;
      //returning true if the positions and radius are equal
      return (this.position == c.position && this.radius == c.radius);
    }
    //not a circle so we it can return false
    else{
      return false;
    }
  }

  @Override
  public int hashCode()
  {  // implement this method and explain your implementation
    //first get the hash from the point object
    int pointHash = this.position.hashCode();
    //then get the hash from the radius and the previous hash
    return Objects.hash(pointHash, radius);
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
    //TODO: add the area to the string representation?
    return "The circle is at position " + this.position + " with a radius of " + this.radius;
  }
} // end of class Circle

