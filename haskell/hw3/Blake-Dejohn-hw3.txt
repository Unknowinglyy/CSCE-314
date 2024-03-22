-- CSCE 314 [Sections 595, 596, 597] Programming Languages Spring 2024
-- Homework Assignment 3 (Total 120 points)
-- Due on Friday, March 1, 2024

-- Problem 1 (5 points)
-- Student Name: (Blake Dejohn)
-- UIN: (531002472)
-- (For making the tree class an instance of show: https://stackoverflow.com/questions/35733219/haskell-display-tree-without-data-tree-or-derivingshow)
-- (Programming in Haskell by Graham Hutton sections 8.4 & 8.7)
-- (Reminding myself out preorder, inorder, postorder tree traversal: https://www.programiz.com/dsa/tree-traversal)
-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit
import System.Exit

-- *** Read Chapters 8 and 16 ***

data Tree a b = Leaf a | Branch b (Tree a b) (Tree a b) --Defining a tree data type. A tree value can either be a leaf or branch. If it is a leaf, then it has one value. If it is a branch, then it is recursively defined as having two subtrees and its own value (different from a leaf's value)

---- Tree objects to be used to test your functions in Problems 2 and 3
-- Use tree1 to show the step-by-step of your functions in Problem 3
tree1 :: Tree Int String
tree1 = Branch "*" 
            (Branch "+"
               (Branch "*" (Leaf 2) (Leaf 6))
               (Branch "+" (Leaf 3) (Leaf 4)))
            (Branch "*"
               (Branch "+"
                  (Branch "*" (Leaf 8) (Leaf 2))
                  (Leaf 7))
               (Branch "+" (Leaf 5) (Leaf 4)))

-- Another example Tree object
tree2 :: Tree Int String 
tree2 = Branch "+" 
            (Branch "*" (Leaf 1) (Leaf 2)) 
            (Leaf 3)

-- Yet another Tree object
tree3 :: Tree Int String
tree3 = Branch "+" 
            (Branch "*" 
               (Leaf 3)
               (Leaf 4))
            (Branch "+"
               (Branch "*" (Leaf 5) (Leaf 2))
               (Leaf 1))
---------------

-- Problem 2 (20 points)
instance (Show a, Show b) => Show (Tree a b) where --syntax for making the Tree data type able to be printed through the show function. Similar to function overloading in C++.
   show t = levelShow 0 t --Calls the levelShow function when trying to show a tree. The zero denotes starting off on a depth of 0. The variable "t" is type inferred to be of type Tree.
     where
        addTab n x = concat (replicate n "\t") ++ show x ++ "\n" --function that adds the required tab characters. Overall, it takes in an int (which denotes the current depth) and a value with a type that can be used with the show function. It first replicates the tab character as many times as denotes in its int parameter. Then, concatenates these characters together into a single list with the concat function. Moving on, this is appended to the result of the show function on its second parameter, which is then appneded to a newline character (as every value within either a branch or leaf starts on a newline)

        levelShow n (Leaf a) = addTab n a --If the function is called upon a leaf, then it can simply use the addTab function on the Leaf's value. (this returns a string with a number of tabs, the value of the show function called on variable "a", and a newline at the end of it)
        levelShow n (Branch b lT rT) = addTab n b ++ levelShow (n+1) rT ++ levelShow (n+1) lT --If the function is called upon a branch, then it should first create the string associated with the branch value (using the addTab function), append this resulting string to the recursive call of the function to the right subtree with an incremented "n" value (denotes moving down the tree to a deeper depth), and then finally appending this resulting string to the recursive call of the function to the left subtree with an incremented n value. Overall, this function aims to print the tree through the order of branch value first, right subtree values next, then left subtree values last (with recursive calls to any subtrees within subtrees using the same order). 


-- Problem 3 (15 + 20 = 35 points)
---- Problem 3.1 (5 + 5 + 5 = 15 points)
preorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
--Function takes in 3 parameters: two different functions that result in the same type "c" and a Tree object. The resulting type of this function should be a list of type "c" values
preorder lFunction bFunction (Leaf a) = [lFunction a] --If preorder encounters a leaf, then it is sufficient to return a list that contains the result of the leaf function applied to the value stored at that Leaf object.
preorder lFunction bFunction (Branch b lT rT) = [bFunction b] ++ preorder lFunction bFunction lT ++ preorder lFunction bFunction rT --If preorder encounters a branch, then it first calculates the result of the branch function with the parameter of the value stored at the Branch object (and puts it into a list). It then appends this result to a recursive call of preorder to the left subtree of the branch with the same leaf and branch functions that were used in the previous call of the function. Next, the result of this recursive call is finally appended to the recursive call of the preorder function called on the right subtree of the orginal Branch object (with the same functions). This corresponds to the behavior of preorder tree traversal, which is first printing the value located at the node, going down the left subtree and printing the value located there, continuing to go down any additional left subtrees if they exist, and then finally going down the right subtree and printing the value located there (with the same process of prining the value of the node traveled to first, then going down as left subtrees if they exist and then finally travelling to any right subtrees if they also exist).


inorder   :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
--same declaration as preorder
inorder lFunction bFunction (Leaf a) = [lFunction a] --Similar logic to preorder. If the function is called upon a leaf, then there is no additional traversing to be done and as a result a list containing the return of the leaf function on the leaf value is sufficient.
inorder lFunction bFunction (Branch b lT rT) = inorder lFunction bFunction lT ++ [bFunction b] ++ inorder lFunction bFunction rT --Similar logic to preoder also. If the function is called upon a branch, then there is no traversing to be done. However, with inorder, the traversing is different. First, inorder traverses to the left subtree which is denoted by the recursive call to inorder on the left subtree. Then, the result of this is appended to a list containing the result of the branch function on the original branch node value. Finally, this value is appended to the recursive call to inorder on the right subtree of the branch node. This agrees with the definition of inorder tree traversal as inorder hopes to print a tree by first going down as many left subtrees as it can, printing the value of the leaf it will inevitable reach, then printing the value of the branch node that is the parent of this leaf, and then finally printing the value of the right subtree. Of course, if additional subtrees are found between these steps, then the same order applies until all the values in the tree are printed out. 


postorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
--same declaration as preorder and inorder
postorder lFunction bFunction (Leaf a) = [lFunction a] --Similar logic to both preorder and inorder. A leaf object denotes no more traversal at that end of the tree and therefore returned as the result of applying the leaf function to the leaf value and putting it in a list.
postorder lFunction bFunction (Branch b lT rT) = postorder lFunction bFunction lT ++ postorder lFunction bFunction rT ++ [bFunction b] --Similar logic to both preorder and inorder. The only difference is that preorder has a different order from these two other traversal methods. Overall, postorder hopes to print trees by going down any left subtrees and printing their values, then any right subtrees and priting their values, and then finally printing the original branch node value. This is shown in the function by having postorder be recursively called onto itself on the left subtree, having this result be appended to the recursive call of postorder on the left subtree, and then finally that being appended to the list that contains the branch function applied to the original branch node value. This is in line with the definition of the postorder traversal method, which is to go down any left subtrees, then any right subtrees, and then finally printing the value located at the original branch node once all the subtrees are exhausted.


---- Problem 3.2 (10 + 10 = 20 points)
{-- Explain the step-by-step of the following expression (each 10 points).
    Your answer must be in detail step-by-step.

> preorder show id tree1 
1. preorder show id tree1 results in:
[id "*"] ++ preorder show id (Branch "+" (Branch "*" (Leaf 2) (Leaf 6))(Branch "+" (Leaf 3) (Leaf 4))) ++ preorder show id (Branch "*" (Branch "+" (Branch "*" (Leaf 8) (Leaf 2)) (Leaf 7)) (Branch "+" (Leaf 5) (Leaf 4)))

2. [id "*"] = ["*"]

3. preorder show id (Branch "+" (Branch "*" (Leaf 2) (Leaf 6))(Branch "+" (Leaf 3) (Leaf 4))) results in:
[id "+"] ++ preorder show id (Branch "*" (Leaf 2) (Leaf 6)) ++ preorder show id (Branch "+" (Leaf 3) (Leaf 4)))

4. [id "+"] = ["+"]

5. preorder show id (Branch "*" (Leaf 2) (Leaf 6)) results in:
[id "*"] ++ preorder show id (Leaf 2) ++ preorder show id (Leaf 6)

6. [id "*"] = ["*"]

7. preorder show id (Leaf 2) = [show 2] = ["2"]

8. preorder show id (Leaf 6) = [show 6] = ["6"]

9. preorder show id (Branch "+" (Leaf 3) (Leaf 4))) results in:
[id "+"] ++ preorder show id (Leaf 3) ++ preorder show id (Leaf 4)

10. [id "+"] = ["+"]

11. preorder show id (Leaf 3) = [show 3] = ["3"]

12. preorder show id (Leaf 4) = [show 4] = ["4"]

13. preorder show id (Branch "*" (Branch "+" (Branch "*" (Leaf 8) (Leaf 2)) (Leaf 7)) (Branch "+" (Leaf 5) (Leaf 4))) results in:
[id "*"] ++ preorder (Branch "+" (Branch "*" (Leaf 8) (Leaf 2)) (Leaf 7)) ++ preorder show id (Branch "+" (Leaf 5) (Leaf 4))


14. [id "*"] = ["*"]

15. preorder (Branch "+" (Branch "*" (Leaf 8) (Leaf 2)) (Leaf 7)) resuls in:
[id "+"] ++ preorder show id (Branch "*" (Leaf 8) (Leaf 2)) ++ preorder show id (Leaf 7)

16. [id "+"] = ["+"]

17. preorder show id (Branch "*" (Leaf 8) (Leaf 2)) results in:
[id "*"] ++ preorder show id (Leaf 8) ++ preorder show id (Leaf 2)

18. [id "*"] = ["*"]

19. preorder show id (Leaf 8) = [show 8] = ["8"]

20. preorder show id (Leaf 2) = [show 2] = ["2"]

21. preorder show id (Leaf 7) = [show 7] = ["7"]

22. preorder show id (Branch "+" (Leaf 5) (Leaf 4)) results in:
[id "+"] ++ preorder show id (Leaf 5) ++ preorder show id (Leaf 4)

23. [id "+"] = ["+"]

24. preorder show id (Leaf 5) = [show 5] = ["5"]

25. preorder show id (Leaf 4) = [show 4] = ["4"]

26. appending all the results together, you get:
["*"] ++ ["+"] ++ ["*"] ++ ["2"] ++ ["6"] ++ ["+"] ++ ["3"] ++ ["4"] ++ ["*"] ++ ["+"] ++ ["*"] ++ ["8"] ++ ["2"] ++ ["7"] ++ ["+"] ++ ["5"] ++ ["4"]

or

["*","+","*","2","6","+","3","4","*","+","*","8","2","7","+","5","4"]



> inorder show id tree1

1. inorder show id tree1 results in:
inorder show id (Branch "+" (Branch "*" (Leaf 2) (Leaf 6)) (Branch "+" (Leaf 3) (Leaf 4))) ++ [id "*"] ++ inorder show id (Branch "*" (Branch "+" (Branch "*" (Leaf 8) (Leaf 2)) (Leaf 7)) (Branch "+" (Leaf 5) (Leaf 4)))

2. inorder show id (Branch "+" (Branch "*" (Leaf 2) (Leaf 6)) (Branch "+" (Leaf 3) (Leaf 4))) results in:
inorder show id (Branch "*" (Leaf 2) (Leaf 6)) ++ [id "+"] ++ inorder show id (Branch "+" (Leaf 3) (Leaf 4))

3. inorder show id (Branch "*" (Leaf 2) (Leaf 6)) results in:
inorder show id (Leaf 2) ++ [id "*"] ++ inorder show id (Leaf 6)

4. inorder show id (Leaf 2) = [show 2] = ["2"]

5. [id "*"] = ["*"]

6. inorder show id (Leaf 6) = [show 6] = ["6"]

7. [id "+"] = ["+"]

8. inorder show id (Branch "+" (Leaf 3) (Leaf 4)) results in:
inorder show id (Leaf 3) ++ [id "+"] ++ inorder show id (Leaf 4)

9. inorder show id (Leaf 3) = [show 3] = ["3"]

10. [id "+"] = ["+"]

11. inorder show id (Leaf 4) = [show 4] = ["4"]

12. [id "*"] = ["*"]

13. inorder show id (Branch "*" (Branch "+" (Branch "*" (Leaf 8) (Leaf 2)) (Leaf 7)) (Branch "+" (Leaf 5) (Leaf 4))) results in:
inorder show id (Branch "+" (Branch "*" (Leaf 8) (Leaf 2)) (Leaf 7)) ++ [id "*"] ++ inorder show id(Branch "+" (Leaf 5) (Leaf 4))

14. inorder show id (Branch "+" (Branch "*" (Leaf 8) (Leaf 2)) (Leaf 7)) results in:
inorder show id (Branch "*" (Leaf 8) (Leaf 2)) ++ [id "+"] ++ inorder show id (Leaf 7)

15. inorder show id (Branch "*" (Leaf 8) (Leaf 2)) results in:
inorder show id (Leaf 8) ++ [id "*"] ++ inorder show id (Leaf 2)

16. inorder show id (Leaf 8) = [show 8 ] = ["8"]

17. [id "*"] = ["*"]

18. inorder show id (Leaf 2) = [show 2] = ["2"]

20. [id "+"] = ["+"]

21. inorder show id (Leaf 7) = [show 7] = ["7"]

22. [id "*"] = ["*"]

23. inorder show id(Branch "+" (Leaf 5) (Leaf 4)) results in:
inorder show id (Leaf 5) ++ [id "+"] ++ inorder show id (Leaf 4)

24.inorder show id (Leaf 5) = [show 5] = ["5"]

25. [id "+"] = ["+"]

26. inorder show id (Leaf 4) = [show 4] = ["4"]

27. appending together you get:
["2"] ++ ["*"] ++ ["6"] ++ ["+"] ++ ["3"] ++ ["+"] ++ ["4"] ++ ["*"] ++ ["8"] ++ ["*"] ++ ["2"] ++ ["+"] ++ ["7"] ++ ["*"] ++ ["5"] ++ ["+"] ++ ["4"]

or

["2","*","6","+","3","+","4","*","8","*","2","+","7","*","5","+","4"]

--}
                          

-- Problem 4 (40 points) Chapter 8, Exercise 9 Modified
data Expr = Val Int | Add Expr Expr | Subt Expr Expr | Mult Expr Expr --An Expre object can be of four types: a single value, an add expression that contains two other expressions, a subtract expression that also contains two other expressions, and finally, a multiply expression that also contains two other expressions.

type Cont = [Op] --Defined a type that symbolizes the control stack. Overall, this is a list of operations that preserves the order of operations that the expression should follow in order to get a constant/correct answer.

data Op = EVALA Expr | ADD Int | EVALS Expr | SUBT Int | EVALM Expr | MULT Int --The different operations that are allowed to be in the control stack. These include the addition operation, the subtraction operation, and the multiplication operation. There are also ADD, SUBT, and MULT data types that include parameters that are not expressions. This is to differentiate between the different operations that contain expressions within their parameters and operations that only have an int as their parameter (when these operations have a single int as their parameter, then that means their expression was simplified to a single number and therefore can have this number applied to the next value it encounters with the operation that it specifies)

eval :: Expr -> Cont -> Int --the eval function takes in an expression and a control stack and then returns an integer
-- Give four definitions for eval.
-- First two definitions,
-- 1) for (Val n) and c as arguments and
eval (Val n) c = exec c n --If the function encounters a single value with the control stack, then simply call the execute function with the control stack and the value stored in the Val object
-- 2) for (Add x y) and c as arguments
eval (Add x y) c = eval x (EVALA y : c) --If the function encounters a Add object with two expressions, then you first need to add the add operation to the control stack and recursively call the evalulate function on the first value stored in the Add object and store the second value in the control stack along with the add operation (this denotes that x and y should be added together)
-- are already given in the text Section 8.7, but
-- you need to modify the second definition slightly
-- and give the third and fourth definitions for
-- (Subt x y) and (Mult x y)
eval(Subt x y) c = eval x (EVALS y: c) --Similar logic to the eval function call on the Add object. If the function is called on a Subt object, then it's recursively called on the first value stored in the Subt object and on the control stack with the subtract operation added to the beginning of stack with the second value stored in the Subt object stored with this subtract operation. (means that the value x should be subtracted to the y value when it finally does it evaluated)
eval(Mult x y) c = eval x (EVALM y: c) --Similar logic to the previous eval function calls. If the function is called on a Mult object, then it will recursively call itself on the first value stored in the Mult object with the control stack that has the multiplication operation added to the beginning of it. (means that the value x should be multiplied by the value y when it finally does get evaluated)


