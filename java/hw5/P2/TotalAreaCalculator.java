
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2024, Assignment 5 Problem 2
 
   Student Name: Blake Dejohn
   UIN: 531002472 
   Acknowledgements:
*/

class TotalAreaCalculator {
  public static double calculate(Shape[] shapes) {
  // for each shape in the shapes array,   
  // invoke the object's area() method,
  // summing up the areas
  // and finally returns the total area
  //start off with a total area of 0
    double totalArea = 0;
    //for each shape in the shapes array
    for(Shape shape : shapes){
      //call the area function for that specific shape and add it to the total area
      totalArea += shape.area();
    }
    //return the total area
    return totalArea;
  }
}

