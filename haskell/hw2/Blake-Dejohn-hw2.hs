
-- CSCE 314 [Sections  595, 596, 597] Programming Languages Spring 2024
-- Hyunyoung Lee
-- Homework Assignment 2 (Total 100 points) Due on Friday, 2/16/24 at 11:59 p.m.

-- Problem 1 (5 points)
-- Student Name: (Blake Dejohn)
-- UIN: (531002472)
-- Resources: (Programming in Haskell by Graham Hutton Chapters 5,6 and 7. Also used this for help with the alternating map function: https://codereview.stackexchange.com/questions/113156/a-map-function-that-alternates-between-two-mapping-functions)

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit
import System.Exit
import Data.List
import Data.Char

-- *** Read textbook Chapters 5, 6, and 7 ***

-- Problem 2 (Chapter 5, Exercise 9 (modified)) (10+5=15 points)
scalarproduct :: [Int] -> [Int] -> Int --the function declaration of scalarproduct. Takes in two lists of ints and returns a singular int.
---- Question 2.1 (10 points)
scalarproduct [] _  = 1 --Defining the case for empty list(s). If any of the two input lists are empty lists, just return the multiplicative identity (which is 1 since x * 1 = x)
scalarproduct xs ys = sum [x*y | (x,y) <- zip xs ys] --The base definition of scalarproduct. First,it creates a list of tuples that correspond to elements of the two input lists that are in the same index using the zip function (i.e groups the two elements that are at index 0 from list 1 and list 2, then groups the two elements at index 1 from list 1 and 2, etc.). Next, using list comprehension, from the tuple list created by the zip function, a new list is created by multiplying the elements grouped together in each tuple. Finally, the sum function is used on the resulting list to add all the elements of this product list, which gives a scalar result and overall defines the scalar product function.   

---- Question 2.2 (5 points)
{- Write your answer for Question 2.2 within this block comment.
Explaining the scalarproduct [3,2,4] [5..] = 55 call:

1. First, the zip function is used to create a list of tuples.
   zip [3,2,4] [5..]

2. Because of lazy evaluation, the infinite list denoted as the second input list is only evaluted as much as needed for the zip function (evalutes to only 3 elements since that is all the is needed for the list [3,2,4]).
  zip [3,2,4] [5,6,7]

3. The zip function returns a list of tuples that contain elements at the same index in each input list.
  zip [3,2,4] [5,6,7] = [(3,5),(2,6),(4,7)]

4. Per the function definition, list comprehension is used to create a new list that the sum function will use later. 
  [3*5, 2*6, 4*7] = [15, 12, 28]

5. Finally, the sum function is applied to this new list and a scalar answer is returned.
  sum[15, 12, 28] = (15+12+28) = 55
-}



-- Problem 3 (Chapter 6, Exercise 7) (10 points)
merge :: Ord a => [a] -> [a] -> [a] --Function declaration of merge. It takes 2 lists that include a type that must be belong to the Ord class. Then, it results a list of the same type within it 
merge [] n          = n --Defining the bases case of the merge function. If there is an empty list within any of the two input lists (or if they are both the empty list), then the input list that has not been pattern matched to the empty list is returned. If one of the inputs are the empty list, then we can simply add the remaining non-empty list to the end of the new list created by the merge function since we can assume that this list is already sorted.
merge n []          = n
merge (x:xs) (y:ys) = if x <= y then [x] ++ merge xs (y:ys) else --the main definition of the merge function. Using an if statement, the function looks at the head of each list (which is the smallest element of their respective lists). If the first list's head element is smaller than the second list's head element, then that head is appended to the recursive call of merge on the two lists without the head element of the first list. Else, the head element of the second list is appended to the recursive call of merge on the two lists without the head element of the second list. Based on the base cases for merge, when one or both of these lists become the empty list, then the list that is not pattern matched to the empty list is returned which will simply append the remaining list to the end of whatever new list was created beforehand (which will be a partially sorted list that only needs the elements from the remaining list). 
                         [y] ++ merge (x:xs) ys 



-- Problem 4 (Chapter 6, Exercise 8) (5+10=15 points)
---- Question 4.1 (5 points)
halve :: [a] -> ([a], [a]) --This function takes in a list of type variable "a" and returns a tuple of 2 lists that are of the same type
halve xs = splitAt (length xs `div` 2) xs --Using the splitAt function from the standard Prelude, halve is defined by calling this function and supplying it with an integer denoting the middle "split point" of the list and the original input list. The middle split point is found by finding the length of the inputted list and then dividing it by 2 using the div function. This tells what half the length of the inputted list is and therefore where the original list should be splitted at to make two halves (this guarantees that the tuple will contain two lists that differ in size at most by 1). 



---- Question 4.2 (10 points)
msort :: Ord a => [a] -> [a] --The merge sort function takes a list of type variable "a" and returns a list of the same type.
msort []  = [] --Base case for merge sort. If there is an empty list, it is considered sorted and therefore returned.
msort [x] = [x] --Second base case for merge sort. If there is a singleton list, it is also considered sorted and therefore returned.
msort xs  = let (firstHalf, secondHalf) = halve xs in merge (msort firstHalf) (msort secondHalf) --The main recursive definition of merge sort. First, the inputted list is split in half. Then, msort is called again on these two halves which makes them become further divided. This continues on until either one of the base cases is reached in which the recursion stops. From here, multiple merge function calls start to merge the singleton lists into 2 element lists and then these 2 element lists into 4 element lists and so on. Since the merge function calls make sure their return lists are sorted at each step, the soon-to-be returned list is more and more partially sort until its final merge call. From here, it merges the two half lists from the beginning (where the two halves are now sorted of course) to return the final sorted list.



-- Problem 5 (10+10+15=35 points)
---- Question 5.1 (10 points) 
mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a] --This function takes in a predicate function (function that takes in two inputs of the same type and returns a bool) and two lists of the same type variable marked "a". It then returns a list of the same type.
mergeBy _ [] ys = ys --Base cases for the mergeBy function. Since sorted list inputs are assumed, if one of the inputs turns up as the empty list, then it can be reasoned that the entire list was gone through by the function and the remaining non-empty list input can be appended to whatever partially sorted list was made by the recursive calls. 
mergeBy _ xs [] = xs
mergeBy (p) (x:xs) (y:ys) = if p x y then [x] ++ mergeBy p xs (y:ys) else --recursive definition of the mergeBy function. Very similar to the merge function recursive definition. All in all, it uses the head of the two lists (which will be their biggest or smallest elements based on how its sorted) on the predicate function and checks whether the function returns a True or False. If the function returns true, that means the pattern p x y was correct and therefore x can be appended to the recursive call of mergeBy on the two input lists without the head element of the first list. However, if it returns false, then the pattern p y x was correct and therefore y can be appended to the recursive call of mergeBy on the two lists without the head element of the second list. This will continue until one of the two base cases are reached in which the recursion stops and the new sorted list is created from all the appending operations.
                               [y] ++ mergeBy p (x:xs) ys