exec :: Cont -> Int -> Int --The exec function takes in a control stack and an integer and returns a integer.
-- Give seven definitions for exec, one for an empty list and
-- one for each of the six constructors of the data type Op
-- Some of these are already given in the text Section 8.7.
exec [] n = n --If the exec function takes in an empty list and a intger, then it returns an integer 

exec (EVALA y : c) n = eval y (ADD n : c) --If there is an EVALA operation on the top of the control stack, then the exec function calls the eval function with the expression stored in this operation along with adding an ADD object to the top of the control stack and placing the integer value that the exec function was called with in this ADD object. (means that the y expression is not fully simplified and therefore an ADD operation should be added to the top of the stack while the y expression object is in the process of being simplified) 
exec (ADD n : c) m = exec c (n+m) --If the exec function is called on a control stack that has an ADD object on the top if it, then the function is recursively called on itself with the control stack without its head element and a new integer that is found by adding the value within the ADD object and the previous integer that was called with the exec function. (this symbolizes simplifying the ADD object into a new integer value and popping an add operation from the top of the control stack)

exec (EVALS y: c) n = eval y (SUBT n : c) --Similar logic to the calls above. If the exec function is called on a control stack with a EVALS object on the top of it, then the eval function is called on the expression contained within this object along with a control stack that has a SUBT object added to the top of it which includes the integer value that the exec function was called with. (means that the top of the control stack has an expression that is not yet simplified and therefore a SUBT operation should be added to the top of the stack while the expression is simplified)
exec (SUBT n : c) m = exec c (n-m) --If the exec function is called on a control stack that has a SUBT object on the top of it, then that means the expression was simplified and an integer is left. As a result, the exec function is recursively called on the control stack with its head element popped off and an new integer that is found by subtracting the value in the SUBT object and the previous integer called with the exec function. (denotes that the subtraction expression was simplified to a single integer and can be finally simplified by subtracting the value found in the SUBT object with the integer parameter in the exec function) 

