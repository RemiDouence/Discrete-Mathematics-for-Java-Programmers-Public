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
		
		
		// 4) p48: (A => B) => (!B => !A)
		
		
		// 5) p48: (!A || B) => (A => B)
		

		// 6) p48: (&& monoid) 
		// (A && B) && C <=> A && (B && C) (assoc)
		// 
		// 6.1) (A && B) && C => A && (B && C) (assoc)
		

		// 6.2) A && (B && C) => (A && B) && C (assoc)
		
		
		// 7) A && A <=> A (idempotent)
		// 
		// 7.1) A && A => A 
		

		// 7.2) A => A && A
		
		
		// 8) True && A <=> A (identity)
		// 
		// 8.1) True && A => A (identity)
		

		// 8.2) A => True && A (identity)
		

		// 9) A && B <=> B && A (commut)
		// 
		// 9.1) A && B => B && A 
		

		// 9.2) B && A => A && B
		

		// 10)  A || A <=> A (idempotent)
		// 
		// 10.1)  A || A => A (idempotent)
		

		// 10.2)  A => A || A (idempotent)
		
				
		// 11) False || A <=> A (identity)
		// 
		// 11.1) False || A => A (identity)
		
				
		// 11.2) A => False || A (identity)
		
		
		// 12) A || B <=> B || A (commut) 
		

		// 13) reflexivity : A => A 
		
		
		// 14) transitivity : (A => B) => ((B => C) => (A => C))
		
			
		// 15) curryfication : ((A && B) => C) <=> (A => (B => C))
		// 
		// 15.1) ((A && B) => C) => (A => (B => C))
		

		// 15.2) (A => (B => C)) => ((A && B) => C) 
		
		
		// 16) p49: modus tollens
		// (A => B) => (!B => !A)
		
		
		

		
		
	}
}




