
-- CSCE 314 [Sections 595, 596, 597] Programming Languages, Spring 2024
-- Homework Assignment 1 (Total 140 points)

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: Blake Dejohn
-- UIN: 531002472
-- Resources: Programming in Haskell by Graham Hutton (Chapters 4 - 7)

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit  -- if this line causes compile error, try the following
                   -- command at the terminal prompt > cabal v1-install HUnit
import System.Exit


-- Example:
-- Write a recursive function mySum that sums all the numbers
-- in a list without using the prelude function sum.
mySum :: [Int] -> Int  -- type signature of mySum. mySum accepts a list of Ints
                       -- as its argument and returns an Int
mySum []     = 0  -- def.1
mySum (x:xs) = x + mySum xs -- def.2

{- Block comment over multiple lines is enclosed within {- and -}
Explanation:
The type of mySum tells us that mySum takes a list of Ints as an argument
and returns an Int that is the sum of all the Ints in the argument list.

Def.1 of mySum is the base case of the recursion, that is,
the argument list is empty, for which case the function value is 
defined as zero (summation identity).

Def.2 is when the argument list contains at least one element, 
namely x, in which case the function is defined as the sum of x 
and the result of the recursive call of mySum applied to the rest of 
the argument list, namely xs.
-}


-- Problem 2 (5+10 = 15 points)
qsort1 :: Ord a => [a] -> [a] --takes a list of ordered types and returns a list of the same ordered type 
---- Question 2.1 (5 points)
qsort1 []     = [] --defining the base case of the qsort1 function definition (if given an empty list, return an empty list)
qsort1 (x:xs) = qsort1 larger ++ [x] ++ qsort1 smaller --When given a list of ordered types, the first number is put in the middle of a new list and the numbers larger
                                                       --than that number are put in front of this new list while the numbers smaller than that number are put behind
                                                       --the middle number. The qsort1 function is recursively called on these two lists in which new larger and smaller
                                                       --lists are created and the cycle continues until the base case is reached.  After the base case is reached
                                                       --for each of the original function calls, all the lists are concatenated together to make the final list.
                                                       --(this final list is a sorted list of the ordered types in descending order)
                where
                    smaller = [a | a <- xs, a <= x]
                    larger  = [b | b <- xs, b > x]

---- Question 2.2 (10 points)
{- Write your answer for Question 2.2 within this block comment.
Explaining qsort1 [5,2,6,9,7]:
(going from left to right)

1. qsort1 [5,2,6,9,7] gives rise to the statement: 
qsort1 [6,9,7] ++ [5] ++ qsort1[2]

2. then qsort1 [6,9,7] results in:
qsort1 [9,7] ++ [6] ++ qsort1[]

3. then qsort1 [9,7] results in:
qsort1 [] ++ [9] ++ qsort1[7]

4. then qsort1 [] results in:
[]

5. then qsort1 [7] results in:
qsort1 [] ++ [7] ++ qsort1[]

6. qsort1[] result in:
[]

7. qsort1[] result in:
[]

8. Going up some levels, the qsort1[] at statement 2. gives:
[]

9. Going up some levels, the qsort1[2] at statement 1. gives:
qsort1[] ++ [2] ++ qsort1[]

10. qsort1[] result in:
[]

11. qsort1[] result in:
[]

Counting the recursive calls from each statement, you get a total of 10 recursive calls. Within each statement, per the function defintion, the first list is the larger variable, the middle number is the x variable, and the last list is the smaller variable. 
-}


-- Problem 3 (10 points)
lucas :: Int -> Int --this function takes an int as input and outputs another int
lucas 0 = 2 --defining that the 0th lucas number is equal to 2 (base case)
lucas 1 = 1 --defining that the 1st lucas number is equal to 1 (second base case)
lucas n = lucas(n-1) + lucas (n-2) --The recursive definition of the nth lucas number. Overall, it is found by adding the previous two lucas numbers. 
                                   --The two previous lucas numbers denoted by both n-1 and n-2. As a result, by the recursive definition of the function,
                                   --when some number n is inputted into the function, it will go into the n-2 & n-1 parameter until that expression
                                   --reaches either 0 or 1 in which the first two definitions of the lucas function kick in. (makes the recursion stop)


