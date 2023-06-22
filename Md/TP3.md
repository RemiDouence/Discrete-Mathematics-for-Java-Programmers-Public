# `Exp`
```
package TP3;

import java.util.HashMap;
import java.util.LinkedList;

```
`Exp` represents integer expressions  
```
public abstract class Exp {
	public int hashCode() {
		return 0;
	}
```
`unifiable` decide if a pattern (an expression with variables) has the same shape than another expression. 
```
	public abstract boolean unifiable(Exp other);
```
`unify` returns the values of the variables so that a pattern match another expression. 
```
	public abstract HashMap<String,Exp> unify(Exp other);
```
`substitue` replace in a pattern the variables by their values
```
	public abstract Exp substitute(HashMap<String,Exp> ss);
```
`apply` rewrites an expression E with the rule P1 => P2
by unifying P1 with E and returning P2 where variables have been substituted 
```
	public Exp apply(Rule eq) {
		if (eq.lhs.unifiable(this)) {
			return eq.rhs.substitute(eq.lhs.unify(this));			
		} else {
			return this;
		}
	}
```
`apply` rewrites an expression by applying the first rule of the list that matches 
```
	public Exp apply(LinkedList<Rule> eqs) {
		for (Rule eq: eqs) {
			if (!equals(apply(eq))) {
				return apply(eq);
			}
		}
		return this;
	}
```
`fixPoint` rewrites an expression with a list of rules until the expression does not change
```
	public Exp fixPoint(LinkedList<Rule> eqs) {
		Exp result = this;
		System.out.println(result); 
		while (! result.equals(result.apply(eqs))) {
			result = result.apply(eqs);
			System.out.println(result);
		}
		return result;
	}
}
```
# `Z` (zero) 
`Z` represents the natural zero. 
```
package TP3;

import java.util.HashMap;

public class Z extends Exp {
	public String toString() {
		return "Z";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Z;
	}
	public HashMap<String, Exp> unify(Exp other) {
		return new HashMap<String,Exp>();
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return this;
	}
	public boolean equals(Object o) {
		return o != null && o instanceof Z;
	}
}
```
# `S` (successor) 
`S n` represents the natural `n+1`. 
```
package TP3;

import java.util.HashMap;

public class S extends Exp {
	Exp p;
	public S(Exp p) {
		this.p = p;
	}
	public String toString() {
		return "S(" + p + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof S && p.unifiable(((S)other).p);
	}
	public HashMap<String, Exp> unify(Exp other) {
		return p.unify(((S)other).p);
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new S(p.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new S(p.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null && o instanceof S && p.equals(((S)o).p);
	}
}
```
# `Add` (addition) 
`Add e1 e2` represents the sum `e1 + e2`. 
`Add` enables us to define rules for the addition. 
```
package TP3;

import java.util.HashMap;

public class Add extends Exp {
	Exp e1, e2;
	public Add(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public String toString() {
		return "Add(" + e1 + "," + e2 + ")";
	}
```
We assume a variable has a single occurrence in a pattern.
We assume the same variable does not occurs in `e1` and `e2` at the same time. 
We assume patterns are linear. 
So there is no need to check the substitution for `e1` are compatible with the substitution for `e2`. 
```
	public boolean unifiable(Exp other) {
		return other instanceof Add 
				&& e1.unifiable(((Add)other).e1) 
				&& e2.unifiable(((Add)other).e2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = e1.unify(((Add)other).e1);
		result.putAll(e2.unify(((Add)other).e2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Add(e1.substitute(ss),e2.substitute(ss));
	}
```
If we cannot rewrite an `Add` expression, we try to rewrite its subexpressions. 
```
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Add(e1.apply(eq),e2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Add 
				&& e1.equals(((Add)o).e1) 
				&& e2.equals(((Add)o).e2);
	}
}
```
# `Var` (variable) 
`Var` represents a variable in an expression, i.e., in a pattern. 
```
package TP3;

import java.util.HashMap;

public class Var extends Exp {
	String s;
	public Var(String s) {
		this.s = s;
	}
	public String toString() {
		return s;
	}
	public boolean unifiable(Exp other) {
		return true;
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = new HashMap<String,Exp>();
		result.put(s, other);
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		if (ss.containsKey(s)) {
			return ss.get(s);			
		} else {
			return this;
		}
	}
	public boolean equals(Object o) {
		return o != null && o instanceof Var && s.equals(((Var)o).s);
	}
}
```
# `Rule` (a rewriting rule) 
`Rule` a rule is a pair of patterns 
```
package TP3;

import java.util.HashMap;

public class Rule {
	public Exp lhs, rhs;
	public Rule(Exp lhs, Exp rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public String toString() {
		return lhs + " -> " + rhs;
	}
	public Rule substitute(HashMap<String,Exp> ss) {
		return new Rule(lhs.substitute(ss),rhs.substitute(ss));
	}
}
```
# `Equation` (equation) 
`Equation e1 e2` represents the equality `e1 = e2`. 
```
package TP3;

import java.util.HashMap;

public class Equation {
	public Exp lhs, rhs;
	public Equation(Exp lhs, Exp rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public String toString() {
		return lhs + " == " + rhs;
	}
	public Equation substitute(HashMap<String,Exp> ss) {
		return new Equation(lhs.substitute(ss),rhs.substitute(ss));
	}
	public Equation apply(Rule r) {
		lhs = lhs.apply(r);
		rhs = rhs.apply(r);
		return this;
	}
	public boolean isTrivial() {
		return lhs.equals(rhs);
	}
	public void print() {
		System.out.println(this);
	}
}
```
# `Proof` (proof by induction) 
`Proof(e,x)` represents a proof of an equation `e` by induction on `x`. 
- `caseZ` is the equation where `x` is instantiated with `Z`. 
- `caseS` is the equation where `x` is instantiated with `S i`. 
- `hyp` is the equation where `x` is instantiated with `i`.
```
package TP3;

import java.util.HashMap;

public class Proof {
	public Equation caseZ;
	public Equation caseS;
	public Rule hyp;
	public Proof(Equation e,String x) {
		HashMap<String,Exp> sZ = new HashMap<String,Exp>();
		sZ.put(x, new Z());
		this.caseZ = e.substitute(sZ);
		HashMap<String,Exp> sS = new HashMap<String,Exp>();
		sS.put(x, new S(new Var("i")));
		this.caseS = e.substitute(sS);
		HashMap<String,Exp> sH = new HashMap<String,Exp>();
		sH.put(x, new Var("i"));
		this.hyp = new Rule(e.lhs.substitute(sH),e.rhs.substitute(sH));
	}
	public String toString() {
		return "" + hyp + "\n" + caseZ + "\n" + caseS;
	}
```
`cqfd` checks that the rewritten equations are trivial. 
Hence, a proof is over. 
```
	public boolean cqfd() {
		return caseZ.isTrivial() && caseS.isTrivial();
	}
}
```
# `Add1` (addition) 
`Add1 e1 e2` represents the sum `e1 + e2`. 
`Add1` enables us to define alternative rules for the addition. 
```
package TP3;

import java.util.HashMap;

public class Add2 extends Exp {
	Exp e1, e2;
	public Add2(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public String toString() {
		return "Add2(" + e1 + "," + e2 + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Add2 
				&& e1.unifiable(((Add2)other).e1) 
				&& e2.unifiable(((Add2)other).e2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = e1.unify(((Add2)other).e1);
		result.putAll(e2.unify(((Add2)other).e2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Add2(e1.substitute(ss),e2.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Add2(e1.apply(eq),e2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Add2 
				&& e1.equals(((Add2)o).e1) 
				&& e2.equals(((Add2)o).e2);
	}
}
```
# `Add1` (addition) 
`Add1 e1 e2` represents the sum `e1 + e2`. 
`Add1` enables us to define alternative rules for the addition. 
```
package TP3;

import java.util.HashMap;

public class Add3 extends Exp {
	Exp e1, e2;
	public Add3(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public String toString() {
		return "Add3(" + e1 + "," + e2 + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Add3 
				&& e1.unifiable(((Add3)other).e1) 
				&& e2.unifiable(((Add3)other).e2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = e1.unify(((Add3)other).e1);
		result.putAll(e2.unify(((Add3)other).e2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Add3(e1.substitute(ss),e2.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Add3(e1.apply(eq),e2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Add3 
				&& e1.equals(((Add3)o).e1) 
				&& e2.equals(((Add3)o).e2);
	}
}
```
# `Add1` (addition) 
`Add1 e1 e2` represents the sum `e1 + e2`. 
`Add1` enables us to define alternative rules for the addition. 
```
package TP3;

import java.util.HashMap;

public class Add4 extends Exp {
	Exp e1, e2;
	public Add4(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public String toString() {
		return "Add4(" + e1 + "," + e2 + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Add4 
				&& e1.unifiable(((Add4)other).e1) 
				&& e2.unifiable(((Add4)other).e2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = e1.unify(((Add4)other).e1);
		result.putAll(e2.unify(((Add4)other).e2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Add4(e1.substitute(ss),e2.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Add4(e1.apply(eq),e2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Add4 
				&& e1.equals(((Add4)o).e1) 
				&& e2.equals(((Add4)o).e2);
	}
}
```
# `Add1` (addition) 
`Add1 e1 e2` represents the sum `e1 + e2`. 
`Add1` enables us to define alternative rules for the addition. 
```
package TP3;

import java.util.HashMap;

public class Add5 extends Exp {
	Exp e1, e2;
	public Add5(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public String toString() {
		return "Add5(" + e1 + "," + e2 + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Add5 
				&& e1.unifiable(((Add5)other).e1) 
				&& e2.unifiable(((Add5)other).e2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = e1.unify(((Add5)other).e1);
		result.putAll(e2.unify(((Add5)other).e2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Add5(e1.substitute(ss),e2.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Add5(e1.apply(eq),e2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Add5 
				&& e1.equals(((Add5)o).e1) 
				&& e2.equals(((Add5)o).e2);
	}
}
```
# `Mul` (multiplication) 
`Mul e1 e2` represents the product `e1 * e2`. 
`Mul` enables us to define rules for the product. 
```
package TP3;

import java.util.HashMap;

public class Mul extends Exp {
	Exp p1, p2;
	public Mul(Exp e1, Exp e2) {
		this.p1 = e1;
		this.p2 = e2;
	}
	public String toString() {
		return "Mul(" + p1 + "," + p2 + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Mul 
				&& p1.unifiable(((Mul)other).p1) 
				&& p2.unifiable(((Mul)other).p2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = p1.unify(((Mul)other).p1);
		result.putAll(p2.unify(((Mul)other).p2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Mul(p1.substitute(ss),p2.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Mul(p1.apply(eq),p2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Mul 
				&& p1.equals(((Mul)o).p1) 
				&& p2.equals(((Mul)o).p2);
	}
}
```
# `MainTP3` (proof by induction) 
TP3 introduces expression rewriting (expression, pattern, unification, substitution, rules).
Rewriting is exemplified with natural numbers and addition. 
This can be viewed as the base for functional languages. 

