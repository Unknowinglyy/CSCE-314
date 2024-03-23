
/* CSCE 314 [Sections 595, 596, 597] Programming Languages, Spring 2024
   Homework Assignment 4 
   Skeleton for Problems 4-9
   Written by Hyunyoung Lee for CSCE 314 Students

   Student Name: Blake Dejohn
   Student UIN: 531002472
   Acknowledgements: Section 1.6 of The Java Programming Language, 4th Edition, used this for function overloading: https://stackoverflow.com/questions/58860034/having-methods-take-constant-parameters-the-java-programming-language-4th-editi
*/

import java.util.*; // for Collections.sort() and ArrayList

class Vehicle {
				// private fields
        private int currentSpeed;
        private int currentDirection;
        private String ownerName;
        private int carID;
        private static int nextID = 1; 
				// public fields
        public enum turn{
          TURN_LEFT(270), 
          TURN_RIGHT(90);

          public final int degree;

          turn(int degree){
            this.degree = degree;
          }
        }
        public static final turn TURN_LEFT = turn.TURN_LEFT;
        public static final turn TURN_RIGHT = turn.TURN_RIGHT;

				// constructors
        Vehicle(){
          this.currentSpeed = 0;
          this.currentDirection = 0;
          this.ownerName = "";
          this.carID = nextID;
          nextID++;
        }
        Vehicle(String ownerName){
          this.currentSpeed = 0;
          this.currentDirection = 0;
          this.ownerName = ownerName;
          this.carID = nextID;
          nextID++;
        }
				// public methods
        //getter functions
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
        //setter functions
        public void setCurrentSpeed(int speed){
          this.currentSpeed = speed;
        }
        public void setCurrentDirection(int direction){
          this.currentDirection = direction;
        }
        public void setOwnerName(String name){
          this.ownerName = name;
        }
        public void setCarID(int id){
          this.carID = id;
        }
        @Override
        public String toString(){
          return "The current vehicle is owned by " + this.ownerName + ", has ID number " + this.carID + ", is going at a speed of " + this.currentSpeed + " mph, and is heading in a direction that is " + this.currentDirection + " degrees from North.";
        }
        public void changeSpeed(int speed){
          setCurrentSpeed(speed);
        }
        public void stop(){
          setCurrentSpeed(0);
        }
        public void turn(int value){
          this.currentDirection = (this.currentDirection + value) % 360;
        }
        public void turn(turn direction){
          turn(direction.degree);
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
  private int totalSeats = 0;
  private int occupiedSeats = 0;


  /* constructors: Give three constructors, 
     1. one no-arg constructor,
     2. a constructor with one argument: only owner name as an argument,
     3. a constructor with two arguments: owner name and total # of seats

     Probably you already have the first two constructors in the Vehicle
     class, then, invoke the Vehicle class constructor by using `super`
  */
  PassengerVehicle(){
    super();
  }
  PassengerVehicle(String name){
    super(name);
  }
  PassengerVehicle(String name, int seats){
    super(name);
    this.totalSeats = seats;
  }

  /* get methods for the private fields */
  public int getTotalSeats(){
    return this.totalSeats;
  }
  public int getOccupiedSeats(){
    return this.occupiedSeats;
  }

  /* set methods for the private fields */
  public void setTotalSeats(int seats){
    this.totalSeats = seats;
  }
  public void setOccupiedSeats(int seats){
    this.occupiedSeats = seats;
  }

  // override the toString method (inherited from the Object class) 
  // signature: toString() 
  // @Override
  // ...
  @Override
  public String toString(){
    return "";
  }

  // implement compareTo method (to `implements` Comparable)
  // signature: compareTo(PassengerVehicle)
  @Override
  public int compareTo(PassengerVehicle o) {
    
  }
  
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

} // end of class PassengerVehicle