-- Problem 4 (10 points)
factorial :: Int -> Int --this function also takes an int as input and outputs another int
factorial 0 = 1 --defining that the 0th factorial is equal to 1 (base case)
factorial n = n * factorial(n-1) --The recursive definition of the factorial function. It can be described as the factorial of some number n is the product
                                 --of that number n times the factorial of the previous number (or n-1). This recursive definition of the factorial continues
                                 --until it reaches the base case. When it does reach this case, the previous declarations of the factorial function are
                                 --all resolved and they are multiplied to each other all the way to the base case in which the final result is given.


-- Problem 5 (5+10+10=25 points)
---- Question 5.1 (5 points)
semifactorial :: Int -> Int --this function takes an int as input and outputs a single int
semifactorial 0 = 1 --defining a base case for the semifactorial function (the 0th lucas number is equal to 1)
semifactorial 1 = 1 --defining a base case for the semifactorial function (the 1st lucas number is equal to 1)
semifactorial n = n * semifactorial(n-2) --The recursive definition of the semifactorial function. Overall, this function is defined as the inputted number times
                                         --the semifactorial of the second previous consecutive number. This recursive function call causes other recursive funciton
                                         --calls until either of the base cases are found. When this happens the final result is found by multiplying the orginal
                                         --n number by all the semifactorial numbers that are the second previous consecutive numbers of the previous n numbers.

---- Question 5.2 (10 points)
{- Write your answer for Question 5.2 within this block comment.
Explaining the function call semifactorial(12):
(going from left to right)

1. semifactorial(12) returns:
12 * semifactorial(10)

2. semifactorial(10) returns:
10 * semifactorial(8)

3. semifactorial(8) returns:
8 * semifactorial(6)

4. semifactorial(6) returns:
6 * semifactorial(4)

5. semifactorial(4) returns:
4 * semifactorial(2)

6. semifactorial(2) returns:
2 * semifactorial(0)

7. semifactorial(0) returns:
1

Counting the recursive function calls, you can see that there are 6 recursive function calls for the semifactorial function call semifactorial(12). This function works by multiplying the input number by the semifactorial of the second previous consecutive number. These semifactorial numbers are found recursively and the final result is found when the base case is reached and all the multiplication is done on all the resolved semifactorial function calls.

-}

---- Question 5.3 (10 points)
myfactorial :: Int -> Int --this function takes an integer and results an integer
myfactorial 0 = 1 --defining the base case for the myfactorial function (the 0th number is defined as 1)
myfactorial n = semifactorial(n) * semifactorial(n-1) --The definition of the myfactorial function using the semifactorial function in its definition.
                                                      --Overall, I created this definition by seperating the odd and even numbers that would be needed to be 
                                                      --multiplied to each other to create the full factorial function. From this, it can be seem that the 
                                                      --semifactorial(n) part of the definition deals with the type of numbers that the input number is.
                                                      --For example, if the original inputted number is odd, then this part of the definition will multiply
                                                      --all the odd numbers from the base case to the inputted number. As a result, the other part of the definition
                                                      --deals with the types of numbers that are opposite to the inputted number (if the inputted number is odd
                                                      --then the semifactorial(n-1) deals with the even numbers and multiplys them all together when the base case 
                                                      --is reached). After every semifactorial function call is resolved by reaching the base case, both parts of the
                                                      --function definition multiply together to finally make up the regular factorial function (which is all the 
                                                      --odd and even numbers from the inputted number till 1 multiplied together)



-- Problem 6 (10+15+10=35 points)
---- Question 6.1 (10 points)
term :: Num a => Int -> a -> a --The term function takes two inputs, one integer and another input that is of type Num.
                               --From these values, a value of type Num is returned.
