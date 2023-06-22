```

// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

```
# `Bool`
The class `Bool` implements static methods for boolean operators. 
The operators are "macros" based on Nand only.
```
package TP1;

abstract public class Bool {
```
The method `eval` is abstract and defined in the subclasses of `Bool`. 
Its argument defines the values of the variables in the boolean expression. 
```
	abstract public boolean eval(Env<Boolean> env);
```
By default, the expression is closed (no free variable) and the top level environment is empty.  
```
	public boolean eval() {
		return eval(new Env<Boolean>());
	}
```
We define the constant once for all. 
```
	public final static Bool T = new True();
	// not b = Nand b b 
	public static Bool Not(Bool b1) {
		return null;
		
	}
	// false = Not true
	public static Bool False() {
		return null;
		
	}
```
We define the constant once for all. 
```
	public static Bool F = Bool.False();

	// b1 /\ b2 = ?

	public static Bool And(Bool b1, Bool b2) {
		return null;

	}

	// b1 \/ b2 = ?

	public static Bool Or(Bool b1, Bool b2) {
		return null;

	}

	// b1 => b2 = ? 

	public static Bool Imply(Bool b1, Bool b2) {
		return null;

	}

	// b1 <=> b2 = ? 

	public static Bool Equiv(Bool b1, Bool b2) {
		return null;
		
	}

```
Universal quantification `forAll x.b` is true when `b` is true whatever the value of `x`
```
	// forAll x.b = ?

	public static Bool ForAll(Var x,Bool b) {
		return null;
		
	}

```
Existential quantification `exist x.b` is true when `b` is true for at least one value of `x`
```
	// exist x.b = ?

	public static Bool Exist(Var x,Bool b) {
		return null;
		
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
# The True constant
```
package TP1;

