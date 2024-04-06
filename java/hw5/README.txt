Name: Blake Dejohn
UIN: 531002472

---------
Problem 2
---------

compile statment: (inside of P2 directory) javac Main.java
run statement: java Main S input.txt 

expected output:

input.txt
s ( 14.5, 1.0 ) 12.5
c ( 23, 37.5 ) 19  
s ( 6.5, 11.1 ) 25 
c ( 30, 50 ) 3     
c ( 51, 15 ) 4.1   
s ( 30, 60 ) 12    
c ( 16.5, 10.5 ) 52
s ( 73.5, 35 ) 100 
s ( 16.5, 10.5 ) 52
c ( 6.5, 11.1 ) 20 
s ( 26.5, 41.1 ) 5 
The input file contains 11 shapes.
11 shapes have been created
The square is at position (14.5, 1.0) with a side length of 12.5 and has an area of 
156.25
The circle is at position (23.0, 37.5) with a radius of 19.0 and has an area of 1134.1149479459152
The square is at position (6.5, 11.1) with a side length of 25.0 and has an area of 
625.0
The circle is at position (30.0, 50.0) with a radius of 3.0 and has an area of 28.274333882308138
The circle is at position (51.0, 15.0) with a radius of 4.1 and has an area of 52.81017250684442
The square is at position (30.0, 60.0) with a side length of 12.0 and has an area of 144.0
The circle is at position (16.5, 10.5) with a radius of 52.0 and has an area of 8494.8665353068
The square is at position (73.5, 35.0) with a side length of 100.0 and has an area of 10000.0
The square is at position (16.5, 10.5) with a side length of 52.0 and has an area of 2704.0
The circle is at position (6.5, 11.1) with a radius of 20.0 and has an area of 1256.6370614359173
The square is at position (26.5, 41.1) with a side length of 5.0 and has an area of 
25.0

The shapes sorted in ascending order of area are:

The square is at position (26.5, 41.1) with a side length of 5.0 and has an area of 
25.0
The circle is at position (30.0, 50.0) with a radius of 3.0 and has an area of 28.274333882308138
The circle is at position (51.0, 15.0) with a radius of 4.1 and has an area of 52.81017250684442
The square is at position (30.0, 60.0) with a side length of 12.0 and has an area of 144.0
The square is at position (14.5, 1.0) with a side length of 12.5 and has an area of 
156.25
The square is at position (6.5, 11.1) with a side length of 25.0 and has an area of 
625.0
The circle is at position (23.0, 37.5) with a radius of 19.0 and has an area of 1134.1149479459152
The circle is at position (6.5, 11.1) with a radius of 20.0 and has an area of 1256.6370614359173
The square is at position (16.5, 10.5) with a side length of 52.0 and has an area of 2704.0
The circle is at position (16.5, 10.5) with a radius of 52.0 and has an area of 8494.8665353068
The square is at position (73.5, 35.0) with a side length of 100.0 and has an area of 10000.0

The total area of all the shapes is: 24620.953051077784

Explanation:

Overall, this output shows the creation of Circle and Square objects that use both the Shape and Point classes. This output also shows different methods within their respective classes such as the toString() method and the calculate() method in the totalAreaCalculator class.

---------
Problem 3
---------

compile statment: (inside of P3 directory) javac CellTest.java
run statement: java CellTest

expected output:
===
1 22 21 12 24 17
sum of intlist is 97        
sum of null list is 0       
===
===
1.0 16.0 13.72 5.0 22.0 7.1 
sum ints = 97.0    
sum doubles = 64.82
===

Explanation:

Simiarily, this output shows the different methods that interact with Cell objects and overall proves that the creation of the Cell objects were indeed correct when linked together to make a linked list.

---------
Problem 4
---------

compile statment: (inside of P3 directory) javac CellListTest.java
run statement: java CellListTest

expected output:
stringlist = [(head: dove) -> (the) -> (the) -> (A)]
stringlist2 = [(head: the) -> (the) -> (dove) -> (A)] 
stringlist3 = [(head: the) -> (dove) -> (dove) -> (A)]
stringlist equals to stringlist2 ? false
stringlist equals to stringlist3 ? false
CellList<Integer> equals to CellList<String> ? false  
list  = [(head: 4) -> (3) -> (2) -> (1)]
list1 = [(head: 1) -> (3) -> (4) -> (2)]
list == list1 is false
list.equals(list1) = false
list3 = [(head: 1) -> (3) -> (2) -> (1)]
list4 = [(head: 4) -> (1) -> (3) -> (2) -> (1)]       
list1.equals(list3) = false
list1.equals(list4) = false
list.compareTo(list1) = 0
list.compareTo(list4) = -1

[(head: 4) -> (3) -> (2) -> (1)]
4
[(head: 22) -> (21) -> (3) -> (2) -> (1)]
22
[(head: 22) -> (21) -> (3) -> (2) -> (1)]
22 22
21 21
3 3
2 2
1 1

list1 = [(head: 1) -> (3) -> (4) -> (2)]
list2 = [(head: 1) -> (2) -> (3) -> (21) -> (22) -> (4) -> (3) -> (2) -> (1)]       
list2.compareTo(list1) = 1
=== end of test

Explanation:

This final problem shows my implementation of the CellList class. This overall displays the many methods used in this class that provide modification of the Cell linked list and displays informatoin of said list.

