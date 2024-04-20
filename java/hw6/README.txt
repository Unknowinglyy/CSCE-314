
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

First off, the reason why the output of this program is not always the same is because the program uses threads. Threads are not guaranteed to run in a specific order and so the output of the program can change depending on how the threads are scheduled/preempted to run (which is dependent on the operating system and the JVM).

With this in mind, the output of the program is as expected. The SimBox class acts as both sender and receiver of messages. To test the SimBox class, the SimMain class is used to create a few SimBox objects (which are essentially threads) and then create a separate thread that sends messages to the SimBox objects (using the SimBox class, of course). From here, the SimBox objects receive the messages in a specific order based on how the threads were scheduled. Finally, the output of the program is the messages that were sent and received by the SimBox objects. (most of the time, a portion of the final messages are not outputted because the program stops recieving messages after 5 seconds)

More information about how SimBox.java works can be found in the comments of the SimBox.java file.


===================
=====   Part 2   ======
===================

explanation of how java wildcard works:

Java wildcards work by allowing for a variable, method, or class to accept multiple different types. Using certain keywords like "extends" or "super" lets the programmer specify exactly what types are allowed to replace the wildcard. Using Market.java as an example, the sell bulk function has the function signature "void sell(Collection<? extends T> items)". What this means in layman's terms is that with this sell function, you can pass in any value into the sell function as long as the value is a type of Collection and the type that is contained within that collection is a subtype of the type passed into the Market class. This is useful because it allows for the sell function to be more flexible and accept more types of collections. 

how would the code be different if the wildcard was not used and only type variables?:

If I did not use wildcards within the Market class and only used type variables, then the Market class would be less flexible and more bloated. For example, still using the sell bulk function as a reference, I would now have to add more overloaded functions to the Market class to support different types of collections. This would include me adding the exact same function but with a different type parameter. Overall, this is almost always bad practice because it makes the code more verbose and harder to maintain.

===================
=====   Part 3   ======
===================

why do you need to synchronize the message queues in the SimBox class?:

You need to synchronize the message queues in the SimBox class because you want to make sure that the program is thread safe. Since the message queues are a shared resource (meaning that multiple threads can access them), you want to make sure the actions of accessing and modifying this resource are synchronized so that you do not run into race conditions. If you do not synchronize the message queues, then you run the risk of having multiple threads accessing the message queues at the same time and causing the program to behave in an unexpected way. This is because the threads can preempt each other and cause the program to behave in an unintended way.


why does my implementation of the SimBox class not create the possibility of a deadlock?:

My implementation of the SimBox class does not create the possibility of a deadlock because of the way I synchronize my message queues. Overall, when a thread obtains a lock on either the send or recieve function, there is no condition that the thread has to wait upon to continue executing. As a result, when a thread has gained a lock, executed what it needs to, and then releases the lock, another thread can immediately obtain the lock and continue executing. Also, when I synchonize both the global and private message queues, similarily, there is no condition that keeps the thread from doing nothing in an infinite loop, which would result in a deadlock (in fact, there is a condition that makes sure the thread stops when it needs to which is the aptly named stop instance variable!).