term n x = if n == 1 then x else --The definition of the term function states that if the power variable (n) is 1, then simply
                                 --the base of the exponent is returned. (i.e. the result of any number x^1 = x).
              if n > 1 then x * (term(n-1) x) else 1 --Next, if the number is greater than one, then a recursive definition
                                                     --is invoked. This is done by multiplying the base variable (x) by the recursive function call term(n-1) x, which
                                                     --goes down the chain of values from n to 1 and multiplies each of these values by the base variable each time.
                                                     --This is the same as multiplying the base variable n number of times since that is the definition of the exponential function.
                                                     --Finally, I added a default case in the situation where when n < 1, the
                                                     --function returns the number one. This will lead to incorrect behavior
                                                     --for negative numbers but the problem statement leaves no mention of this
                                                     --so I will keep this default case as is (works perfectly when n = 0 though!).

---- Question 6.2 (15 points)
polynaive :: Num a => [a] -> Int -> a -> a --This function takes 3 inputs: the first input is a list of values that are
                                           --specifically of type Num, the second input is an integer, and the third input
                                           --is of the same type inside the list of the 1st input (type Num). Finally, this
                                           --function returns a single value that is of the same type as the 3rd input (type Num).
polynaive [a] n x   = a * (term n x) --The base case of the polynaive function. If there is a single 
                                     --number within the list parameter, then the function returns that number multiplied with
                                     --a call to the term function with the same degree parameter and input variable (n and x
                                     --respectively). This denotes the action of multiplying a coefficient with the value of
                                     --the input variable raised to the power of the degree variable. (a is multiplied to 
                                     --term n x, which returns the result of x raised to the nth power)
polynaive (a:as) n x = a * (term n x) + (polynaive as (n-1) x) --The main recursive definition of the polynaive function. Overall,
                                                               --it multiplies the first coefficient within the coefficient 
                                                               --list with the value of x raised to the power of n (gotten from
                                                               --term n x). After this, it adds the result of operation to a 
                                                               --recursive call of the polynaive function with decremented
                                                               --parameters, with the exception of the input variable (x) (i.e.
                                                               --the rest of the coefficient list without the first coefficient,
                                                               --the degree variable subtracted by one, and the same input
                                                               --parameter). This recursive call continues calling other recursive
                                                               --calls until it reaches the base case. When this happens, all
                                                               --these calls become resolved and become added with one another.
                                                               --Altogether, this function works by multiplying the coefficient of
                                                               --the polynomial with the value of the input variable raised to
                                                               --some power and then works down the polynomial by completing the
                                                               --same action until the end of the polynomial is reached in which
                                                               --the function adds all the coefficients multiplied to their 
                                                               --respective expressions until the final result is found.
polynaive [] _ _    = 1 --Added a "safe case". In the case that this function is called with an empty list, it will return a 1.
                        --This case was added for ease of mind mostly.

---- Question 6.3 (10 points)
{- Write your answer for Question 6.3 within this block comment.
Explaining polynaive [3,-4,2,7] 3 2:
(going from left to right)

1. polynaive [3,-4,2,7] 3 2 returns:
3 * (term 3 2) + (polynaive [-4,2,7] 2 2)

2. (term 3 2) returns:
2 * (term 2 2)

3. (term 2 2) returns:
2 * (term 1 2)

4. (term 1 2) returns:
2

5. (polynaive [-4,2,7] 2 2) returns:
-4 * (term 2 2) + (polynaive [2,7] 1 2)

6. (term 2 2) returns: (duplicate of statements 3-4)
2 * (term 1 2)

7. (term 1 2) returns:
2

8. (polynaive [2,7] 1 2) returns:
2 * (term 1 2) + (polynaive[7] 0 2)

9. (term 1 2) returns:
2

10. (polynaive[7] 0 2) returns:
7 * (term 0 2)

11. (term 0 2) returns:
1

Simplifying to get the final result is as easy as going up the chain of statements and replacing function calls with the values that they return. For example, to find the value that statement 10 returns, we can look at the statement 7 * term 0 2. Here, term 0 2 can be replaced with 1, since that is what it returns. As a result, polynaive[7] 0 2 returns 7 * 1 or 7. Moving up, we can find statement 8's value through a similar method. The call polynaive[7] 0 2 is replaced with 7 and term 1 2 is replaced with 2 to get the expression 2 * 2 + 7 or 11. Continuing, statement 5's is simplified by replacing the term call with its corresponding recursive calls and the polynaive call with the value we just found. This gives -4 * (2 * 2) + 11 or -5 since term 2 2 returns 2 * term 1 2 and term 1 2 returns 2. Continuing, finding statement 1's value is found in a similar way. The rewritten expression comes out to 3 * (2 * 2 * 2) + -5 or 19 since the term 3 2 call recurses twice (multiplying by 2 each time) and -5 is the value that we just found which was equal to the polynaive [-4,2,7] 2 2 call. We find that the final result is 19, which is what was denoted in the problem set.
-}