---- Question 5.2 (10 points) 
msortBy :: (a -> a -> Bool) -> [a] -> [a] --This function takes in a predicate function and a list of type variable "a". It returns a list of the same type.
msortBy _ []  = [] --Base cases for general merge sort. If an empty list or singleton list is passed as input, then simply return what was inputted.
msortBy _ [x] = [x]
msortBy p xs = let (firstHalf, secondHalf) = halve xs in mergeBy p (msortBy p firstHalf) (msortBy p secondHalf) --Recursive function definition for the modified merge sort. Logic is almost identical to the recursive definition of the general merge sort described in msort above. The only things that changed was the addition of using mergeBy (which is explained above) instead of merge and adding the predicate p where it was needed (such as in the recursive msortBy calls and the mergeBy calls). To summarize, mergeBy works exactly like merge except that it now takes in a predicate to change the order of its sorting and msortBy works exactly like msort except that it also takes in a predicate to change the order of its sort.

---- Question 5.3 (15 points)
{- Write your answer for Question 5.3 within this block comment.
Explaining the msortBy (>) [3,2,1,5,4] function call:

1. The list is first split in half
[3,2] and [1,5,4]

2. The calls msortBy (>) [3,2] and msortBy (>) [1,5,4] are made

3. This splits the first list again
[3] and [2]

4. The calls msortBy (>) [3] and msortBy (>) [2] are made

5. These calls return their respective singleton lists:
msortBy (>) [3] = [3] and msortBy (>) [2] = [2]

6. The mergeBy (>) [3] [2] call is made

7. This call returns [3] ++ mergeBy (>) [] [2] per the function definition

8. mergeBy (>) [] [2] returns [2] (base case)

9. mergeBy (>) [3] [2] call is resolved
mergeBy (>) [3] [2] = [3] ++ [2]

10. Going up some statements (to statement 2), the second list is halved again
[1] and [5,4] are returned

11. The msortBy (>) [1] is made

12. This call returns its singleton list
msortBy (>) [1] = [1]

13. The [5,4] list is split again
[5] and [4]

14. The calls msortBy (>) [5] and msortBy (>) [4] are made

15. These calls return their respective singleton lists
msortBy (>) [5] = [5] and msortBy (>) [4] = [4]

16. The mergeBy (>) [5] [4] call is made

17. This call returns [5] ++ mergeBy (>) [] [4]

18. mergeBy (>) [] [4] returns [4] (base case)

19. mergeBy (>) [5] [4] call is resolved
mergeBy (>) [5] [4] = [5] ++ [4]

20. The mergeBy (>) [1] [5,4] is made

21. This call returns [5] ++ mergeBy (>) [1] [4]

22. The mergeBy (>) [1] [4] is made

23. This call returns [4] ++ mergeBy (>) [1] []

24. mergeBy (>) [1] [] returns [1]

25. As a result, mergeBy (>) [1] [5,4] results in [5] ++ [4] ++ [1]

26. The mergeBy (>) [3,2] [5,4,1] is made

27. This call retuns [5] ++ mergeBy (>) [3,2] [4,1]

28. The mergeBy (>) [3,2] [4,1] is made

29. This call returns [4] ++ mergeBy (>) [3,2] [1]

30. The mergeBy (>) [3,2] [1] is made

31. This call returns [3] ++ mergeBy (>) [2] [1]

32. The mergeBy (>) [2] [1] is made

31. This call returns [2] ++ mergeBy (>) [] [1]

32. The mergeBy (>) [] [1] is made

33. mergeBy (>) [] [1] returns its singleton list (base case)
mergeBy (>) [] [1] = [1]

34. The final sorted list takes the form [5] ++ [4] ++ [3] ++ [2] ++ [1] or [5,4,3,2,1]
-}



