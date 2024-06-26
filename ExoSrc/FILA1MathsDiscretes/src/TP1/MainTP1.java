// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence

package TP1;

public class MainTP1 {
	public static void main(String[] args) {
				
		System.out.println("Test of False");
		System.out.println(Bool.False().eval() == false);
		
		System.out.println("Test of Not");
		System.out.println(Bool.Not(Bool.T).eval() == false);
		System.out.println(Bool.Not(Bool.F).eval() == true);

		System.out.println("Test of /\\");
        // test the four possible expressions

		System.out.println("Test of \\/");
        // test the four possible expressions

		System.out.println("Test of =>");
        // test the four possible expressions

		System.out.println("Test of <=>");
        // test the four possible expressions

		System.out.println("Test of Let");
		Var x = new Var("x");
		
		System.out.println(new Let(x,true,x).eval() == true);
		System.out.println(new Let(x,false,x).eval() == false);
		
		System.out.println("Test of ForAll (tiers exclus): forAll x. x \\/ not x");
        // test the previous expression is a tautology

		System.out.println("Test of Exist: exist x. x => not x");
        // test the previous expression is a tautology
		
		System.out.println("Test of false neutral for \\/");
        // test the previous expression is a tautology (2 cases)

		System.out.println("Test of true absorbing for \\/");
        // test the previous expression is a tautology (2 cases)

		System.out.println("Test of idempotency for \\/");
        // test the previous expression is a tautology (1 case)
		
		System.out.println("Test of commutativity for \\/");
		Var y = new Var("y");
        // test the previous expression is a tautology (1 case)
				
		System.out.println("Test of associativity for \\/");
		Var z = new Var("z");
        // test the previous expression is a tautology (1 case)
				
		System.out.println("Test of distributivity for \\/ over /\\");
        // test the previous expression is a tautology (1 case)
				
		System.out.println("Test of true neutral for /\\");
        // test the previous expression is a tautology (2 cases)

		System.out.println("Test of false absorbing for /\\");
        // test the previous expression is a tautology (2 cases)

		System.out.println("Test of idempotency for /\\");
        // test the previous expression is a tautology (1 case)
		
		System.out.println("Test of idempotency for not");
        // test the previous expression is a tautology (1 case)

		System.out.println("Test of commutativity for /\\");
        // test the previous expression is a tautology (1 case)

		System.out.println("Test of associativity for /\\");
        // test the previous expression is a tautology (1 case)

		System.out.println("Test of distributivity for /\\ over \\/");
        // test the previous expression is a tautology (1 case)
				
		System.out.println("Test de morgan's laws for \\/");
        // test the previous expression is a tautology (1 case)

		System.out.println("Test de morgan's laws for /\\");
        // test the previous expression is a tautology (1 case)
		
		System.out.println("Test exist x_1. ... exist x_n. x_1");
        // test the previous expression is a tautology (30 cases)

	}
}	

