qsort1 :: Ord a => [a] -> [a]
qsort1 []     = []
qsort1 (x:xs) = qsort1 larger ++ [x] ++ qsort1 smaller
                where
                    smaller = [a | a <- xs, a <= x]
                    larger  = [b | b <- xs, b > x]

lucas :: Int -> Int
lucas 0 = 2
lucas 1 = 1
lucas n = lucas(n-1) + lucas (n-2)

factorial:: Int -> Int
factorial 0 = 1
factorial n = n * factorial(n-1)

semifactorial :: Int -> Int
semifactorial 0 = 1
semifactorial 1 = 1
semifactorial n = n * semifactorial(n-2)

myfactorial :: Int -> Int
myfactorial 0 = 1
myfactorial n = semifactorial(n) * semifactorial(n-1)

term :: Num a => Int -> a -> a
term n x = if n == 1 then x else 
              if n > 1 then x * (term(n-1) x) else 1

polynaive :: Num a => [a] -> Int -> a -> a
polynaive [a] n x   = a * (term n x)
polynaive (a:as) n x = a * (term n x) + (polynaive as (n-1) x)
polynaive [] _ _    = 1


type Set a = [a]

isElem :: Eq a => a -> [a] -> Bool 
isElem _ [] = False
isElem x (a:as) = if x == a then True else
                     isElem x as

toSet :: Eq a => [a] -> Set a
toSet [] = []
toSet (a:as) = if isElem a as then toSet as else
                  a : (toSet as)

subset :: Eq a => Set a -> Set a -> Bool
subset [] _ = True
subset (a:as) b = if isElem a b then subset as b else
                     False

setEqual :: Eq a => Set a -> Set a -> Bool
setEqual a b = if (subset a b) && (subset b a) then True else
                  False

data Shape = Circle Float | Rect Float Float

area(Circle r) = pi * r^2
area(Rect x y) = x * y

c1 = Circle 2.7
c2 = Circle 3.51
r1 = Rect 3 4.19
r2 = Rect 7.8 2.6

ss = [c1,c2,r1,r2]

totalArea ss = (sum . filter(\n -> n >= 20 && n <= 30) . map area) ss


newtype StkType a = Stk [a] deriving Show
push x (Stk xs) = Stk (x:xs)
pop(Stk(_:xs)) = Stk xs
top(Stk(x:_))   = x --line(3)
empty           = Stk[]

stack1 = push(3::Int) . push 4 . push 5 $ empty
stack2 = pop stack1
-- module Stack (StkType, push, pop, top, empty) where
--     newtype StkType a = Stk [a] deriving Show --line(1)

--     push x (Stk xs) = Stk (x:xs)
--     pop(Stk (_:xs)) = Stk xs --line(2)
--     top(Stk(x:_))   = x --line(3)
--     empty           = Stk[]

--     stack1 = push(3::Int) . push 4 . push 5 $ empty
--     stack2 = pop stack1