type Set a = [a]

-- Problem 7 (10 points)
isElem :: Eq a => a -> [a] -> Bool --This function takes two inputs: a value that must be apart of the Eq class and a list of values of the same type. From these
                                   --values, it returns a boolean value.
isElem _ [] = False --The base case of the function. If an empty list is given as a parameter for the 2nd input, then false is returned. Overall, this case denotes
                    --that either you started with an empty list which will just give you a false result, or that the function went through the entire list and found no
                    --elements that matched the 1st input. (in which it returns false)
isElem x (a:as) = if x == a then True else --The recursive definition of the function. In general, the function looks at the first element within the list. If it
                                           --is equal to the 1st input, then true is returned. If is not equal, then the function is called again with the same search
                                           --variable (x), but with a new list that contains all the previous elements except for the head (or the element that was 
                                           --just checked for equally with the search variable). This goes on until either the base case is hit, in which a false is
                                           --returned, or the element is found in the list, which returns a true.
                     isElem x as


-- Problem 8 (10 points)
-- Using isElem (from Problem 7) in the definition is required.
toSet :: Eq a => [a] -> Set a --This function takes one input, namely a list of values that have to belong to the Eq class. From this, it outputs a value of type Set
toSet [] = [] --The base case of the function. If an empty list is inputted to the function, then it is simply returned.
toSet (a:as) = if isElem a as then toSet as else --The recursive definition of the function. First, it checks if the element at the head of the list has an duplicates
                                                 --within the rest of the list. If it does, then the function is called again on a new list that does not include the 
                                                 --head. However, if the head is not contained again within the list, it is added to the new Set return value while
                                                 --the rest of the list is checked for duplicates in similar way. At the end, it will reach the base case and concatenate
                                                 --all the values that it has deemed do not have duplicates onto a new Set value that is then returned.
                  a : (toSet as)


-- Problem 9 (10 points)
-- Using isElem (from Problem 7) in the definition is required.
subset :: Eq a => Set a -> Set a -> Bool --This function takes two inputs of the same type, which is a Set variable. Then, it returns a boolean value.
subset [] _ = True --The base case for this recursive function. Overall, I decided to heavily refer to set notation for this base case definition. That is to say
                   --that the empty set is a subset of every other set (including itself). Therefore, if a empty set is given as an input, then it is decided that
                   --whatever set that comes as the second input has to be a superset (since the empty set is contained in every list) and will return a True value.
subset (a:as) b = if isElem a b then subset as b else --The recursive definition of the function. The function works by taking the first element of the first set,
                                                      --deciding if this element is contained in the second set using the isElem function, and going one of two ways
                                                      --based on this decision. From here, if the element is not contained in the second set, then the first set is 
                                                      --already not a subset of B and as a result False is returned. Otherwise, if the first element is contained in
                                                      --the second set, then the subset function is called again on a new list that does not contain the original first
                                                      --element and the original second set. This goes on until either there is an element in the first set that is not
                                                      --contained in the second set which would result in a False value or it gets all the way to the base case which
                                                      --denotes that the entire first set was iterated through and every element was contained in the second set (which
                                                      --will result in a True being returned).
                     False


