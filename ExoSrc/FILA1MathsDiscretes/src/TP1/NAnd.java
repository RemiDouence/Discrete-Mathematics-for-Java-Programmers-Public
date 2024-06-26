```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # Not and (aka Nand)
// Nand gates are used to define processors. 
// In this TP we use them to define other boolean operators. 
package TP1;

public class NAnd extends Bool {
	// Boolean expression are immutable (hence the `final` field modifier). 
	final public Bool b1, b2;
	public NAnd(Bool b1, Bool b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
	// Fully bracketed infix printer.  
	public String toString() {
		return "(" + b1 + " nand " + b2 + ")";
	}
	// This is the only place we use Java boolean operator. 
	public boolean eval(Env<Boolean> env) {
		return !(b1.eval(env) && b2.eval(env));
	}
}

```
