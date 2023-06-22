// # Main 
// This project implements a simple interpreter for boolean expressions.
// The primitive are:
// - constant `true`
// - the logical operator nand (not and) 
// - constant binder `let`
// 
// 1) study the code, then define in `Bool` other constant and logical operators with the primitives of the interpreter only 
// (you are not allowed to use Java constant and operators: `false`, `!`, `&&`, `||`.
// 
// 2) complete the tests below
// 
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