-- Problem 10 (10 points)
-- Using subset (from Problem 9) in the definition is required.
setEqual :: Eq a => Set a -> Set a -> Bool --This function takes two inputs, in which both are of type Set (also need to be in the Eq class). From this, the function
                                           --returns a Boolean value.
setEqual a b = if (subset a b) && (subset b a) then True else --The function definition. Using the subset function defined above, I simply defined the setEqual function
                                                              --as returning True only if set a and set b where subsets of each other (i.e. a is a subset of b AND 
                                                              -- b is a subset of a). If this was not the case, then the function will return False. As a result,
                                                              --if a is subset of b but b is not a subset of a (or vice versa) then a False value will be returned.
                  False 



myTestList = 
  TestList [

      "qsort1 1" ~: qsort1 [3, 2, 5, 1, 8] ~=? [8,5,3,2,1]
    , "qsort1 2" ~: qsort1 "howdy" ~=? "ywohd"
    , "qsort1 3" ~: qsort1 "Oh, happy day!" ~=? "yypphhdaaO,!  "

    , "lucas 1" ~: lucas 0 ~=? 2
    , "lucas 2" ~: lucas 1 ~=? 1    
    , "lucas 3" ~: lucas 4 ~=? 7
    
    , "factorial 1" ~: factorial 3 ~=? 6
    , "factorial 2" ~: factorial 5 ~=? 120
    , "factorial 3" ~: factorial 10 ~=? 3628800

    , "semifactorial 1" ~: semifactorial 3 ~=? 3
    , "semifactorial 2" ~: semifactorial 5 ~=? 15
    , "semifactorial 3" ~: semifactorial 10 ~=? 3840

    , "myfactorial 1" ~: myfactorial 3 ~=? 6
    , "myfactorial 2" ~: myfactorial 5 ~=? 120
    , "myfactorial 3" ~: myfactorial 10 ~=? 3628800

    , "term 1" ~: term 3 2 ~=? 8
    , "term 2" ~: term 4 5 ~=? 625
    , "term 3" ~: term 10 3 ~=? 59049

    , "polynaive 1" ~: polynaive [2,-1,3,5] 3 2 ~=? 23
    , "polynaive 2" ~: polynaive [1,3,0,7,2] 4 5 ~=? 1037
    , "polynaive 3" ~: polynaive [(1/3),1,-2,0,2,1,(1/2)] 6 3 ~=? 345.5
    , "polynaive 4" ~: polynaive [3,-4,2,7] 3 2 ~=? 19

    , "isElem 1" ~: (isElem 'h' "happy") ~=? True
    , "isElem 2" ~: (isElem 'o' "happy") ~=? False
    , "isElem 3" ~: (isElem 'p' "happy") ~=? True

    , "toSet 1" ~: length (toSet "aardvark") ~=? 5
    , "toSet 2" ~: length (toSet "BartBart") ~=? 4

    , "subset 1" ~: subset [] [1,2] ~=? True
    , "subset 2" ~: subset [1,2] [] ~=? False
    , "subset 3" ~: subset [2,3] [1,2] ~=? False
    , "subset 4" ~: subset [2,3] [3,1,2] ~=? True
    , "subset 5" ~: subset [2,3] [2,1,4] ~=? False

    , "setEqual 1" ~: setEqual "abc" "bca" ~=? True
    , "setEqual 2" ~: setEqual [1,2] [2,1] ~=? True
    , "setEqual 3" ~: setEqual [1,2,3] [1,2,3,4] ~=? False
    , "setEqual 4" ~: setEqual [2,3,1] [1,2,3] ~=? True

    ]

main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          exitWith (codeGet errs fails)
          
codeGet errs fails
 | fails > 0       = ExitFailure 2
 | errs > 0        = ExitFailure 1
 | otherwise       = ExitSuccess

