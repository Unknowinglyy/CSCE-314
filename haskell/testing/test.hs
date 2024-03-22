import System.IO ()

act :: IO (Char, Char)
act = do x <- getChar
         getChar
         y <- getChar
         return (x,y)

strlen :: IO ()
strlen = do putStr "Enter a string: "
            xs <- getLine
            putStr "The string has "
            putStr (show(length xs))
            putStrLn " characters"

match :: String -> String -> String
match xs ys = [if elem x ys then x else '-' | x <- xs]

cc:: IO (Char, Char)
cc = do getChar
        x <- getChar
        y <- getChar
        return(x,y)

xs >>= f = [y | x <- xs, y <- f x]

safetail::[x] -> [x]
safetail(xs) = if null(xs) then [] else tail (xs)

safetail1:: [x] -> [x]
safetail1 xs | null xs = []
             | otherwise = tail(xs)

safetail2:: [x] -> [x]
safetail2 [] = []
safetail2 xs = tail(xs)

inc :: Functor f => f Int -> f Int
inc = fmap (+1)

