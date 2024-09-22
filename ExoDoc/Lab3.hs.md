-- This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
-- https://creativecommons.org/licenses/by-nc-nd/4.0/
-- Remi Douence
# Lab3
```
-- Please do not distribute solutions but let people learn by doing the exercices.
```
## Key Points
- natural inductive definition `Nat`
    - zero `Z`
    - successor `S Nat`
    - infinite number of values (saturate the set)
    - binary function symbol: `add Nat Nat`
- equation
    - left-hand side = right-hand side
    - each side is a `Nat` expression 
    - each side can contain variables 
    - right-hand side variables must occur in the left-hand side
    - example : `add Z y = y` 
    - rules can be recursive
    - example : `add (S x) y = S (add x y)`
- rewriting rules 
    - rule = equation
    - rewrite an expression with a rule
    - unify the left-hand side with a sub-expression
    - example: `add (S x) y` unifies with `add (S (S (S Z))) (S Z)` with `(x,S (S Z))` and `(y,S Z)`
    - substitute the variables by their value in the right-hand side
    - example: `add (S (S (S Z))) (S Z)` is rewriten to `S (add (S (S Z)) (S Z))`
    - apply rules until fixpoint = computation
    - example: `add (S (S (S Z))) (S Z)` is rewriten to `S (S (S (S Z)))`
- property 
    - property = equation
    - example: `add x Z = x`
    - impossible to test for an infinite number of values for `x`
- proof (of property) by induction    
    1. check property for `x` is `Z`?
    2. assume hypothesis: the property is true for `x` is `i`
    3. check property for `x` is `S i`?
    4. result: the property is true for `x` is any value
