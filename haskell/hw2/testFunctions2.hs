scalarproduct :: [Int] -> [Int] -> Int
scalarproduct [] _ = 1
scalarproduct xs ys = sum [x*y | (x,y) <- zip xs ys]

merge :: Ord a => [a] -> [a] -> [a]
merge [] n          = n
merge n []          = n
merge (x:xs) (y:ys) = if x <= y then [x] ++ merge xs (y:ys) else
                         [y] ++ merge (x:xs) ys 

halve :: [a] -> ([a], [a])
halve xs = splitAt (length xs `div` 2) xs

msort :: Ord a => [a] -> [a]
msort [] = []
msort [x] = [x]
msort xs = let (firstHalf, secondHalf) = halve xs in merge (msort firstHalf) (msort secondHalf)

mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a]
mergeBy _ [] ys = ys
mergeBy _ xs [] = xs
mergeBy (p) (x:xs) (y:ys) = if p x y then [x] ++ mergeBy p xs (y:ys) else
                               [y] ++ mergeBy p (x:xs) ys

msortBy :: (a -> a -> Bool) -> [a] -> [a]
msortBy _ []  = []
msortBy _ [x] = [x]
msortBy p xs = let (firstHalf, secondHalf) = halve xs in mergeBy p (msortBy p firstHalf) (msortBy p secondHalf)

altMap :: (a -> b) -> (a -> b) -> [a] -> [b]
altMap _ _ [] = []
altMap f _ [x] = [f x]
altMap f g (x1:x2:xs) = f x1 : g x2 : altMap f g xs