exec (EVALM y : c) n = eval y (MULT n : c)--Similar logic to the calls above. IF the exec function is called on a control stack with a EVALM object on the top of it, then the eval function is called with the expression contained in the EVALM object and a control stack with a MULT object added to the top of it that contains the previous integer parameter in the exec function. (means that there is a multiplication operation that contains an expression that is not fully simplified, therefore the eval function should be called to simplify this expression and at the same time add a MULT object to the top of the control stack to preserve the order of operations)
exec (MULT n : c) m = exec c (n*m) --If the exec function is called on a control stack that has a MULT object on the top of it, then that means thereis an opportunity for simplification within this function. As a result, the exec function is called recursively with the control stack that has this MULT object popped from the top of it and a new integer that is found by multiplying the integer parameter in the previous exec function and the integer stored in the MULT object. (means that multiplication expression was simplified and therefore the overall expression can be simplified by popping from the control stack and multiplying the value in the MULT object and the integer parameter within the previous exec function.) 


value :: Expr -> Int
value e = eval e []

-- Following expressions are to test your eval and exec definitions
-- (2 + 3) + 4 = 9
e1 = (Val 3)    -- 3
e2 = (Add (Val 4) (Val 2))  -- 4 + 2 = 6
e3 = (Mult (Val 4) (Val 3))  -- 4 * 3 = 12
e4 = (Add (Subt (Val 5) (Val 3)) (Val 4))  -- (5 - 3) + 4 = 6
e5 = (Mult (Mult (Val 2) (Val 3)) (Val 4))  -- (2 * 3) * 4 = 24
e6 = (Mult (Add (Val 2) (Val 3)) (Val 4))  -- (2 + 3) * 4 = 20
e7 = (Mult (Subt (Val 3) (Val 1)) (Val 4))  -- (3 - 1) * 4 = 8
e8 = (Add (Mult (Val 2) (Val 3)) (Val 4))  -- (2 * 3) + 4 = 10
e9 = (Subt (Mult (Val 2) (Val 3)) (Add (Val 4) (Val 1))) -- (2 * 3) - (4 + 1) = 1
e10 = (Mult (Subt (Val 10) (Val 3)) (Add (Val 4) (Val 5))) -- (10 - 3) * (4 + 5) = 63
e11 = (Add (Mult (Add (Val 2) (Val 3)) (Mult (Val 4) (Val 5))) (Mult (Val 3) (Subt (Val 4) (Val 7)))) -- ((2 + 3) * (4 * 5)) + (3 * (4 - 7)) = 91


