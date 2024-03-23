Problem 1
This document serves as the answer to problem 1.
(used Java version 17 for this hw)
(Can also find all relevant code here: https://github.com/Unknowinglyy/CSCE314/tree/main/java/hw4)

Problem 2

compile statement: javac Fibonacci.java

run statement: java SubsetOutputFib {starting number} {ending number}
(replace "{starting number}" and "{ending number}" with the range of fibonacci numbers you would to see)

(e.g "I want to see the 4th through the 7th fibonacci numbers. Therefore I will type java SubsetOutputFib 4 7")

Which results in:
4: 3
5: 5
6: 8 *
7: 13

The * symbol denotes an even number.

There is multiple error checks within this program. If there are negative starting or ending numbers, then they will converted to their absolute value equivalents. Also, if the beginning number is greater than the ending number, then the two numbers will be switched (no errors for beginning number equaling the ending number).


Problem 3

compile statement: javac Fibonacci.java

run statment: java ImprovedFibonacci

The result of this is as follows:
Now printing array of Fibonacci numbers

fibonacci number 1: 1
is it even? No       

fibonacci number 2: 1
is it even? No

fibonacci number 3: 2
is it even? Yes

fibonacci number 4: 3
is it even? No

fibonacci number 5: 5
is it even? No

fibonacci number 6: 8
is it even? Yes

fibonacci number 7: 13
is it even? No

fibonacci number 8: 21
is it even? No

fibonacci number 9: 34
is it even? Yes

Overall, this program does the same thing as the original ImprovedFibonacci program from the "The Java Programming Language, 4th Edition" book, however, with the use of an array instead. (I also tweaked the outputing of the fibonacci sequence for better viewing)

Exact implementation is explained through the comments included within the Fibonacci.java file.

Problem 4, Problem 5, Problem 6, Problem 7, & Problem 8

compile statement: javac Vehicle.java

run statement: java VehicleTest

The result of this is as follows:
The current vehicle is owned by Bruce, has ID number 1, is going at a speed of 30 mph, and is heading in a direction that is 20 degrees from North.
The current vehicle is owned by Emma, has ID number 2, is going at a speed of 90 mph, and is heading in a direction that is 150 degrees from North.
The current vehicle is owned by Bryan, has ID number 3, is going at a speed of 10 mph, and is heading in a direction that is 140 degrees from North.
The current vehicle is owned by Noah, has ID number 4, is going at a speed of 60 mph, and is heading in a direction that is 0 degrees from North.
The current vehicle is owned by Sophia, has ID number 5, is going at a speed of 0 mph, and is heading in a direction that is 5 degrees from North.
The current vehicle is owned by Mike, has ID number 6, is going at a speed of 100 mph, and is heading in a direction that is 0 degrees from North.
The current vehicle is owned by Ally, has ID number 7, is going at a speed of 40 mph, and is heading in a direction that is 270 degrees from North.
The current vehicle is owned by Rob, has ID number 8, is going at a speed of 34 mph, and is heading in a direction that is 180 degrees from North.
The current vehicle is owned by Jade, has ID number 9, is going at a speed of 25 mph, and is heading in a direction that is 180 degrees from North.
The current vehicle is owned by Alex, has ID number 10, is going at a speed of 1 mph, and is heading in a direction that is 5 degrees from North.
The highest ID created so far is: 10

Changing the speed of car10 from 1 -> 100
Current speed of car10: 100

car10 got pulled over...
Current speed of car10: 0

Turning car9 by +5 degrees
Car9's current direction: 185
Now turning car9 left
Car9's current direction: 95
Now undoing the last turn by turning right
Car9's current direction: 185

Overall, I created the necessary fields, constructors, static methods and non-static methods, and overloaded methods for the Vehicle class.

I tested each of these implementations by creating 10 vehicle objects (through both the no argument constructor and single argument constructor), setting their fields through accessor methods, modifying their state through other methods, and then finally printing specific information about each object.

(The first block tests the construction of the objects through constructors, the setter methods, and the toString overloading while the other blocks test specifically the changing speed methods and then the turning methods, respectively)

Again, specific implementation is explained through the comments found in the Vehicle.java file.

Problem 9

compile statement: javac Vehicle.java

run statement: java PassengerVehicle

The result of this is as follows:
Bruce's vehicle has a total of 10 seats.
Mia's vehicle has a total of 6 seats.
Tyler's vehicle has a total of 5 seats.
Lucas's vehicle has a total of 4 seats.
Madison's vehicle has a total of 2 seats.

9 people are trying to get into Bruce's car
6 people are trying to get into Mia's car
3 people are trying to get into Tyler's car
1 person is trying to get into Lucas's car
3 people are trying to get into Madison's car
You can not occupy that many seats. Only letting the maximum amount of people in the car.

Bruce's vehicle has 1 seats remaining.
Mia's vehicle has 0 seats remaining.
Tyler's vehicle has 2 seats remaining.
Lucas's vehicle has 3 seats remaining.
Madison's vehicle has 0 seats remaining.

I implemented the PassengerVehicle class by having it extend from the Vehicle class and implementing the Comparable class.

To do this, I created the necessary specific fields (total seats and occupied seats), constructors (using the super keyword to implement the Vehicle class's constructor), and getters and setters for these specific fields.

Finally, to complete the implementation, I overloaded both the toString method and compareTo methods for the PassengerVehicle class (with the former using the occupied and total seats fields while the latter only uses the total seats field).

To test my implementation of the PassengerVehicle class, I used the provided ArrayList by creating 5 PassengerVehicle objects and adding them to the ArrayList. Then, I sorted these objects (using the sort function on ArrayLists which uses my compareTo overloaded method) in ascending order. Next, to print them in descending order, I iterated through the ArrayList using a for loop by starting at the last index and going all the way until the first index. Finally, after setting different amounts of occupied seats for each object in the ArrayList, I outputted the available seats in each object within the ArrayList. (this comes with error checking as if the number of occupied seats exceeds the total number of seats for that object, then the occupied seats will simply be equal to the total number of seats)

If curious about anything discussed here, more explanation is available in the comments in the PassengerVehicle class within Vehicle.java.