```
{-# OPTIONS_GHC -Wno-x-partial #-} 

addZ = Rule (add Z     y) y
addS = Rule (add (S x) y) (S (add x y))

test_unify = unify (add (S x) y) (add (S (S (S Z))) (S Z))

test_subst = subst [(x,S (S Z)),(y,S Z)] (S (add x y))

test_rewrite = rewrite [addZ,addS] (add (S (S (S Z))) (S Z))

test_add = eval [addZ,addS] (add (S (S (S Z))) (S Z))

-- x+0 = x
prop11 = Rule (add x Z) x 
test_prop11 = prove prop11 x [] [] 

-- (x+y)+z = x+(y+z)
prop12 = Rule (add (add x y) z) (add x (add y z))
test_prop12 = prove prop12 x [] []

-- (1+x)+y = x+(1+y)
prop13 = Rule (add (S x) y) (add x (S y))
test_prop13 = prove prop13 x [] []

-- x+(1+y) = (1+x)+y
prop14 = Rule (add x (S y)) (add (S x) y)
test_prop14 = prove prop14 x [] []

-- x+y = y+x
prop15 = Rule (add x y) (add y x)
test_prop15 = prove prop15 x [] []

-- add2

add2Z = Rule (add2 x Z)     x
add2S = Rule (add2 x (S y)) (S (add2 x y))

test_add2 = eval [add2Z,add2S] (add2 (S (S (S Z))) (S Z))

prop21 = Rule (add2 Z y) y
test_prop21 = prove prop21 y [] []

prop22 = Rule (add2 (add2 x y) z) (add2 x (add2 y z))
test_prop22 = prove prop22 z [] []

prop23 = Rule (add2 (S x) y) (add2 x (S y))
test_prop23 = prove prop23 y [] []

prop24 = Rule (add2 x (S y)) (add2 (S x) y)
test_prop24 = prove prop24 y [] []

prop25 = Rule (add2 x y) (add2 y x)
test_prop25 = prove prop25 y [] []

-- add3

add3Z = Rule (add3 Z     y) y
add3S = Rule (add3 (S x) y) (add3 x (S y))

test_add3 = eval [add3Z,add3S] (add3 (S (S (S Z))) (S Z))

prop31 = Rule (add3 x (S y)) (add3 (S x) y)
test_prop31 = prove prop31 x [] []

prop32 = Rule (S (add3 x y)) (add3 (S x) y)
test_prop32 = prove prop32 x [] []

prop33 = Rule (add3 (S x) y) (S (add3 x y)) 
test_prop33 = prove prop33 x [] []

prop34 = Rule (add3 x Z) x
test_prop34 = prove prop34 x [] []

prop35 = Rule (add3 x y) (add3 y x)
test_prop35 = prove prop35 x [] []

prop36 = Rule (add3 (add3 x y) z) (add3 x (add3 y z))
test_prop36 = prove prop36 x [] []

-- define multiplication
-- prove properties about multiplication


---------------------------------------------------------------------------
-- DO NOT READ BELOW ------------------------------------------------------
---------------------------------------------------------------------------

type Id = String

data Nat 
  = Z 
  | S Nat
  | Fun Id Nat Nat
  | Var Id
  | I
  deriving Eq

a = Var "a"
b = Var "b"
c = Var "c"
d = Var "d"
e = Var "e"
f = Var "f"
g = Var "g"
h = Var "h"
j = Var "j"
k = Var "k"
l = Var "l"
m = Var "m"
n = Var "n"
o = Var "o"
p = Var "p"
q = Var "k"
r = Var "r"
s = Var "s"
t = Var "t"
u = Var "u"
v = Var "v"
w = Var "w"
x = Var "x"
y = Var "y"
z = Var "z"
                
add  = Fun "add"
add2 = Fun "add2"
add3 = Fun "add3"
add4 = Fun "add4"
mul  = Fun "mul"

instance Show Nat where 
  show Z = "Z"
  show (S n) = "S " ++ bracket n
  show (Fun f n1 n2) = f ++ " " ++ bracket n1 ++ " " ++ bracket n2 
  show (Var x) = x
  show I = "i"
  
bracket Z = show Z
bracket (Var x) = show (Var x)
bracket I = show I
bracket n = "(" ++ show n ++ ")"

-- substitution, unification

unifiable :: Nat -> Nat -> Bool 
unifiable Z Z = True
unifiable (S n1) (S n2) = unifiable n1 n2
unifiable (Fun f x y) (Fun g z t) = f==g && unifiable x z && unifiable y t
unifiable (Var x) n = True
unifiable I I = True
unifiable _ _ = False

type Subst = [(Nat,Nat)]

unify :: Nat -> Nat -> Subst
unify Z Z = []
unify (S n1) (S n2) = unify n1 n2
unify (Fun f x y) (Fun g z t) = unify x z ++ unify y t
unify (Var x) n = [(Var x,n)]
unify I I = []

get :: Nat -> Subst -> Nat
get y ((x,n):e) 
  | y == x    = n
  | otherwise = get y e
get y [] = y

subst :: Subst -> Nat -> Nat 
subst e Z = Z
subst e (S n1) = S (subst e n1)
subst e (Fun f n1 n2) = Fun f (subst e n1) (subst e n2)
subst e (Var y) = get (Var y) e
subst e I = I

-- rewrite 

data Rule = Rule Nat Nat | Hyp deriving Eq

instance Show Rule where 
  show (Rule lhs rhs) = show lhs ++ " = " ++ show rhs
  show Hyp            = "hypothesis"

rewrite1 :: Rule -> Nat -> [Nat] 
rewrite1 (Rule lhs rhs) n 
  | unifiable lhs n = [subst (unify lhs n) rhs]
  | otherwise       = []

rewriteS :: [Rule] -> Nat -> [Nat]
rewriteS (r:rs) n = rewrite1 r n++rewriteS rs n
rewriteS []     n = []

rewriteAll :: [Rule] -> Nat -> [Nat]
rewriteAll rs Z = rewriteS rs Z 
rewriteAll rs (S n) = rewriteS rs (S n)++map S (rewriteAll rs n)
rewriteAll rs (Fun f n1 n2) 
  = rewriteS rs (Fun f n1 n2)++
    [Fun f n n2 | n <- rewriteAll rs n1]++
    [Fun f n1 n | n <- rewriteAll rs n2]
rewriteAll rs (Var x) = rewriteS rs (Var x)
rewriteAll rs I = rewriteS rs I 

rewrite :: [Rule] -> Nat -> Nat
rewrite rs n = head (rewriteAll rs n++[n])

eval :: [Rule] -> Nat -> Nat
eval rs = fixPoint (rewrite rs)
  where 
    fixPoint :: Eq a => (a -> a) -> a -> a 
    fixPoint f x | x==f x = x 
                 | otherwise = fixPoint f (f x)

-- property

type Prop = Rule

rewriteProp :: Rule -> Prop -> Prop 
rewriteProp r (Rule lhs rhs) 
  | lhs/=rewrite [r] lhs = Rule (rewrite [r] lhs) rhs
  | otherwise            = Rule lhs (rewrite [r] rhs)
    
substProp :: Subst -> Prop -> Prop 
substProp s (Rule lhs rhs) = Rule (subst s lhs) (subst s rhs)

hyp :: Rule
hyp = Hyp

lhs :: Rule -> Nat
lhs (Rule lhs rhs) = lhs


prove :: Prop -> Nat -> [Rule] -> [Rule] -> Proof
prove p x rZ rS | p `elem` rS = error "ERROR you cannot use the property but you must use hyp" 
prove p x rZ rS =
  let h = substProp [(x,I)] p
      pZ = substProp [(x,Z)] p 
      pS = substProp [(x,S I)] p 
      rewritePropS :: [Rule] -> Rule -> Rule
      rewritePropS []       g = g
      rewritePropS (Hyp:rs) g | g==rewriteProp h g = 
        error ("ERROR you must apply hypothesis " ++ show h ++ " on i")
      rewritePropS (Hyp:rs) g = rewritePropS rs (rewriteProp h g)
      rewritePropS (r:rs)   g = rewritePropS rs (rewriteProp r g)
  in Proof p (rewritePropS rZ pZ) (rewritePropS rS pS)

refl :: Rule -> Bool
refl (Rule lhs rhs) = lhs==rhs

data Proof = Proof Prop Rule Rule

instance Show Proof where 
  show (Proof p rZ rS) = 
    if (refl rZ && refl rS) 
    then ("CQFD you can now use the property " ++ show p ++ " in other proofs") 
    else "Proof in progress\n" ++ 
    "case Z: " ++ (if (refl rZ) then "done" else show rZ) ++ "\n" ++ 
    "case S: " ++ (if (refl rS) then "done" else show rS) 
```
