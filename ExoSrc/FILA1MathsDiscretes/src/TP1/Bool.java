```

// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `Bool`
// The class `Bool` implements static methods for boolean operators. 
// The operators are "macros" based on Nand only.
package TP1;

abstract public class Bool {
	// The method `eval` is abstract and defined in the subclasses of `Bool`. 
	// Its argument defines the values of the variables in the boolean expression. 
	abstract public boolean eval(Env<Boolean> env);
	// By default, the expression is closed (no free variable) and the top level environment is empty.  
	public boolean eval() {
		return eval(new Env<Boolean>());
	}
	// We define the constant once for all. 
	public final static Bool T = new True();
	// not b = Nand b b 
	public static Bool Not(Bool b1) {
		return new NAnd(b1, b1);
		
	}
	// false = Not true
	public static Bool False() {
		return Not(new True());
		
	}
	// We define the constant once for all. 
	public static Bool F = Bool.False();

	// b1 /\ b2 = Not (Nand b1 b2)

	public static Bool And(Bool b1, Bool b2) {
		return Not(new NAnd(b1, b2));

	}

	// b1 \/ b2 = Nand (Not b1) (Not b2)

	public static Bool Or(Bool b1, Bool b2) {
		return new NAnd(Not(b1), Not(b2));

	}

	// b1 => b2 = (Not b1) \/ b2 

	public static Bool Imply(Bool b1, Bool b2) {
		return Or(Not(b1),b2);

	}

	// b1 <=> b2 = (b1 => b2) /\ (b2 => b1)  

	public static Bool Equiv(Bool b1, Bool b2) {
		return And(Imply(b1,b2),Imply(b2,b1));
		
	}

	// Universal quantification `forAll x.b` is true when `b` is true whatever the value of `x`
	// forAll x.b = (let x=true in b) /\ (let x=false in b)

	public static Bool ForAll(Var x,Bool b) {
		return And(new Let(x,true,b),new Let(x,false,b));
		
	}

	// Existential quantification `exist x.b` is true when `b` is true for at least one value of `x`
	// exist x.b = (let x=true in b) \/ (let x=false in b) 

	public static Bool Exist(Var x,Bool b) {
		return Or(new Let(x,true,b),new Let(x,false,b));
		
	}
}

```
