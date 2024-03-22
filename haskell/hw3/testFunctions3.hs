data Tree a b = Leaf a | Branch b (Tree a b) (Tree a b)
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

tree2 :: Tree Int String 
tree2 = Branch "+" 
            (Branch "*" (Leaf 1) (Leaf 2)) 
            (Leaf 3)

instance (Show a, Show b)=> Show (Tree a b) where
    show tree = levelShow 0 tree
     where
        addTab n x = concat (replicate n "\t") ++ show x ++ "\n"
        levelShow n (Leaf a) = addTab n a
        levelShow n (Branch b lT rT) = addTab n b ++ levelShow (n+1) rT ++ levelShow (n+1) lT


preorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]
preorder lFunction bFunction (Leaf a) = [lFunction a]
preorder lFunction bFunction (Branch b lT rT) = [bFunction b] ++ preorder lFunction bFunction lT ++ preorder lFunction bFunction rT

inorder   :: (a -> c) -> (b -> c) -> Tree a b -> [c] 
inorder lFunction bFunction (Leaf a) = [lFunction a]
inorder lFunction bFunction (Branch b lT rT) = inorder lFunction bFunction lT ++ [bFunction b] ++ inorder lFunction bFunction rT

postorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]
postorder lFunction bFunction (Leaf a) = [lFunction a]
postorder lFunction bFunction (Branch b lT rT) = postorder lFunction bFunction lT ++ postorder lFunction bFunction rT ++ [bFunction b]

data Expr = Val Int | Add Expr Expr | Subt Expr Expr | Mult Expr Expr

type Cont = [Op]

data Op = EVALA Expr | ADD Int | EVALS Expr | SUBT Int | EVALM Expr | MULT Int

eval :: Expr -> Cont -> Int
eval (Val n) c = exec c n
eval (Add x y) c = eval x (EVALA y : c)
eval(Subt x y) c = eval x (EVALS y: c)
eval(Mult x y) c = eval x (EVALM y: c)


exec :: Cont -> Int -> Int
exec [] n = n

exec (EVALA y : c) n = eval y (ADD n : c)
exec (ADD n : c) m = exec c (n+m)

exec (EVALS y: c) n = eval y (SUBT n : c)
exec (SUBT n : c) m = exec c (n-m)

exec (EVALM y : c) n = eval y (MULT n : c)
exec (MULT n : c) m = exec c (n*m)


value :: Expr -> Int
value e = eval e []

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