-- Problem 6 (Chapter 7, Exercise 9) (10+10=20 points)
---- Question 6.1 (10 points)
altMap :: (a -> b) -> (a -> b) -> [a] -> [b] --This function takes two functions that map from the same type domain to the same type codomain (i.e. they start with the same type and end with the same type) and a list of the same type as the domain.
altMap _ _ [] = [] --The base case for this funciton. If an empty list is passed to it, it is simply returned.
altMap f _ [x] = [f x] --Another base case. If a singleton list is passed to it, then only the first function will be applied to it and the corresponding list is returned.
altMap f g (x1:x2:xs) = f x1 : g x2 : altMap f g xs --The recursive definition of the altMap function. First, it applies the first and second functions to the first and second element respectively. After this, it recursively calls itself on the original input list without both the head element and the element after that. From here, multiple recursive calls are made until either one of the base cases are reached. When one of these cases are reached, the recursion stops and a new list is created by the multiple cons operations. This new list is returned by the function.

---- Question 6.2 (10 points)
{- Write your answer for Question 6.2 within this block comment.
Explaining the altMap (`div` 2) (*7) [1..9] call:

1. The [1..9] call is resolved and returns a list
[1,2,3,4,5,6,7,8,9]

2. The altMap (`div` 2) (*7) [1,2,3,4,5,6,7,8,9] call is made

3. This call returns (`div` 2) 1 : (*7) 2 : altMap (`div` 2) (*7) [3,4,5,6,7,8,9]

4. (`div` 2) 1 returns 0 while (*7) 2 returns 14

5. The altMap (`div` 2) (*7) [3,4,5,6,7,8,9] call is made

6. This call returns (`div` 2) 3 : (*7) 4 : altMap (`div` 2) (*7) [5,6,7,8,9]

7. (`div` 2) 3 returns 1 while (*7) 4 returns 28

8. The altMap (`div` 2) (*7) [5,6,7,8,9] call is made

9. This call returns (`div` 2) 5 : (*7) 6 : altMap (`div` 2) (*7) [7,8,9]

10. (`div` 2) 5 returns 2 while (*7) 6 returns 42

11. The altMap (`div` 2) (*7) [7,8,9] call is made

12. This call returns (`div` 2) 7 : (*7) 8 : altMap (`div` 2) (*7) [9]

13. (`div` 2) 7 returns 3 while (*7) 8 returns 56

14. The altMap (`div` 2) (*7) [9] call is made and immediately returns [(`div` 2) 9] (base case)

15. [(`div` 2) 9] evaluates to 4

16. Using the cons operator and starting from the beginning of the recursion calls, the final list is returned
 0 : 14 : 1 : 28 : 2 : 42 : 3 : 56 : [4] or [0, 14, 1, 28, 2, 42, 3, 56, 4]
-}




    
myTestList =
  TestList [
      "scalarproduct 1" ~: scalarproduct [4,5,6] [1,2,3] ~=? 32
    , "scalarproduct 2" ~: scalarproduct [2,3] [1,-1] ~=? -1
    , "scalarproduct 3" ~: scalarproduct [1..10] [1..5] ~=? 55
    , "scalarproduct 4" ~: scalarproduct [3,2,4] [5..] ~=? 55

    , "merge 1" ~: merge "EGG" "ABCDEFGH" ~=? "ABCDEEFGGGH" 
    , "merge 2" ~: merge "Hello" "e" ~=? "Heello"
    , "merge 3" ~: merge [1,3,5,7,9] [2,4,6] ~=? [1,2,3,4,5,6,7,9]

    , "halve 1" ~: halve "" ~=? ("","")
    , "halve 2" ~: halve "halves" ~=? ("hal","ves")
    , "halve 3" ~: halve "halve" ~=? ("ha","lve")

    , "msort 1" ~: msort "Howdy all!" ~=? " !Hadllowy"
    , "msort 2" ~: msort "" ~=? ""
    , "msort 3" ~: msort "Mississippi" ~=? "Miiiippssss"
    , "msort 4" ~: msort [3,2,1,5,4] ~=? [1,2,3,4,5]

    , "mergeBy 1" ~: mergeBy (>) "FED" "GBA" ~=? "GFEDBA"
    , "mergeBy 2" ~: mergeBy (<) "Howdy" "Maui" ~=? "HMaouiwdy"
    , "mergeBy 3" ~: mergeBy (>) [5,1] [6,4,3] ~=? [6,5,4,3,1]
      
    , "msortBy 1" ~: msortBy (<) "gig 'em" ~=? " 'eggim" 
    , "msortBy 2" ~: msortBy (>) "Jack be nimble" ~=? "nmlkieecbbaJ  "
    , "msortBy 3" ~: msortBy (<) "" ~=? ""
    , "msortBy 4" ~: msortBy (>) [3,2,1,5,4] ~=? [5,4,3,2,1]

    , "altMap 1" ~: altMap (* 10) (* 100) [1,2,3,4,5] ~=? [10,200,30,400,50]
    , "altMap 2" ~: and (altMap even odd [1..10]) ~=? False
    , "altMap 3" ~: altMap toLower toUpper "Haskell IS fun!" ~=? "hAsKeLl iS FuN!"
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