-- Problem 5 (20 points)
-- Show the step-by-step of the following application of value.
-- > value e9
{-- Your answer goes here. Your answer must be in detail step-by-step.

1. value (Subt (Mult (Val 2) (Val 3)) (Add (Val 4) (Val 1))) results in:    (value e = eval e [])
eval (Subt (Mult (Val 2) (Val 3)) (Add (Val 4) (Val 1))) []

2. eval (Subt (Mult (Val 2) (Val 3)) (Add (Val 4) (Val 1))) [] results in:   (eval(Subt x y) c = eval x (EVALS y: c))
eval (Mult (Val 2) (Val 3)) [EVALS (Add (Val 4) (Val 1))]

3. eval (Mult (Val 2) (Val 3)) [EVALS (Add (Val 4) (Val 1))] results in:     (eval(Mult x y) c = eval x (EVALM y: c))
eval (Val 2) [EVALM (Val 3), EVALS (Add (Val 4) (Val 1))]

4. eval (Val 2) [EVALM (Val 3), EVALS (Add (Val 4) (Val 1)))] results in:    (eval (Val n) c = exec c n)
exec [EVALM (Val 3), EVALS (Add (Val 4) (Val 1))] 2

5. exec [EVALM (Val 3), EVALS (Add (Val 4) (Val 1))] 2 results in:           (exec (EVALM y : c) n = eval y (MULT n : c))
eval (Val 3) [MULT 2, EVALS (Add (Val 4) (Val 1))] 

