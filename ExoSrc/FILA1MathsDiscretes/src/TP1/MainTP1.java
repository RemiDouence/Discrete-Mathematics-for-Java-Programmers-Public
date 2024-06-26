```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

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
		System.out.println(Bool.ForAll(x,Bool.Or(x, Bool.Not(x))).eval() == true);

		System.out.println("Test of Exist: exist x. x => not x");
		System.out.println(Bool.Exist(x,Bool.Imply(x, Bool.Not(x))).eval() == true);
		
		System.out.println("Test of false neutral for \\/");
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.Or(Bool.F,x),Bool.Or(x,Bool.F))).eval());
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.Or(Bool.F,x),x)).eval());

		System.out.println("Test of true absorbing for \\/");
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.Or(Bool.T,x),Bool.Or(x,Bool.T))).eval());
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.Or(Bool.T,x),Bool.T)).eval());

		System.out.println("Test of idempotency for \\/");
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.Or(x,x),x)).eval());
		
		System.out.println("Test of commutativity for \\/");
		Var y = new Var("y");
		System.out.println(Bool.ForAll(x,Bool.ForAll(y,
				Bool.Equiv
					(Bool.Or(x,y)
					,Bool.Or(y,x)
				))).eval());
				
		System.out.println("Test of associativity for \\/");
		Var z = new Var("z");
		System.out.println(Bool.ForAll(x,Bool.ForAll(y,Bool.ForAll(z,
				Bool.Equiv
					(Bool.Or(Bool.Or(x,y),z)
					,Bool.Or(x,Bool.Or(y,z))
				)))).eval());
				
		System.out.println("Test of distributivity for \\/");
		System.out.println(Bool.ForAll(x,Bool.ForAll(y,Bool.ForAll(z,
				Bool.Equiv
					(Bool.Or(x,Bool.And(y,z))
					,Bool.And(Bool.Or(x,y),Bool.Or(x,z))
				)))).eval());
				
		System.out.println("Test of true neutral for /\\");
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.And(Bool.T,x),Bool.And(x,Bool.T))).eval());
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.And(Bool.T,x),x)).eval());

		System.out.println("Test of false absorbing for /\\");
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.And(Bool.F,x),Bool.And(x,Bool.F))).eval());
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.And(Bool.F,x),Bool.F)).eval());

		System.out.println("Test of idempotency for /\\");
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.And(x,x),x)).eval());
		
		System.out.println("Test of idempotency for not");
		System.out.println(Bool.ForAll(x,Bool.Equiv(Bool.Not(Bool.Not(x)),x)).eval());

		System.out.println("Test of commutativity for /\\");
		System.out.println(Bool.ForAll(x,Bool.ForAll(y,
				Bool.Equiv
					(Bool.And(x,y)
					,Bool.And(y,x)
				))).eval());

		System.out.println("Test of associativity for /\\");
		System.out.println(Bool.ForAll(x,Bool.ForAll(y,Bool.ForAll(z,
				Bool.Equiv
					(Bool.And(Bool.And(x,y),z)
					,Bool.And(x,Bool.And(y,z))
				)))).eval());

		System.out.println("Test of distributivity for /\\");
		System.out.println(Bool.ForAll(x,Bool.ForAll(y,Bool.ForAll(z,
				Bool.Equiv
					(Bool.And(x,Bool.Or(y,z))
					,Bool.Or(Bool.And(x,y),Bool.And(x,z))
				)))).eval());
				
		System.out.println("Test de morgan's laws for \\/");
		System.out.println(Bool.ForAll(x,Bool.ForAll(y,
				Bool.Equiv
					(Bool.Not(Bool.And(x,y))
					,Bool.Or(Bool.Not(x),Bool.Not(y))
				))).eval());

		System.out.println("Test de morgan's laws for /\\");
		System.out.println(Bool.ForAll(x,Bool.ForAll(y,
				Bool.Equiv
					(Bool.Not(Bool.Or(x,y))
					,Bool.And(Bool.Not(x),Bool.Not(y))
				))).eval());
		
		System.out.println("Test exist x_1. ... exist x_n. x_1");
		for (int n=1; n<30; n++) {
			Bool b = new Var("x_1");
			for (int i=n; i>0; i--) {
				b = Bool.Exist(new Var("x_"+i), b);
			}
			System.out.println("test for n=" + n);
			System.out.println(b.eval());
		}

	}
}	

```
