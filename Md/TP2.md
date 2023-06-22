```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `Bool`
The class `Bool` (and its subclasses) represents an abstract syntax tree. 
This structure has no behavior (i.e., no method) beside `toString` and `equals`
```
package TP2.Bool;

public abstract class Bool {
```
`public String toString();` is inherited from `Object`. 
The printers are compliant with the syntax of Java for boolean expressions
```
	
```
`hasCode` is required by `HashSet`. 
Note that `HashSet.contains` calls `static Object.equals` calls `Object.equals` 
```
	public int hashCode() { 
		return 0;
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
## `True` 
```
package TP2.Bool;

public class True extends Bool {
	public String toString() {
		return "true";
	}
	public boolean equals(Object o) {
		return o instanceof True;
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
## `False` 
```
package TP2.Bool;

public class False extends Bool {
	final public String toString() {
		return "false";
	}
	public boolean equals(Object o) {
		return o instanceof False;
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
## `Var` 
```
package TP2.Bool;

public class Var extends Bool {
	final public String s;
	public Var(String s) {
		this.s = s;
	}
	public String toString() {
		return s;
	}
```
`equals` compares the sequence of characters (not the address of the `String`)
```
	public boolean equals(Object o) {
		return o instanceof Var 
			&& s.equals(((Var)o).s);
	}
}

```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
## `And` 
```
package TP2.Bool;

public class And extends Bool {
	final public Bool b1, b2;
	public And(Bool b1, Bool b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
	public String toString() {
		return "(" + b1 + " && " + b2 + ")";
	}
	public boolean equals(Object o) {
		return o instanceof And 
			&& b1.equals(((And)o).b1) 
			&& b2.equals(((And)o).b2);
	}
}

```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
## `Or` 
```
package TP2.Bool;

public class Or extends Bool {
	final public Bool b1, b2;
	public Or(Bool b1, Bool b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
	public String toString() {
		return "(" + b1 + " || " + b2 + ")";
	}
	public boolean equals(Object o) {
		return o instanceof Or 
			&& b1.equals(((Or)o).b1) 
			&& b2.equals(((Or)o).b2);
	}
}

```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
## `Not` 
```
package TP2.Bool;

public class Not extends Bool {
	final public Bool b1;
	public Not(Bool b1) {
		this.b1 = b1;
	}
	public String toString() {
		return "(!" + b1 + ")";
	}
	public boolean equals(Object o) {
		return o instanceof Not 
			&& b1.equals(((Not)o).b1);
	}
}

```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
## `Imply` 
```
package TP2.Bool;

public class Imply extends Bool {
	final public Bool b1, b2;
	public Imply(Bool b1, Bool b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
	public String toString() {
		return "(" + b1 + " => " + b2 + ")";
	}
	public boolean equals(Object o) {
		return o instanceof Imply 
			&& b1.equals(((Imply)o).b1) 
			&& b2.equals(((Imply)o).b2);
	}
}

```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `Sequent` 
```
package TP2;

import java.util.HashSet;

import TP2.Bool.*;

```
A `Sequent` is a `Pair<Set Bool, Bool>` 
```
public class Sequent {
```
`gamma` represents the environment as a set of `Bool`
```
	HashSet<Bool> gamma;
```
`goal` represents the expression to prove as a `Bool`
```
	Bool goal;
```
By default the environment `gamma` is empty (no hypothesis). 
```
	public Sequent(Bool goal) {
		this.gamma = new HashSet<Bool>();
		this.goal = goal;
	}
	public Sequent(HashSet<Bool> gamma, Bool goal) {
		this.gamma = gamma;
		this.goal = goal;
	}
```
`copy` returns a copy of the sequent by a shallow copy of the environment 
```
	public Sequent copy(Bool goal) {
		return new Sequent(new HashSet<Bool>(gamma), goal);
	}
```
The usual syntax for a goal under a set of hypotheses. 
```
	public String toString() {
		return gamma + " |- " + goal;
	}
}

```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `Tactic` 
```
package TP2;

import TP2.Bool.*;

public abstract class Tactic {
```
A tactic attempts to prove it sequent argument. 
A tactic returns `true` if the proof is in complete.
A tactic returns `false` if the proof is in progress.
An invalid tactic halts the program with an error message.
```
	public abstract boolean prove(Sequent s); 
```
The proof of a goal starts from an initial sequent (with no hypothesis). 
```
	public boolean prove(Bool goal) {
		return prove(new Sequent(goal));
	}
```
The printer returns an ascii representation of the tree of composed tactics 
```
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticAxiom`  
```
package TP2;

public class TacticAxiom extends Tactic {
	public String toString() {
		return "TacticAxiom";
	}
	public boolean prove(Sequent s) {
```
Checks if the goal is one of the hypotheses. 
```
		if (s.gamma.contains(s.goal)) {
			return true;
		} else {
			System.out.println("TacticAxiom.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticHole` (represents a proof in progress) 
```
package TP2;

public class TacticHole extends Tactic {
```
Each hole is identified by a fresh integer. 
```
	static int n = 0;
	int i;
	public TacticHole() {
		this.i= TacticHole.n++;
	}
	public String toString() {
		return "TacticHole" + i;
	}
```
A proof with a hole is incomplete. 
It prints the current sequent yet to be proved. 
```
	public boolean prove(Sequent s) {
		System.out.println("where " + this + " : " + s);
		return false;
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticTrueI` (true introduction) 
```
package TP2;

import TP2.Bool.*;

public class TacticTrueI extends Tactic {
	public String toString() {
		return "TacticTrueI";
	}
	public boolean prove(Sequent s) {
```
Checks if the goal is trivially true. 
```
		if (s.goal instanceof True) {
			return true;
		} else {
			System.out.println("TacticTrueI.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticFalseE` (false elimination)  
```
package TP2;

import TP2.Bool.*;

public class TacticFalseE extends Tactic {
	Tactic t;
	public TacticFalseE(Tactic t) {
		this.t = t;
	}
```
a proof in progress contains holes 
```
	public TacticFalseE() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticFalseE(" + t + ")";
	}
```
let us (try to) prove false.
```
	public boolean prove(Sequent s) {
		return t.prove(s.copy(new False()));
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticAndE1` (conjunction elimination) 
```
package TP2;

import TP2.Bool.*;

public class TacticAndE1 extends Tactic {
	Tactic t1;
	Bool e2;
	public TacticAndE1(Tactic t, Bool e2) {
		this.t1 = t;
		this.e2 = e2;
	}
	public TacticAndE1(Bool e2) {
		this(new TacticHole(),e2);
	}
	public String toString() {
		return "TacticAndE1(" + t1 + "," + e2 + ")";
	}
```
To prove A, let us prove A && B
```
	public boolean prove(Sequent s) {
		return t1.prove(s.copy(new And(s.goal,e2)));
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticAndE2` (conjunction elimination) 
```
package TP2;

import TP2.Bool.*;

public class TacticAndE2 extends Tactic {
	Bool e1;
	Tactic t2;
	public TacticAndE2(Bool e1, Tactic t2) {
		this.e1 = e1;
		this.t2 = t2;
	}
	public TacticAndE2(Bool e1) {
		this(e1, new TacticHole());
	}
	public String toString() {
		return "TacticAndE2(" + e1 + "," + t2 + ")";
	}
```
To prove A, let us prove B && A
```
	public boolean prove(Sequent s) {
		return t2.prove(s.copy(new And(e1,s.goal)));
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticAndI` (conjunction introduction) 
```
package TP2;

import TP2.Bool.*;

public class TacticAndI extends Tactic {
	Tactic t1, t2;
	public TacticAndI(Tactic t1, Tactic t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
	public TacticAndI(Tactic t1) {
		this(t1, new TacticHole());
	}
	public TacticAndI() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticAndI(" + t1 + "," + t2 + ")";
	}
```
To prove a conjunction, you have to make two proofs. 
```
	public boolean prove(Sequent s) {
		if (s.goal instanceof And) {
			And e = (And)s.goal;
			return   t1.prove(s.copy(e.b1)) 
				   & t2.prove(s.copy(e.b2));
		} else {
			System.out.println("TacticAndI.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticImplyE` (implication elimination) 
```
package TP2;

import TP2.Bool.*;

public class TacticImplyE extends Tactic {
	Bool b;
	Tactic t1, t2;
	public TacticImplyE(Bool b, Tactic t1, Tactic t2) {
		this.b = b;
		this.t1 = t1;
		this.t2 = t2;
	}
	public TacticImplyE(Bool b, Tactic t1) {
		this(b, t1, new TacticHole());
	}
	public TacticImplyE(Bool b) {
		this(b, new TacticHole());
	}
	public String toString() {
		return "TacticImplyE(" + b + "," + t1 + "," + t2 + ")";
	}
```
To prove B, 
let us introduce a new hypothesis A => B, prove B with this hypothesis
then prove A.  
```
	public boolean prove(Sequent s) {
		return t1.prove(s.copy(new Imply(b,s.goal)))
				&& t2.prove(s.copy(b));
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticImplyI` (implication introduction) 
```
package TP2;

import TP2.Bool.*;

public class TacticImplyI extends Tactic {
	Tactic t;
	public TacticImplyI(Tactic t) {
		this.t = t;
	}
	public TacticImplyI() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticImplyE(" + t + ")";
	}
```
To prove A => B, let us add A to the hypotheses and prove B
```
	public boolean prove(Sequent s) {
		if (s.goal instanceof Imply) {
			Imply b = (Imply)(s.goal);
			Sequent s1 = s.copy(b.b2);
			s1.gamma.add(b.b1);
			return t.prove(s1);
		} else {
			System.out.println("TacticImplyI.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticNotE` (negation elimination)  
```
package TP2;

import TP2.Bool.*;

public class TacticNotE extends Tactic {
	Bool b;
	Tactic t1, t2;
	public TacticNotE(Bool b, Tactic t1, Tactic t2) {
		this.b = b;
		this.t1 = t1;
		this.t2 = t2;
	}
	public TacticNotE(Bool b, Tactic t1) {
		this(b, t1, new TacticHole());
	}
	public TacticNotE(Bool b) {
		this(b, new TacticHole());
	}
	public String toString() {
		return "TacticNotE(" + b + "," + t1 + "," + t2 + ")";
	}
```
When the goal is false, 
this tactic attempts to show a contradiction 
by proving both a property `b` and its negation. 
```
	public boolean prove(Sequent s) {
		if (s.goal instanceof False) {
			return   t1.prove(s.copy(b)) 
				   & t2.prove(s.copy(new Not(b)));
		} else {
			System.out.println("TacticNotE.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticNotI` (negation introduction)
```
package TP2;

import TP2.Bool.*;

public class TacticNotI extends Tactic {
	Tactic t;
	public TacticNotI(Tactic t) {
		this.t = t;
	}
	public TacticNotI() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticNotI(" + t + ")";
	}
```
when the goal is a negation, 
this tactic adds the positive goal (without the negation) to the hypothesis
and attempts to prove false. 
This is also known as a proof by contradiction (preuve par l'absurde en francais). 
```
	public boolean prove(Sequent s) {
		if (s.goal instanceof Not) {
			Sequent s1 = s.copy(new False());
			s1.gamma.add(((Not)(s.goal)).b1);
			return t.prove(s1);
		} else {
			System.out.println("TacticNotI.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticOrE` (disjunction elimination) 
```
package TP2;

import TP2.Bool.*;

public class TacticOrE extends Tactic {
	Bool b1, b2;
	Tactic t1, t2, t3;
	public TacticOrE(Bool b1, Bool b2, Tactic t1, Tactic t2, Tactic t3) {
		super();
		this.b1 = b1;
		this.b2 = b2;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}
	public TacticOrE(Bool b1, Bool b2, Tactic t1, Tactic t2) {
		this(b1, b2, t1, t2, new TacticHole());
	}
	public TacticOrE(Bool b1, Bool b2, Tactic t1) {
		this(b1, b2, t1, new TacticHole());
	}
	public TacticOrE(Bool b1, Bool b2) {
		this(b1, b2, new TacticHole());
	}
	public String toString() {
		return "TacticOrE(" + b1 + "," + b2 + "," + t1 + "," + t2 + "," + t3 + ")";
	}
```
To prove A, 
let us introduce a new hypothesis B, prove A with the hypothesis B
let us introduce a new hypothesis C, prove A with the hypothesis C
and prove B || C
```
	public boolean prove(Sequent s) {
		Sequent s2 = s.copy(s.goal);
		s2.gamma.add(b1);
		Sequent s3 = s.copy(s.goal);
		s3.gamma.add(b2);
		return t1.prove(s.copy(new Or(b1,b2)))
				&& t2.prove(s2)
				&& t3.prove(s3);
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticOrI1` (disjunction introduction) 
```
package TP2;

import TP2.Bool.*;

public class TacticOrI1 extends Tactic {
	Tactic t;
	public TacticOrI1(Tactic t) {
		this.t = t;
	}
	public TacticOrI1() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticOrI1(" + t + ")";
	}
```
To prove A || B, let us prove A. 
```
	public boolean prove(Sequent s) {
		if (s.goal instanceof Or) {
			Or e = (Or)(s.goal);
			return t.prove(s.copy(e.b1));
		} else {
			System.out.println("TacticOrI1.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `TacticOrI2` (disjunction introduction) 
```
package TP2;

import TP2.Bool.*;

public class TacticOrI2 extends Tactic {
	Tactic t;
	public TacticOrI2(Tactic t) {
		this.t = t;
	}
	public TacticOrI2() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticOrI2(" + t + ")";
	}
```
To prove A || B, let us prove B. 
```
	public boolean prove(Sequent s) {
		if (s.goal instanceof Or) {
			Or e = (Or)(s.goal);
			return t.prove(s.copy(e.b2));
		} else {
			System.out.println("TacticOrI2.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}
```
```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `Bool` 
```
package TP2;

import TP2.Bool.*;

```
In the TP1 we have evaluated expressions with an interpreter. 

In the TP2 we do not evaluate expressions, 
but we present rules to build a proof tree.
If a full proof tree is constructed, 
then the expressions is always true.

Source, see pages 45-46 in 
https://www.lix.polytechnique.fr/Labo/Samuel.Mimram/teaching/INF551/course.pdf

For the first example, please browse the classes in the following order: 
- `Tactic`
- `TacTicHole`
- `TacticImplyI`
- `TacticOrI1`
- `TacticAndE1`
- `TacticAxiom`
```

public class MainTP2 {
	public static void main(String[] args) {
		Bool A = new Var("A");
		Bool B = new Var("B");
		Bool C = new Var("C");
		Bool F = new False();

```
1) p47: (A && B) => (A || B)
```
		Bool b = new Imply
				( new And(A,B)
				, new Or (A,B))
				;
		Tactic t;
		t = new TacticHole();
		t = new TacticImplyI();
		t = new TacticImplyI(new TacticOrI1());
		t = new TacticImplyI(new TacticOrI1(new TacticAndE1(new TacticHole(),B)));
		t = new TacticImplyI(new TacticOrI1(new TacticAndE1(new TacticAxiom(),B)));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

```
2) p48: (A || B) => (B || A)
```
		b = new Imply
				(new Or(A,B)
				,new Or(B,A));
		t = new TacticImplyI(
				new TacticOrE
					(A
					,B
					,new TacticAxiom()
					,new TacticOrI2(new TacticAxiom()) 
					,new TacticOrI1(new TacticAxiom())
					));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
```
3) p48: A => ! (! A)
```
		
		
```
4) p48: (A => B) => (!B => !A)
```
		
		
```
5) p48: (!A || B) => (A => B)
```
		

```
6) p48: (&& monoid) 
(A && B) && C <=> A && (B && C) (assoc)

6.1) (A && B) && C => A && (B && C) (assoc)
```
		

```
6.2) A && (B && C) => (A && B) && C (assoc)
```
		
		
```
7) A && A <=> A (idempotent)

7.1) A && A => A 
```
		

```
7.2) A => A && A
```
		
		
```
8) True && A <=> A (identity)

8.1) True && A => A (identity)
```
		

```
8.2) A => True && A (identity)
```
		

```
9) A && B <=> B && A (commut)

9.1) A && B => B && A 
```
		

```
9.2) B && A => A && B
```
		

```
10)  A || A <=> A (idempotent)

10.1)  A || A => A (idempotent)
```
		

```
10.2)  A => A || A (idempotent)
```
		
				
```
11) False || A <=> A (identity)

11.1) False || A => A (identity)
```
		
				
```
11.2) A => False || A (identity)
```
		
		
```
12) A || B <=> B || A (commut) 
```
		

```
13) reflexivity : A => A 
```
		
		
```
14) transitivity : (A => B) => ((B => C) => (A => C))
```
		
			
```
15) curryfication : ((A && B) => C) <=> (A => (B => C))

15.1) ((A && B) => C) => (A => (B => C))
```
		

```
15.2) (A => (B => C)) => ((A && B) => C) 
```
		
		
```
16) p49: modus tollens
(A => B) => (!B => !A)
```
		
		
		

		
		
	}
}



```

