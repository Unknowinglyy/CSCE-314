
   Outline written by Hyunyoung Lee for CSCE 314 Students Homework 6 Problem 1

   Student Name: Blake Dejohn

   UIN: 531002472

   Acknowledgements: All acknowledgements are listed in their respective java files. For this homework assignment, I worked on two java files: Market.java and SimBox.java. In the headers of each of these files, I list what resources I used to help me code those files.

   Can also look at all relevant code here if interested!: 
   https://github.com/Unknowinglyy/CSCE314/tree/main/java/hw6


===================
Part 1
===================

(used Java version 17 for this hw)

=== Problem 2
compile statment: javac Market.java

run statement: java Main

The output of the program is (line denotes the start and end of the output):
------------------------------
Here's what I bought
Apple
Gala
Apple
Apple
Gala
Fruit

Here's what my friend bought
Apple
Gala
Fruit

I bought this on the way out too: Gala

Enjoy!
------------------------------

explanation of the output: 

The first list of items are the tests supplied to us. The second list are used to test the buy bulk function with another container/basket (to ensure correct implementation) and the last line is a test of the single buy function. The output is as expected and the Market class is working as intended.

Within the Market.java file, the Market class is tested extensively. First, hidden from the output, both the single sell and bulk sell functions are used to populate the stock member variable of the Market class. Then, both versions of the buy function are used to fill a type of collection which is then outputted to the console. As can be seen, both buy function versions support being able to buy whatever type that is supplied in the stock member variable.

As an overview, however, the Market class lets a user sell any type that is a subtype of the type parameter supplied to the Market class and buy any item contained within the Market class's stock as long as the container that they supply to the function can hold any of the types contained within the stock. (meaning the container has a type parameter that is the same as the type parameter of the Market class)

Specific implementation details of the Market class are contained within the Market.java file and comments are provided to help understand the code.


=== Problem 3
compile statment: javac SimBox.java SimMain.java

run statement: java SimMain

One possible output of the program is:
------------------------------
From Homer to Marge: My doctor said don't walk.
From Marge to Homer: That was a traffic signal!
From Bart to Homer: There’s a 4:30 in the morning now?
From Homer to Bart: D'oh!
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
------------------------------

Another possible output of the program is:
------------------------------
From Marge to Homer: That was a traffic signal!
From Homer to Marge: My doctor said don't walk.
From Bart to Homer: There’s a 4:30 in the morning now?
From Homer to Bart: D'oh!
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
------------------------------

explanation of the output:



===================
=====   Part 2   ======
===================

explanation of how java wildcard works:

how would the code be different if the wildcard was not used?:

===================
=====   Part 3   ======
===================

why do you need to synchronize the message queues in the SimBox class?:

why does my implementation of the SimBox class not create the possibility of a deadlock?:
