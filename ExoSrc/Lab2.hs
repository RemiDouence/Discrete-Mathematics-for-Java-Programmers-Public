-- This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
-- https://creativecommons.org/licenses/by-nc-nd/4.0/
-- Remi Douence

-- This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
-- https://creativecommons.org/licenses/by-nc-nd/4.0/
-- Remi Douence

-- Please do not distribute solutions but let people learn by doing the exercices.

-- boolean expressions grammar (we assume there are implicit forall for all variables)
data Prop 
  = Imply(Prop,Prop) 
  | And(Prop,Prop) 
  | Or(Prop,Prop) 
  | Not(Prop)  
  | T
  | F
  | Var(Id)
  deriving Eq

a = Var("a")
b = Var("b")
c = Var("c") 
d = Var("d") 
e = Var("e") 
f = Var("f") 
g = Var("g") 
h = Var("h") 
i = Var("i") 
j = Var("j") 
k = Var("k") 
l = Var("l") 
m = Var("m") 
n = Var("n") 
o = Var("o") 
p = Var("p") 
q = Var("q") 
r = Var("r") 
s = Var("s") 
t = Var("t") 
u = Var("u") 
v = Var("v") 
w = Var("w") 
x = Var("x") 
y = Var("y") 
z = Var("z") 

-- proof tree grammar (`Hole` represents incomplete/in progress trees)
data Proof 
  = Hole
  | Axiom 
  | ImplyE(Proof,Proof,Prop)
  | ImplyI(Proof) 
  | AndLE(Prop,Proof)
  | AndRE(Prop,Proof)
  | AndI(Proof,Proof)
  | TI 
  | OrE(Prop,Prop,Proof,Proof,Proof)
  | OrLI(Proof)
  | OrRI(Proof)
  | FE(Proof) 
  | NotE(Prop,Proof,Proof)
  | NotI(Proof)
  deriving (Show,Eq)

prop1 = Imply(And(a,b),Or(a,b))
proof1 = check prop1 Hole -- 4 tactics

prop2 = Imply(Or(a,b),Or(b,a))
proof2 = check prop2 Hole -- 7 tactics

prop3 = Imply(a,Not(Not a))
proof3 = check prop3 Hole -- 5 tactics

prop4 = Imply(Imply(a,b),Imply(Not(b),Not(a)))
proof4 = check prop4 Hole -- 8 tactics

prop5 = Imply(Or(Not a,b),Imply(a,b))
proof5 = check prop5 Hole -- 9 tactics

prop61 = Imply(And(And(a,b),c),And(a,And(b,c)))  
proof61 = check prop61 Hole -- 11 tactics

prop62 = Imply(And(a,And(b,c)),And(And(a,b),c))
proof62 = check prop62 Hole -- 11 tactics

prop71 = Imply(And(a,a),a)
proof71 = check prop71 Hole -- 3 tactics

prop72 = Imply(a,And(a,a))
proof72 = check prop72 Hole -- 4 tactics

prop81 = Imply(And(T,a),a) 
proof81 = check prop81 Hole -- 3 tactics

prop82 = Imply(a,And(T,a))
proof82 = check prop82 Hole -- 4 tactics

prop9 = Imply(And(a,b),And(b,a))
proof9 = check prop9 Hole -- 6 tactics

propA1 = Imply(Or(a,a),a)
proofA1 = check propA1 Hole -- 5 tactics

propA2 = Imply(a,Or(a,a))
proofA2 = check propA2 Hole -- 3 tactics

propB1 = Imply(Or(F,a),a) 
proofB1 = check propB1 Hole -- 6 tactics

propB2 = Imply(a,Or(F,a))
proofB2 = check propB2 Hole -- 3 tactics

propC = Imply(a,a)
proofC = check propC Hole -- 2 tactics

propD = Imply(Imply(a,b),(Imply(Imply(b,c),Imply(a,c))))
proofD = check propD Hole -- 8 tactics

