
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # Let binder
// Let define a new constant in an expression. 
package TP1;

public class Let extends Bool {
	// The name of the constant. 
	final public Var v;
	// The value of the constant. 
	// Exercise: in general `b1` could be a `Bool`expression. 
	// Modify the program accordingly. 
	final public boolean b1;
	// The expression here the constant can be referenced. 
	final public Bool b2;
	public Let(Var v, boolean b1, Bool b2 ) {
		this.v = v;
		this.b1 = b1;
		this.b2 = b2;
	}
	// The printer use a syntax close to Haskell/Caml
	public String toString() {
		return "(let " + v + " = " + b1 + " in " + b2 +")"; 
	}
	// The interpreter adds a new binding to the environment 
	// and proceed to evaluate the expression in this enriched environment. 
	public boolean eval(Env<Boolean> env) {
		return b2.eval(env.set(v.s, b1));
	}
}