public class True extends Bool {
```
Printer compliant with Java syntax. 
```
	public String toString() {
		return "true";
	}
```
Trivial evaluation by returning the `true` Java constant
```
	public boolean eval(Env<Boolean> env) {
		return true;
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
# Not and (aka Nand)
Nand gates are used to define processors. 
In this TP we use them to define other boolean operators. 
```
package TP1;

public class NAnd extends Bool {
```
Boolean expression are immutable (hence the `final` field modifier). 
```
	final public Bool b1, b2;
	public NAnd(Bool b1, Bool b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
```
Fully bracketed infix printer.  
```
	public String toString() {
		return "(" + b1 + " nand " + b2 + ")";
	}
```
This is the only place we use Java boolean operator. 
```
	public boolean eval(Env<Boolean> env) {
		return !(b1.eval(env) && b2.eval(env));
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
# Environment 
An environment is a set of pairs. 
Each pair binds a variable identifier with its value. 
```
package TP1;

import java.util.HashMap;

```
The implementation is based on hash maps. 
The type of values is generic. 
```
public class Env<A> extends HashMap<String,A> {
	public Env() {
		super();
	}
	public Env(Env<A> that) {
		super(that);
	}
	public A get(String k) {
		return super.get(k);
	}
	// MD Environments are immutable: `set` returns a new environment. 
	public Env<A> set(String k,A v) {
```
Beware of Java shallow copy in constructors. 
Here the values are shared but it is fine. 
(And String are immutable in Java). 
```
		Env<A> result = new Env<A>(this);
		result.put(k, v);
		return result;
	}
	private static final long serialVersionUID = 1L;
```
Testing code 
```
	public static void main(String[] args) {
		Env<Boolean> e = new Env<Boolean>();
		System.out.println(e);
		Env<Boolean> e1 = e.set("x",true);
		System.out.println(e1);
		Env<Boolean> e2 = e.set("x",false).set("y", true);
		System.out.println(e2);
		System.out.println(e1);
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
# Variable (aka constant)
A variable is a named constant. 
```
package TP1;

public class Var extends Bool {
```
The name of the constant. 
```
	final public String s;
	public Var(String s) {
		this.s = s;
	}
	public String toString() {
		return s;
	}
```
The interpreter looks up the value of the constant 
in the environment (received as a parameter).
We assume the constant is in the environment,
(no check, no error management).
Nothing prevents you from filling up your car tank with water. 
But you should not.
```
	public boolean eval(Env<Boolean> env) {
		return env.get(s);
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
# Let binder
Let define a new constant in an expression. 
```
package TP1;

public class Let extends Bool {
```
The name of the constant. 
```
	final public Var v;
```
The value of the constant. 
Exercise: in general `b1` could be a `Bool`expression. 
Modify the program accordingly. 
```
	final public boolean b1;
```
The expression here the constant can be referenced. 
```
	final public Bool b2;
	public Let(Var v, boolean b1, Bool b2 ) {
		this.v = v;
		this.b1 = b1;
		this.b2 = b2;
	}
```
The printer use a syntax close to Haskell/Caml
```
	public String toString() {
		return "(let " + v + " = " + b1 + " in " + b2 +")"; 
	}
```
The interpreter adds a new binding to the environment 
and proceed to evaluate the expression in this enriched environment. 
```
	public boolean eval(Env<Boolean> env) {
		return b2.eval(env.set(v.s, b1));
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
# Main 
This project implements a simple interpreter for boolean expressions.
The primitive are:
- constant `true`
- the logical operator nand (not and) 
- constant binder `let`

1) study the code, then define in `Bool` other constant and logical operators with the primitives of the interpreter only 
(you are not allowed to use Java constant and operators: `false`, `!`, `&&`, `||`.

2) complete the tests below

```
package TP1;

public class MainTP1 {
	public static void main(String[] args) {
				
		System.out.println("Test of False");
		System.out.println(Bool.False().eval() == false);
		
		System.out.println("Test of Not");
		System.out.println(Bool.Not(Bool.T).eval() == false);
		System.out.println(Bool.Not(Bool.F).eval() == true);

		System.out.println("Test of /\\");
		System.out.println(Bool.And(Bool.T,Bool.T).eval() == true);
		System.out.println(Bool.And(Bool.T,Bool.F).eval() == false);
		System.out.println(Bool.And(Bool.F,Bool.T).eval() == false);
		System.out.println(Bool.And(Bool.F,Bool.F).eval() == false);

		System.out.println("Test of \\/");
		System.out.println(Bool.Or(Bool.T,Bool.T).eval() == true);
		System.out.println(Bool.Or(Bool.T,Bool.F).eval() == true);
		System.out.println(Bool.Or(Bool.F,Bool.T).eval() == true);
		System.out.println(Bool.Or(Bool.F,Bool.F).eval() == false);

		System.out.println("Test of =>");
		System.out.println(Bool.Imply(Bool.T,Bool.T).eval() == true);
		System.out.println(Bool.Imply(Bool.T,Bool.F).eval() == false);
		System.out.println(Bool.Imply(Bool.F,Bool.T).eval() == true);
		System.out.println(Bool.Imply(Bool.F,Bool.F).eval() == true);

		System.out.println("Test of <=>");
		System.out.println(Bool.Equiv(Bool.T,Bool.T).eval() == true);
		System.out.println(Bool.Equiv(Bool.T,Bool.F).eval() == false);
		System.out.println(Bool.Equiv(Bool.F,Bool.T).eval() == false);
		System.out.println(Bool.Equiv(Bool.F,Bool.F).eval() == true);

		System.out.println("Test of Let");
		Var x = new Var("x");
		
		System.out.println(new Let(x,true,x).eval() == true);
		System.out.println(new Let(x,false,x).eval() == false);
		
		System.out.println("Test of ForAll (tiers exclus): forAll x. x \\/ not x");
		

		System.out.println("Test of Exist: exist x. x => not x");
		
		
		System.out.println("Test of false neutral for \\/");
		

		System.out.println("Test of true absorbing for \\/");
		

		System.out.println("Test of idempotency for \\/");
		
		
		System.out.println("Test of commutativity for \\/");
		
				
		System.out.println("Test of associativity for \\/");
		
				
		System.out.println("Test of distributivity for \\/");
		
				
		System.out.println("Test of true neutral for /\\");
		

		System.out.println("Test of false absorbing for /\\");
		

		System.out.println("Test of idempotency for /\\");
		
		
		System.out.println("Test of idempotency for not");
		

		System.out.println("Test of commutativity for /\\");
		

		System.out.println("Test of associativity for /\\");
		

		System.out.println("Test of distributivity for /\\");
		
				
		System.out.println("Test de morgan's laws for \\/");
		

		System.out.println("Test de morgan's laws for /\\");
		
		
		System.out.println("Test exist x_1. ... exist x_n. x_1");
		

	}
}	
```