propE1 = Imply(Imply(And(a,b),c),Imply(a,Imply(b,c)))
proofE1 = check propE1 Hole -- 8 tactics

propE2 = Imply(Imply(a,Imply(b,c)),Imply(And(a,b),c))
proofE2 = check propE2 Hole -- 9 tactics

propF = Imply(Or(a,b),Imply(Not(a),b))
proofF = check propF Hole -- 16 tactics

propG = Imply(Not(And(a,b)),Imply(a,Not(b)))
proofG = check propG Hole -- 8 tactics

-- https://math.stackexchange.com/questions/2197480/symbolic-logic-proof-of-associativity
propH = Imply(Or(Or(a,b),c),Or(a,Or(b,c))) 


---------------------------------------------------------------------------
-- DO NOT READ BELOW ------------------------------------------------------
---------------------------------------------------------------------------

type Id = String

check prop proof = map S (check' ([],prop) proof)

check' (g,a) Hole = [(g,a)]
check' (g,a) Axiom | a `elem` g = []
check' (g,b) (ImplyE(t1,t2,a)) = check' (g,Imply(a,b)) t1 ++ check' (g,a) t2
check' (g,Imply(a,b)) (ImplyI t) = check' (a:g,b) t
check' (g,a) (AndLE(b,t)) = check' (g,And(a,b)) t
check' (g,b) (AndRE(a,t)) = check' (g,And(a,b)) t
check' (g,And(a,b)) (AndI(t1,t2)) = check' (g,a) t1 ++ check' (g,b) t2
check' (g,T) TI = []
check' (g,c) (OrE(a,b,t1,t2,t3)) = check' (g,Or(a,b)) t1 ++ check' (a:g,c) t2 ++ check' (b:g,c) t3
check' (g,Or(a,b)) (OrLI t) = check' (g,a) t 
check' (g,Or(a,b)) (OrRI t) = check' (g,b) t
check' (g,a) (FE t) = check' (g,F) t
check' (g,F) (NotE(a,t1,t2)) = check' (g,a) t1 ++ check' (g,Not a) t2
check' (g,Not(a)) (NotI t) = check' (a:g,F) t

instance Show Prop where 
  show = showProp
    where
      showProp (Var x) = x 
      showProp (Imply(p1,p2)) = "(" ++ showProp p1 ++ " => " ++ showProp p2 ++ ")" 
      showProp (And(p1,p2)) = "(" ++ showProp p1 ++ " /\\ " ++ showProp p2 ++ ")"
      showProp (Or(p1,p2)) = "(" ++ showProp p1 ++ " \\/ " ++ showProp p2 ++ ")"
      showProp (Not(p)) = "(!" ++ showProp p ++ ")"
      showProp T = "T" 
      showProp F = "F" 

data Sequent = S ([Prop],Prop)

instance Show Sequent where 
  show (S (ps,p)) = "\t" ++ concat ((map show ps) `sepBy` ", ") ++ " |- " ++ show p ++ "\n"
    where []     `sepBy` s = [] 
          [x]    `sepBy` s = [x]
          (x:xs) `sepBy` s = x:s:xs `sepBy` s

size :: Proof -> Int 
size Hole = 0
size Axiom = 1
size (ImplyE(p1,p2,_)) = 1 + size p1 + size p2
size (ImplyI(p)) = 1 + size p 
size (AndLE(_,p)) = 1 + size p 
size (AndRE(_,p)) = 1 + size p
size (AndI(p1,p2)) = 1 + size p1 + size p2
size (TI) = 1 
size (OrE(_,_,p1,p2,p3)) = 1 + size p1 + size p2 + size p3
size (OrLI(p)) = 1 + size p 
size (OrRI(p)) = 1 + size p 
size (FE(p)) = 1 + size p 
size (NotE(_,p1,p2)) = 1 + size p1 + size p2
size (NotI(p)) = 1 + size p
