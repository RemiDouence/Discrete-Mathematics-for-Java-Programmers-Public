-- This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
-- https://creativecommons.org/licenses/by-nc-nd/4.0/
-- Remi Douence

-- This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
-- https://creativecommons.org/licenses/by-nc-nd/4.0/
-- Remi Douence

-- Please do not distribute solutions but let people learn by doing the exercices.

-- we are going to define our very own versions of not, and, or
import Prelude hiding (not, and, or)

-- nand (not and)

table_nand T T = F
table_nand F T = T
table_nand T F = T
table_nand F F = T

-- not (negation)

table_not T = F
table_not F = T 

-- define not with: nand
not(e) = nand(e,e)

-- and (conjonction)

table_and T T = T 
table_and T F = F 
table_and F T = F
table_and F F = F

-- define and with: nand, not
and(e1,e2) = undefined

test_and = test and table_and 

-- or (disjonction)

table_or T T = T 
table_or F T = T 
table_or T F = T
table_or F F = F

-- define or with: nand, not, and
or(e1,e2) = undefined 

test_or = test or table_or 

-- imply (implication)

table_imply T T = T 
table_imply F T = T 
table_imply T F = F
table_imply F F = T

-- define imply with: nand, not, and, or
imply(e1,e2) = undefined 

test_imply = test imply table_imply

-- equiv (equivalence)

table_equiv T T = T 
table_equiv F T = F 
table_equiv T F = F
table_equiv F F = T

-- define equiv with: nand, not, and, or, imply
equiv(e1,e2) = undefined 

test_equiv = test equiv table_equiv

-- xor (exclusive or)

table_xor T T = F 
table_xor F T = T 
table_xor T F = T
table_xor F F = F

-- define xor with: nand, not, and, or, imply, equivalence
xor(e1,e2) = undefined 

test_xor = test xor table_xor

-- def (definition of a constant)
-- def(x,e1,e2) initialize the constant x with e1 then evaluate e2

-- examples 
example1_def = valueOf (def(a,T,and(a,a)))
example2_def = valueOf (def(b,F,or(b,not(b))))

-- forAll (universal quantification)

-- define forAll with nand, not, and, or, imply, equivalence, def
forAll(x,e) = undefined

-- excluded middle
example_forAll = valueOf (forAll(x,or(x,not(x))))

-- exists (existential quantification)

-- define exists with nand, not, and, or, imply, equivalence, def, forAll
exists(x,e) = undefined

example_exists = valueOf (exists(x,and(x,x)))

-- verify and is commutative
exo1 = verify (forAll(a,forAll(b,equiv(and(a,b),and(b,a)))))

-- verify true neutral element for and
exo2 = verify undefined

-- verify false absorbing element for and
exo3 = verify undefined

-- verify idempotency of and
exo4 = verify undefined

-- verify associativity of and
exo5 = verify undefined

-- verify or is commutative
exo6 = verify undefined

-- verify false neutral element for or
exo7 = verify undefined

-- verify true absorbing element for or
exo8 = verify undefined

-- verify idempotency of or
exo9 = verify undefined

-- verify associativity of or 
exo10 = verify undefined

-- verify or distribute over and
exo11 = verify undefined

-- verify and distribute over or
exo12 = verify undefined

-- verify de Morgan's laws for or
exo13 = verify undefined

-- verify de Morgan's laws for and
exo14 = verify undefined

-- propose and verify more laws for imply, equiv, xor, etc.


---------------------------------------------------------------------------
-- DO NOT READ BELOW ------------------------------------------------------
---------------------------------------------------------------------------

type Id = String

data Exp  
  = T 
  | F 
  | Nand (Exp,Exp) 
  | Def  (Id,Exp,Exp)
  | Var  Id
  deriving (Show,Eq)

type Environment = [(Id,Exp)]

set :: Id -> Exp -> Environment -> Environment 
set x e env = (x,e):env

get :: Id -> Environment -> Exp
get x ((y,e):env) 
  | x == y    = e
  | otherwise = get x env
get x [] = error ("variable inconnue " ++ x)

eval :: Environment -> Exp -> Exp 
eval env T               = T 
eval env F               = F 
eval env (Nand(b1,b2))   = table_nand (eval env b1) (eval env b2)
eval env (Var x)         = get x env
eval env (Def (x,b1,b2)) = eval (set x (eval env b1) env) b2

valueOf = eval []

test f t = 
  valueOf (f (T,T)) == t T T && 
  valueOf (f (F,T)) == t F T && 
  valueOf (f (T,F)) == t T F && 
  valueOf (f (F,F)) == t F F  

verify e = valueOf e == T

nand(b1,b2) = Nand (b1,b2)

def (Var x,b1,b2) = Def (x,b1,b2)

a = Var "a" 
b = Var "b"
c = Var "c"
d = Var "d" 
e = Var "e"
e1 = Var "e1"
e2 = Var "e2"
e3 = Var "e3"
f = Var "f"
g = Var "g" 
h = Var "h"
i = Var "i"
j = Var "j" 
k = Var "k"
l = Var "l"
m = Var "m" 
n = Var "n"
o = Var "o"
p = Var "p" 
q = Var "q"
r = Var "r"
s = Var "s"
t = Var "t"
u = Var "u" 
v = Var "v"
w = Var "w"
x = Var "x" 
y = Var "y"
z = Var "z"
