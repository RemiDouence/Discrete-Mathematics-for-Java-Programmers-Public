// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence

package TP1;



// in your code you can only use True and Nand (no && || !)
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


	// ! n = not b = ? 
	public static Bool Not(Bool b1) 
    { return null; } //TODO	


	// false = ? 
	public static Bool False() 
    { return null; } //TODO	
	

	// We define the constant once for all 
	public static Bool F 
    = null; //TODO	


	// conjunction is true when both are true 
	// b1 /\ b2 = b1 && b2 = ?
	public static Bool And(Bool b1, Bool b2) 
    { return null; } //TODO	


	// disjunction is true when at least one is true 
	// b1 \/ b2 = b1 || b2 = ?
	public static Bool Or(Bool b1, Bool b2) 
    { return null; } //TODO	


	// implication is true when b1 is false, true when b1 and b2 are true
	// b1 => b2 = ? 
	public static Bool Imply(Bool b1, Bool b2) 
    { return null; } //TODO	


	// equivalence is true when b1 equals b2
	// b1 <=> b2 = ?  
	public static Bool Equiv(Bool b1, Bool b2) 
    { return null; } //TODO	


	// Universal quantification `forAll x.b` is true when `b` is true whatever the value of `x`
	// forAll x.b = ?
	public static Bool ForAll(Var x,Bool b) 
    { return null; } //TODO	


	// Existential quantification `exist x.b` is true when `b` is true for at least one value of `x`
	// exist x.b = ? 
	public static Bool Exist(Var x,Bool b) 
    { return null; } //TODO	

}