6. eval (Val 3) [MULT 2, EVALS (Add (Val 4) (Val 1))] results in:            (eval (Val n) c = exec c n )
exec [MULT 2, EVALS (Add (Val 4) (Val 1))] 3

7. exec [MULT 2, EVALS (Add (Val 4) (Val 1))] 3 results in:                  (exec (MULT n : c) m = exec c (n*m))
exec [EVALS (Add (Val 4) (Val 1))] (2*3) = exec [EVALS (Add (Val 4) (Val 1))] 6

8. exec [EVALS (Add (Val 4) (Val 1))] 6 results in:                          (exec (EVALS y: c) n = eval y (SUBT n : c))
eval (Add (Val 4) (Val 1)) [SUBT 6]

9. eval (Add (Val 4) (Val 1)) [SUBT 6] results in:                           (eval (Add x y) c = eval x (EVALA y : c))
eval (Val 4) [EVALA (Val 1), SUBT 6]

10. eval (Val 4) [EVALA (Val 1), SUBT 6] reults in:                          (eval (Val n) c = exec c n )
exec [EVALA (Val 1), SUBT 6] 4

11. exec [EVALA (Val 1), SUBT 6] 4 results in:                               (exec (EVALA y : c) n = eval y (ADD n : c))
eval (Val 1) [ADD 4, SUBT 6]

