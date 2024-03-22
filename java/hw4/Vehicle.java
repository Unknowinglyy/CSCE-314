
/* CSCE 314 [Sections 595, 596, 597] Programming Languages, Spring 2024
   Homework Assignment 4 
   Skeleton for Problems 4-9
   Written by Hyunyoung Lee for CSCE 314 Students

   Student Name: Blake Dejohn
   Student UIN: 531002472
   Acknowledgements: Section 1.6 of The Java Programming Language, 4th Edition
*/

import java.util.*; // for Collections.sort() and ArrayList

class Vehicle {
				// private fields
        private int currentSpeed;
        private int currentDirection;
        private String ownerName;
        private int carID; 
				// public fields
        public static int nextID = 0;
				// constructors
        Vehicle(){
          nextID++;
        }
        Vehicle(String ownerName){
          this.ownerName = ownerName;
        }
				// public methods
        public static int highestID(){
          return nextID;
        }
        public int getCurrentSpeed(){
          return currentSpeed;
        }
        public int getCurrentDirection(){
          return currentDirection;
        }
        public String getOwnerName(){
          return ownerName;
        }
        public int getCarID(){
          return carID;
        }
				// private methods if you need

}

class VehicleTest {
				public static void main(String[] args) {
								// create Vehicle instances
								// test the functionalities you implemented
				}
}


// Hints on the PassengerVehicle class for Problem 9 of Homework 4
class PassengerVehicle extends Vehicle 
                       implements Comparable<PassengerVehicle> 
{
  // private fields specific to PassengerVehicle such as 
  // total # of seats and occupied seats (both can be of type int, 
  // and properly initialized)


  /* constructors: Give three constructors, 
     1. one no-arg constructor,
     2. a constructor with one argument: only owner name as an argument,
     3. a constructor with two arguments: owner name and total # of seats

     Probably you already have the first two constructors in the Vehicle
     class, then, invoke the Vehicle class constructor by using `super`
  */

  /* get methods for the private fields */

  /* set methods for the private fields */

  // override the toString method (inherited from the Object class) 
  // signature: toString() 
  // @Override
  // ...

  // implement compareTo method (to `implements` Comparable)
  // signature: compareTo(PassengerVehicle)
  
  // main method
  public static void main(String[] args) {
    // You can use either the basic Java array [] (and use Arrays.sort)
    // or ArrayList 
    // (or any Collections, whichever you feel the easiest)
    // Using ArrayList, you would do something like,
    ArrayList<PassengerVehicle> pVs = new ArrayList<PassengerVehicle>(); 
    // where pVs is object reference for ArrayList of PassengerVehicles

    // Now, you can add PassengerVehicle objects (at least 5) to pVs
    // e.g., pVs.add( new PassengerVehicle("H Lee", 7) ); 
    // which addes a PassengerVehicle object with 
    // owner name "H Lee" and total 7 seats
    
    Collections.sort(pVs); // Sort the PassengerVehicles 
                           // in an ascending order according to
                           // the compareTo method implementation 

    // Find a way to output the ascending sorted result in descending
    // order. Use a for loop to print out the sorted result 
    // in a descending order

  } // end of main

  @Override
  public int compareTo(PassengerVehicle o) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
  }

} // end of class PassengerVehicle

