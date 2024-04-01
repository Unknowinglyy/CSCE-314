
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2024, Assignment 5 Problem 2
 
   Student Name: Blake Dejohn
   UIN: 531002472
   Acknowledgements:
*/

abstract class Shape implements Comparable<Shape> {
  public Point position;  
  public double area;
    
  // constructor that sets position as the Point passed as an argument
  // signature: Shape (Point)
  // implement the constructor
  Shape(Point p){
    this.position = p;
    this.area = 0;
  }

  // implement equals()
  @Override
  public boolean equals(Object o)
  {  // implement this method and explain your implementation
    //checking if the object is an instance of Shape
    if(o instanceof Shape){
      //casting the object to a Shape object
      Shape s = (Shape) o;
      //if this object's position and area is equal to the other object's position and area then return true
      return (this.position == s.position && this.area == s.area);
    }
    //not a shape so return false
    else{
      return false;
    }
  }

  // area() should be abstract
  public abstract double area();
 
  // implement compareTo()
		@Override
  public int compareTo(Shape s)
  {  // implement this method and explain your implementation
    //comparing the area of this object to the area of the other object
    if(this.area > s.area){
      //denotes greater than
      return 1;
    }
    else if(this.area < s.area){
      //denotes less than
      return -1;
    }
    else{
      //denotes equal to
      return 0;
    }

  }
} // end of class Shape




