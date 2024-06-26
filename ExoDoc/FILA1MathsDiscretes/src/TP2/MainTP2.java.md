
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `Bool` 
package TP2;

import TP2.Bool.*;

// In the TP1 we have evaluated expressions with an interpreter. 
// 
// In the TP2 we do not evaluate expressions, 
// but we present rules to build a proof tree.
// If a full proof tree is constructed, 
// then the expressions is always true.
// 
// Source, see pages 45-46 in 
// https://www.lix.polytechnique.fr/Labo/Samuel.Mimram/teaching/INF551/course.pdf
// 
// For the first example, please browse the classes in the following order: 
// - `Tactic`
// - `TacTicHole`
// - `TacticImplyI`
// - `TacticOrI1`
// - `TacticAndE1`
// - `TacticAxiom`

public class MainTP2 {
	public static void main(String[] args) {
		Bool A = new Var("A");
		Bool B = new Var("B");
		Bool C = new Var("C");
		Bool F = new False();

		// 1) p47: (A && B) => (A || B)
		Bool b = new Imply
				( new And(A,B)
				, new Or (A,B))
				;
		Tactic t;
		t = new TacticHole();
		t = new TacticImplyI();
		t = new TacticImplyI(new TacticOrI1());
		t = new TacticImplyI(new TacticOrI1(new TacticAndE1(new TacticHole(),B)));
		t = new TacticImplyI(new TacticOrI1(new TacticAndE1(new TacticAxiom(),B)));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		// 2) p48: (A || B) => (B || A)
		b = new Imply
				(new Or(A,B)
				,new Or(B,A));
		t = new TacticImplyI(
				new TacticOrE
					(A
					,B
					,new TacticAxiom()
					,new TacticOrI2(new TacticAxiom()) 
					,new TacticOrI1(new TacticAxiom())
					));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
		// 3) p48: A => ! (! A)
		b = new Imply(A,new Not(new Not(A)));
		t = new TacticImplyI(new TacticNotI(new TacticNotE(A, new TacticAxiom(), new TacticAxiom())));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
		// 4) p48: (A => B) => (!B => !A)
		b = new Imply(new Imply(A,B),new Imply(new Not(B), new Not(A)));
		t = new TacticImplyI(
				new TacticImplyI(
					new TacticNotI(
						new TacticNotE
							(B
							,new TacticImplyE(A,new TacticAxiom(), new TacticAxiom())
							,new TacticAxiom()
							)
						)
					)
				);
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
		// 5) p48: (!A || B) => (A => B)
		b = new Imply(new Or(new Not(A),B),new Imply(A,B));
		t = new TacticImplyI(
				new TacticImplyI(
					new TacticOrE
							(new Not(A)
							,B
							,new TacticAxiom()
							,new TacticFalseE
								(new TacticNotE
										(A
										,new TacticAxiom()
										,new TacticAxiom()))
							,new TacticAxiom()
							)));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		// 6) p48: (&& monoid) 
		// (A && B) && C <=> A && (B && C) (assoc)
		// 
		// 6.1) (A && B) && C => A && (B && C) (assoc)
		b = new Imply(new And(new And(A,B),C),new And(A,new And(B,C)));
		t = new TacticImplyI
				(new TacticAndI
					(new TacticAndE1
						(new TacticAndE1(new TacticAxiom(),C)
						,B)
					, new TacticAndI
						(new TacticAndE2(A,new TacticAndE1(new TacticAxiom(),C))
						,new TacticAndE2(new And(A,B),new TacticAxiom())
						)
					)
				);
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		// 6.2) A && (B && C) => (A && B) && C (assoc)
		b = new Imply(new And(A,new And(B,C)),new And(new And(A,B),C));
		t = new TacticImplyI
				(new TacticAndI
					(new TacticAndI
						(new TacticAndE1(new TacticAxiom(),new And(B,C))
						,new TacticAndE1(new TacticAndE2(A, new TacticAxiom()),C))
					,new TacticAndE2(B,new TacticAndE2(A, new TacticAxiom()))));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
		// 7) A && A <=> A (idempotent)
		// 
		// 7.1) A && A => A 
		b = new Imply(new And(A,A),A);
		t = new TacticImplyI(new TacticAndE1(new TacticAxiom(),A));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		// 7.2) A => A && A
		b = new Imply(A,new And(A,A));
		t = new TacticImplyI(new TacticAndI(new TacticAxiom(),new TacticAxiom()));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
		// 8) True && A <=> A (identity)
		// 
		// 8.1) True && A => A (identity)
		b = new Imply(new And(new True(),A),A);
		t = new TacticImplyI(new TacticAndE2(new True(), new TacticAxiom()));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		// 8.2) A => True && A (identity)
		b = new Imply(A,new And(new True(),A));
		t = new TacticImplyI(new TacticAndI(new TacticTrueI(),new TacticAxiom()));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		// 9) A && B <=> B && A (commut)
		// 
		// 9.1) A && B => B && A 
		b = new Imply(new And(A,B),new And(B,A));
		t = new TacticImplyI
				(new TacticAndI
						(new TacticAndE2(A,new TacticAxiom())
						,new TacticAndE1(new TacticAxiom(),B)));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		// 9.2) B && A => A && B
		// see 9.1 above

		// 10)  A || A <=> A (idempotent)
		// 
		// 10.1)  A || A => A (idempotent)
		b = new Imply(new Or(A,A),A);
		t = new TacticImplyI(new TacticOrE(A,A,new TacticAxiom(),new TacticAxiom(),new TacticAxiom()));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		// 10.2)  A => A || A (idempotent)
		b = new Imply(A,new Or(A,A));
		t = new TacticImplyI(new TacticOrI1(new TacticAxiom()));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
				
		// 11) False || A <=> A (identity)
		// 
		// 11.1) False || A => A (identity)
		b = new Imply(new Or(F,A),A);
		t = new TacticImplyI
				(new TacticOrE
					(F
					,A
					,new TacticAxiom()
					,new TacticFalseE(new TacticAxiom())
					,new TacticAxiom()
					));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
				
		// 11.2) A => False || A (identity)
		b = new Imply(A,new Or(F,A));
		t = new TacticImplyI(new TacticOrI2(new TacticAxiom()));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
		// 12) A || B <=> B || A (commut) 
		// see 2 above

		// 13) reflexivity : A => A 
		b = new Imply(A,A);
		t = new TacticImplyI(new TacticAxiom());
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
		// 14) transitivity : (A => B) => ((B => C) => (A => C))
		b = new Imply(new Imply(A,B),new Imply(new Imply(B,C),new Imply(A,C)));
		t = new TacticImplyI
				(new TacticImplyI
					(new TacticImplyI
						(new TacticImplyE
								(B
								,new TacticAxiom()
							    ,new TacticImplyE
							    	(A
							    	,new TacticAxiom()
							    	,new TacticAxiom()))
								)));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
			
		// 15) curryfication : ((A && B) => C) <=> (A => (B => C))
		// 
		// 15.1) ((A && B) => C) => (A => (B => C))
		b = new Imply(new Imply(new And(A,B),C),new Imply(A,new Imply(B,C)));
		t = new TacticImplyI
				(new TacticImplyI
						(new TacticImplyI(
							new TacticImplyE
								(new And(A,B)
								,new TacticAxiom()
								,new TacticAndI(new TacticAxiom(),new TacticAxiom()))
								)));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		// 15.2) (A => (B => C)) => ((A && B) => C) 
		b = new Imply(new Imply(A,new Imply(B,C)),new Imply(new And(A,B),C));
		t = new TacticImplyI(
				new TacticImplyI(
					new TacticImplyE
					(B
					, new TacticImplyE
						(A
						, new TacticAxiom()
						, new TacticAndE1(new TacticAxiom(),B))
					, new TacticAndE2(A,new TacticAxiom()))));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
		// 16) p49: modus tollens
		// (A => B) => (!B => !A)
		// see 4 above 
		
		// 17 and 18 are not exported
		// 17) p49: modus tollendo ponens
		// (A || B) => (!A => B)
		b = new Imply(new Or(A,B),new Imply(new Not(A),B));
		t = new TacticImplyI(
				new TacticImplyI(
					new TacticOrE
						(B
						,A
						,new TacticOrI1
							(new TacticOrE
								(A
								,B
								,new TacticAxiom()
								,new TacticFalseE
									(new TacticNotE(A,new TacticAxiom(),new TacticAxiom()))
								,new TacticAxiom()
								)
								),new TacticAxiom()
							     ,new TacticFalseE
							     	(new TacticNotE(A,new TacticAxiom(),new TacticAxiom()))
							     )));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		// 18) p49: modus ponendo tonens
		// !(A && B) => (A => !B)
		b = new Imply(new Not(new And(A,B)),new Imply(A,new Not(B)));
		t = new TacticImplyI(
				new TacticImplyI
					(new TacticNotI
						(new TacticNotE
							(new And(A,B)
							,new TacticAndI(new TacticAxiom(),new TacticAxiom())
							,new TacticAxiom()
									))));
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

		/*
		// p48: (|| monoid)
		// (A || B) || C <=> A || (B || C) (assoc)
		// https://math.stackexchange.com/questions/2197480/symbolic-logic-proof-of-associativity
		// (A || B) || C  => A || (B || C) (assoc)
		b = new Imply(new Or(new Or(A,B),C),new Or(A,new Or(B,C)));
		t = new TacticHole();
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		//
		// A || (B || C) => (A || B) || C  (assoc)
		b = new Imply(new Or(A,new Or(B,C)),new Or(new Or(A,B),C));
		t = new TacticHole();
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		//
		System.exit(0);
		//
		// distributivity 
		// A && (B || C) <=> (A && B) || (A && C)
		// A && (B || C) => (A && B) || (A && C)
		b = new Imply(new And(A,new Or(B,C)),new Or(new And(A,B),new And(A,C)));
		t = new TacticHole();
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		// (A && B) || (A && C) => A && (B || C)		
		// distributivity2
		// B1 || (B2 && B3) <=> (B1 || B2) && (B1 || B3)
		//
		System.exit(0);
		*/
		
	}
}




