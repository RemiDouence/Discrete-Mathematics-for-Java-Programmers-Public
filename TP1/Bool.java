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
		return null;
		
	}
	// false = Not true
	public static Bool False() {
		return null;
		
	}
	// We define the constant once for all. 
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

	// Universal quantification `forAll x.b` is true when `b` is true whatever the value of `x`
	// forAll x.b = ?

	public static Bool ForAll(Var x,Bool b) {
		return null;
		
	}

	// Existential quantification `exist x.b` is true when `b` is true for at least one value of `x`
	// exist x.b = ?

	public static Bool Exist(Var x,Bool b) {
		return null;
		
	}
}