12. eval (Val 1) [ADD 4, SUBT 6] results in:                                 (eval (Val n) c = exec c n)
exec [ADD 4, SUBT 6] 1

13. exec [ADD 4, SUBT 6] 1 results in:                                       (exec (ADD n : c) m = exec c (n+m))
exec [SUBT 6] (4+1) = exec [SUBT 6] 5

14. exec [SUBT 6] 5 results in:                                              (exec (SUBT n : c) m = exec c (n-m))
exec [] (6-5) = exec [] 1

15. exec [] 1 results in:                                                    (exec [] n = n)
1

1 is the final result
--}



myTestList = 
  TestList [
  
    "preorder 1"  ~: (concat (preorder show id tree1)) ~=? "*+*26+34*+*827+54"
  , "inorder 1"   ~: (concat (inorder show id tree1))  ~=? "2*6+3+4*8*2+7*5+4"
  , "postorder 1" ~: (concat (postorder show id tree1)) ~=? "26*34++82*7+54+**"
  , "preorder 2"  ~: (concat (preorder show id tree2)) ~=? "+*123"
  , "inorder 2"   ~: (concat (inorder show id tree2))  ~=? "1*2+3"
  , "postorder 2" ~: (concat (postorder show id tree2))  ~=? "12*3+"
  , "preorder 3"  ~: (concat (preorder show id tree3)) ~=? "+*34+*521"
  , "inorder 3"   ~: (concat (inorder show id tree3))  ~=? "3*4+5*2+1"
  , "postorder 3" ~: (concat (postorder show id tree3))  ~=? "34*52*1++"

  , "value 1"  ~: value e1 ~=? 3
  , "value 2"  ~: value e2 ~=? 6
  , "value 3"  ~: value e3 ~=? 12
  , "value 4"  ~: value e4 ~=? 6
  , "value 5"  ~: value e5 ~=? 24
  , "value 6"  ~: value e6 ~=? 20
  , "value 7"  ~: value e7 ~=? 8
  , "value 8"  ~: value e8 ~=? 10
  , "value 9"  ~: value e9 ~=? 1
  , "value 10" ~: value e10 ~=? 63
  , "value 11" ~: value e11 ~=? 91
  
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