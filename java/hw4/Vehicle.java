
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
        private static int currentNextID = 1;  
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
          //each time a vehicle object is made, the next Vehicle ID is incremented
          //get the current nextID before incrementing
          currentNextID = nextID;
          nextID++;
        }
        Vehicle(String ownerName){
          this.currentSpeed = 0;
          this.currentDirection = 0;
          this.ownerName = ownerName;
          this.carID = nextID;
          //each time a vehicle object is made, the next Vehicle ID is incremented
          //get the current nextID before incrementing
          currentNextID = nextID;
          nextID++;
        }
				// public methods
        //getter functions
        public static int getHighestID(){
          return currentNextID;
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
        //using absolute function to make sure speed is never negative
        public void setCurrentSpeed(int speed){
          this.currentSpeed = Math.abs(speed);
        }
        //using the mod function to make sure the direction field ranges from 0 - 359 ONLY
        public void setCurrentDirection(int direction){
          this.currentDirection = Math.floorMod(direction, 360);
        }
        public void setOwnerName(String name){
          this.ownerName = name;
        }
        // public void setCarID(int id){
        //   this.carID = id;
        // }
        @Override
        //overloading the toString method to print all relevant information about a vehicle object.
        public String toString(){
          return "The current vehicle is owned by " + this.ownerName + ", has ID number " + this.carID + ", is going at a speed of " + this.currentSpeed + " mph, and is heading in a direction that is " + this.currentDirection + " degrees from North.";
        }
        public void changeSpeed(int speed){
          setCurrentSpeed(speed);
        }
        public void stop(){
          setCurrentSpeed(0);
        }
        //using modulus to make sure the direction field ranges from 0-359 ONLY
        public void turn(int value){
          this.currentDirection = Math.floorMod(this.currentDirection + value, 360);
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
                //using no arg constructor
                Vehicle car1 = new Vehicle();
                car1.setCurrentSpeed(30);
                car1.setCurrentDirection(20);
                car1.setOwnerName("Bruce");
                System.out.println(car1);

                Vehicle car2 = new Vehicle();
                car2.setCurrentSpeed(90);
                car2.setCurrentDirection(150);
                car2.setOwnerName("Emma");
                System.out.println(car2);

                Vehicle car3 = new Vehicle();
                car3.setCurrentSpeed(10);
                car3.setCurrentDirection(500);
                car3.setOwnerName("Bryan");
                System.out.println(car3);

                Vehicle car4 = new Vehicle();
                car4.setCurrentSpeed(60);
                car4.setCurrentDirection(360);
                car4.setOwnerName("Noah");
                System.out.println(car4);

                Vehicle car5 = new Vehicle();
                car5.setCurrentSpeed(0);
                car5.setCurrentDirection(5);
                car5.setOwnerName("Sophia");
                System.out.println(car5);

                //using 1 arg constructor
                Vehicle car6 = new Vehicle("Mike");
                car6.setCurrentSpeed(100);
                car6.setCurrentDirection(360);
                System.out.println(car6);

                //turns negative speed into positive speed
                //same with negative direction 
                Vehicle car7 = new Vehicle("Ally");
                car7.setCurrentSpeed(-40);
                car7.setCurrentDirection(-90);
                System.out.println(car7);

                Vehicle car8 = new Vehicle("Rob");
                car8.setCurrentSpeed(34);
                car8.setCurrentDirection(-180);
                System.out.println(car8);

                Vehicle car9 = new Vehicle("Jade");
                car9.setCurrentSpeed(25);
                car9.setCurrentDirection(180);
                System.out.println(car9);

                Vehicle car10 = new Vehicle("Alex");
                car10.setCurrentSpeed(1);
                car10.setCurrentDirection(5);
                System.out.println(car10);

                //testing highest ID method
                System.out.println("The highest ID created so far is: " + Vehicle.getHighestID());

                //testing changeSpeed method
                System.out.println();
                System.out.println("Changing the speed of car10 from 1 -> 100");
                car10.changeSpeed(100);
                System.out.println("Current speed of car10: " + car10.getCurrentSpeed());

                //testing stop method
                System.out.println();
                System.out.println("car10 got pulled over...");
                car10.stop();
                System.out.println("Current speed of car10: " + car10.getCurrentSpeed());

                //testing turn methods
                System.out.println();
                System.out.println("Turning car9 by +5 degrees");
                car9.turn(5);
                System.out.println("Car9's current direction: " + car9.getCurrentDirection());
                System.out.println("Now turning car9 left");
                car9.turn(Vehicle.TURN_LEFT);
                System.out.println("Car9's current direction: " + car9.getCurrentDirection());
                System.out.println("Now undoing the last turn by turning right");
                car9.turn(Vehicle.TURN_RIGHT);
                System.out.println("Car9's current direction: " + car9.getCurrentDirection());
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
  //calling the constructor for Vehicle
  PassengerVehicle(){
    super();
  }
  //calling the constructor for Vehicle
  PassengerVehicle(String name){
    super(name);
  }
  //calling the constructor for Vehicle
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
    this.totalSeats = Math.abs(seats);
  }
  //checking to see if the amount of occupied seats is valid. If not, simply set it to the max it could be.
  public void setOccupiedSeats(int seats){
    if(seats <= this.totalSeats){
      this.occupiedSeats = Math.abs(seats);
    }
    else{
      System.out.println("You can not occupy that many seats. Only letting the maximum amount of people in the car.");
      this.occupiedSeats = this.totalSeats;
    }
  }

  // override the toString method (inherited from the Object class) 
  // signature: toString() 
  // @Override
  // ...
  @Override
  //overring the toString method to print out the available seats which is the result of subtracting the occupied number of seats by the total amount of seats
  public String toString(){
    return this.getOwnerName() + "\'s vehicle has " + (this.getTotalSeats() - this.getOccupiedSeats()) + " seats remaining.";
  }

  // implement compareTo method (to `implements` Comparable)
  // signature: compareTo(PassengerVehicle)
  @Override
  //overloading the compareTo method according to its documentation. If the objects are equal, return 0. If the left object is less, return a -1. If the left object is greater, return a 1.
  public int compareTo(PassengerVehicle o) {
    if(this.totalSeats == o.totalSeats){
      return 0;
    }
    else if (this.totalSeats > o.totalSeats){
      return 1;
    }
    else{
      return -1;
    }
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
    //adding 5 elements to the arraylist, all of which are PassengerVehicle instances
    pVs.add(new PassengerVehicle("Bruce", 10));
    pVs.add(new PassengerVehicle("Madison", 2));
    pVs.add(new PassengerVehicle("Tyler", 5));
    pVs.add(new PassengerVehicle("Lucas", 4));
    pVs.add(new PassengerVehicle("Mia", 6));
    //sorting the arraylist in ascending order
    Collections.sort(pVs); // Sort the PassengerVehicles 
                           // in an ascending order according to
                           // the compareTo method implementation 

    // Find a way to output the ascending sorted result in descending
    // order. Use a for loop to print out the sorted result 
    // in a descending order
    //starting from the last element of the arraylist and going all the way until its first element
    //since the arraylist is in ascending order, this for loop traverses in descending order
    for(int i = pVs.size()-1; i >= 0; i--){
      System.out.println(pVs.get(i).getOwnerName() + "\'s vehicle has a total of " + pVs.get(i).getTotalSeats() + " seats.");
    }
    //setting the occupied seats
    System.out.println();
    System.out.println("9 people are trying to get into Bruce's car");
    pVs.get(4).setOccupiedSeats(9);
    System.out.println("6 people are trying to get into Mia's car");
    pVs.get(3).setOccupiedSeats(6);
    System.out.println("3 people are trying to get into Tyler's car");
    pVs.get(2).setOccupiedSeats(3);
    System.out.println("1 person is trying to get into Lucas's car");
    pVs.get(1).setOccupiedSeats(1);
    System.out.println("3 people are trying to get into Madison's car");
    pVs.get(0).setOccupiedSeats(3);
    System.out.println();
    //going through the arraylist of vehicles and using the toString method to print their available seats
    for(int i = pVs.size()-1; i >= 0; i--){
      System.out.println(pVs.get(i));
    }
  } // end of main

} // end of class PassengerVehicle