Then TP3 introduces proof by induction (equation). 
With restricted renamming (via substitution) 
we pose an hypothesis and rewrite equations until they are trivial. 
```
package TP3;

import java.util.LinkedList;

public class MainTP3 {
		
	public static void main(String[] args) {

```
Natural number grammar

`N ::= Zero | Suc N | Add N N` 
```

```
Pattern grammar

`P ::= Zero | Suc P | Add P P | Var` 
```

```
Rule grammar

`R ::= P -> P` 

Examples of `Add`
- `add Z     y -> y`			[ADDZ]  
- `add (S x) y -> S (add x y)`	[ADDS]

Sequence of rewrites for 2 + 1:
- `add (S (S Z)) (S Z)	->` [ADDS] 
- `S (add (S Z) (S Z))  ->` [ADDS]
- `S (S (add Z (S Z)))  ->` [ADDZ]
- `S (S (S Z))`
```

```
Questions : 
- is the order of rules important? 
- is the place (redex) of the rewrites important?
```
		
```
## PART 1: REWRITING
```
		
```
Definition of add with rewriting rules 
- `add(Z,y) -> y` 
- `add(S(x),y) -> S(add(x,y))`
```
		
```
In Java we would program:
- `Exp Z.add(Exp y) { return y; }`
- `Exp S.add(Exp y) { return new S(this.p.add(y)); }`
```
		Exp Z = new Z();
		Exp One = new S(Z);
		Exp Two = new S(One);
		Exp Three = new S(Two);
		Exp x = new Var("x");
		Exp y = new Var("y");
		Rule add1Z = new Rule(new Add(Z,y),y);
		Rule add1S = new Rule(new Add(new S(x),y),new S(new Add(x,y)));
		LinkedList<Rule> prog1 = new LinkedList<Rule>();
		prog1.add(add1Z);
		prog1.add(add1S);
		System.out.println("definition of add: " + prog1);
		Exp twoPlusOne = new Add(Two, One);
		System.out.println("expression to evaluate: " + twoPlusOne);
		System.out.println("Evaluation of add1");
		twoPlusOne.fixPoint(prog1);
		//System.exit(0);

```
## PART 2: PROOF
```

```
Proof `Z` neutral right for `add`
```
		Proof p11 = new Proof(new Equation(new Add(x,Z),x), "x");
		System.out.println("\nProof of p11 for add1: " + p11.hyp);
		System.out.println("Proof:");
		// complete before print() 
		p11.caseZ.apply(add1Z).print();
		p11.caseS.apply(add1S).apply(p11.hyp).print();
		System.out.println(p11.cqfd());
		//System.exit(0);

```
## TODO 
### Proof `add` associative 
```
		Exp z = new Var("z");
		Proof p12 = new Proof(new Equation(new Add(new Add(x,y),z),new Add(x,new Add(y,z))), "x");
		System.out.println("\nProof of p12 for add1: " + p12.hyp);
		System.out.println("Proof:");
		p12.caseZ.print(); 
		
		p12.caseS.print(); 
		
		System.out.println(p12.cqfd());
		//System.exit(0);

```
### Proof `S` jumps args in `add`
```
		Proof p13 = new Proof(new Equation(new Add(x,new S(y)),new Add(new S(x),y)), "x");
		System.out.println("\nProof of p13 for add1: " + p13.hyp);
		System.out.println("Proof:");
		p13.caseZ.print();
		
		p13.caseS.print();

		System.out.println(p13.cqfd());
		//System.exit(0);
		
```
### Proof `add` commutative
```
		Proof p14 = new Proof(new Equation(new Add(x,y),new Add(y,x)), "x");
		System.out.println("\nProof of p14 for add1: " + p14.hyp);
		System.out.println("Proof:");
		p14.caseZ.print();
		
		p14.caseS.print();

		System.out.println(p14.cqfd());
		//System.exit(0);
		
		
```
### Another definition of addition 
- `add2(x,Z) -> x` 
- `add2(x,S(y)) -> S(add2(x,y))`
```
		Rule add2Z = null;
		
		Rule add2S = null; 
		
		LinkedList<Rule> prog2 = new LinkedList<Rule>();
		prog2.add(add2Z);
		prog2.add(add2S);
		System.out.println();
		System.out.println("definition of add: " + prog2);
		twoPlusOne = new Add2(Two, One);
		System.out.println("expression to evaluate: " + twoPlusOne);
		twoPlusOne.fixPoint(prog2);
		//System.exit(0);

```
### Proof `Z` neutral left for `add2`
```
		Proof p21 = new Proof(new Equation(new Add2(Z,y),y), "y");
		System.out.println("\nProof of p21 for add2: " + p21);
		System.out.println("Proof:");
		p21.caseZ.print();
		
		p21.caseS.print();

		System.out.println(p21.cqfd());
		//System.exit(0);

```
### Proof `add2` associative 
```
		Proof p22 = new Proof(new Equation(new Add2(new Add2(x,y),z),new Add2(x,new Add2(y,z))), "z");
		System.out.println("\nProof of p22 for add2: " + p22);
		System.out.println("Proof:");
		p22.caseZ.print();
		
		p22.caseS.print();

		System.out.println(p22.cqfd());
		//System.exit(0);

```
### Proof `S` jumps args in `add2`
```
		Proof p23 = new Proof(new Equation(new Add2(x,new S(y)),new Add2(new S(x),y)), "y");
		System.out.println("\nProof of p23 for add2: " + p23);
		System.out.println("Proof:");
		p23.caseZ.print();
		
		p23.caseS.print();

		System.out.println(p23.cqfd());
		//System.exit(0);
		
```
### Proof `add2` commutative
```
		Proof p24 = new Proof(new Equation(new Add2(x,y),new Add2(y,x)), "y");
		System.out.println("\nProof of p24 for add2: " + p24);
		System.out.println("Proof:");
		p24.caseZ.print();
		
		p24.caseS.print();

		System.out.println(p24.cqfd());
		//System.exit(0);
				
```
### Yet another definition of addition
define 2 rules equivalent to: `while (x != 0) { x--; y++; } return y;`
```
		

		Rule add3Z = null;
		
		Rule add3S = null; 

		LinkedList<Rule> prog3 = new LinkedList<Rule>();
		prog3.add(add3Z);
		prog3.add(add3S);
		System.out.println("definition of add: " + prog3);
		twoPlusOne = new Add3(new S(new S(Z)), new S(Z));
		System.out.println("expression to evaluate: " + twoPlusOne);
		System.out.println("Evaluation");
		twoPlusOne.fixPoint(prog3);
		//System.exit(0);
		
```
### Proof `S` jumps from second arg of `add3`
```
		Proof p33 = new Proof(new Equation(new Add3(x,new S(y)),new S(new Add3(x,y))), "x");
		System.out.println("\nProof of p33 for add3: " + p33.hyp);
		System.out.println("Proof:");
		p33.caseZ.print(); 
		
		p33.caseS.print();
		
		System.out.println(p33.cqfd());
		//System.exit(0);

```
### Proof `Z` neutral right for `add3`
```
		Proof p31 = new Proof(new Equation(new Add3(x,Z),x), "x");
		System.out.println("\nProof of p31 for add3: " + p31.hyp);
		System.out.println("Proof:");
		p31.caseZ.print();
		
		p31.caseS.print();
		
		System.out.println(p31.cqfd());
		//System.exit(0);

```
### Proof `add3` associative 
```
		Proof p32 = new Proof(new Equation(new Add3(new Add3(x,y),z),new Add3(x,new Add3(y,z))), "x");
		System.out.println("\nProof of p32 for add3: " + p32.hyp);
		System.out.println("Proof:");
		p32.caseZ.print();
		
		p32.caseS.print();
		
		System.out.println(p32.cqfd());
		//System.exit(0);

```
### Proof `add3` commutative
```
		Proof p34 = new Proof(new Equation(new Add3(x,y),new Add3(y,x)), "x");
		System.out.println("\nProof of p34 for add3: " + p34.hyp);
		System.out.println("Proof:");
		p34.caseZ.print();
		
		p34.caseS.print();

		System.out.println(p34.cqfd());
		//System.exit(0);

```
### Proof of equivalence of `add` and `add3`
```
		Proof p35 = new Proof(new Equation(new Add(x,y),new Add3(x,y)),"x");
		System.out.println("\nProof of p35 for add/add3: " + p35.hyp);
		System.out.println("Proof:");
		p35.caseZ.print();
		
		p35.caseS.print();

		System.out.println(p35.cqfd());
		//System.exit(0);

```
### Yet another definition of addition
- `add4(Z,y) -> y`
```
		Rule add4Z = null;

```
- `add4(S(x),y) -> S(add4(y,x))`
```
		Rule add4S = null; 
		
		LinkedList<Rule> prog4 = new LinkedList<Rule>();
		prog4.add(add4Z);
		prog4.add(add4S);
		System.out.println("definition of add: " + prog4);
		twoPlusOne = new Add4(new S(new S(Z)), new S(Z));
		System.out.println("expression to evaluate: " + twoPlusOne);
		twoPlusOne.fixPoint(prog4);
		//System.exit(0);
		
```
### Proof `Z` neutral right for `add4`
```
		Proof p41 = new Proof(new Equation(new Add4(x,Z),x), "x");
		System.out.println("\nProof of p41 for add4: " + p41.hyp);
		System.out.println("Proof:");
		p41.caseZ.print();
		
		p41.caseS.print();

		System.out.println(p41.cqfd());
		//System.exit(0);

		
		
```
### Another version of addition (not well founded)
- `add5(Z,y) -> y`
```
		Rule add5Z = null; 

```
- `add5(S(x),y) -> add5(S(y),x)`
```
		Rule add5S = null;
		
		LinkedList<Rule> prog5 = new LinkedList<Rule>();
		prog5.add(add5Z);
		prog5.add(add5S);
		System.out.println("definition of add: " + prog5);
		twoPlusOne = new Add5(new S(new S(Z)), new S(Z));
		System.out.println("expression to evaluate: " + twoPlusOne);
		System.out.println(twoPlusOne.fixPoint(prog5));
		// what is the problem with these rules set?
		//System.exit(0);
		
```
### Definition of `mul`
```
		Rule mul1Z = null; Rule mul1S = null; 
		
		LinkedList<Rule> prog7 = new LinkedList<Rule>();
		prog7.add(add1Z);
		prog7.add(add1S);
		prog7.add(mul1Z);
		prog7.add(mul1S);
		System.out.println();
		System.out.println("definition of add and mul: " + prog7);
		Exp twoTimesThree = new Mul(Two,Three);
		System.out.println("expression to evaluate: " + twoTimesThree);
		System.out.println("Evaluation");
		twoTimesThree.fixPoint(prog7);
		//System.exit(0);
		
```
### Proof `mul` associative
```
		
		
		p62.caseS.print();

		System.out.println(p62.cqfd());
		//System.exit(0);
		
	}
}
```

