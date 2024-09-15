// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
```
package TP2;

import TP2.Bool.*;

```
# Proof Tree
```
// In the TP1 we have evaluated expressions with an interpreter. 
// 
// In the TP2 we do not evaluate expressions, 
// but we transform them with rewriting rules.
// The composition of rules is represented by a proof tree.
// If a full proof tree is constructed, 
// then the expressions is always true.
// It is proved.
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

```
## 1) p47: (A && B) => (A || B)
```
		// 1) p47: (A && B) => (A || B)
		Bool b = new Imply
				( new And(A,B)
				, new Or (A,B))
				;
		Tactic t;
		t = new TacticHole();
        // complete the tactic t to prove the expression b

		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

```
## 2) p48: (A || B) => (B || A)
```
		// 2) p48: (A || B) => (B || A)
		b = new Imply
				(new Or(A,B)
				,new Or(B,A));
        // complete the tactic t to prove the expression b

		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
```
## 3) p48: A => ! (! A)
```
		// 3) p48: A => ! (! A)
		b = new Imply(A,new Not(new Not(A)));
        // complete the tactic t to prove the expression b

		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
```
## 4) p48: (A => B) => (!B => !A)
```
		// 4) p48: (A => B) => (!B => !A)
		b = new Imply(new Imply(A,B),new Imply(new Not(B), new Not(A)));
        // complete the tactic t to prove the expression b

		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
```
## 5) p48: (!A || B) => (A => B)
```
		// 5) p48: (!A || B) => (A => B)
		b = new Imply(new Or(new Not(A),B),new Imply(A,B));
        // complete the tactic t to prove the expression b

		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

```
## 6) p48: (&& monoid) 
```
		// 6) p48: (&& monoid) 
		// (A && B) && C <=> A && (B && C) (assoc)
		// 
```
### 6.1) (A && B) && C => A && (B && C) (assoc)
```
		// 6.1) (A && B) && C => A && (B && C) (assoc)
		b = new Imply(new And(new And(A,B),C),new And(A,new And(B,C)));
        // complete the tactic t to prove the expression b

		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

```
### 6.2) A && (B && C) => (A && B) && C (assoc)
```
		// 6.2) A && (B && C) => (A && B) && C (assoc)
		b = new Imply(new And(A,new And(B,C)),new And(new And(A,B),C));
        // complete the tactic t to prove the expression b

		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
```
## 7) A && A <=> A (idempotent)
```
		// 7) A && A <=> A (idempotent)
		// 
```
### 7.1) A && A => A 
```
		// 7.1) A && A => A 
		b = new Imply(new And(A,A),A);
        // complete the tactic t to prove the expression b

		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

```
### 7.2) A => A && A
```
		// 7.2) A => A && A
		b = new Imply(A,new And(A,A));
        // complete the tactic t to prove the expression b
		
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));
		
```
## 8) True && A <=> A (identity)
```
		// 8) True && A <=> A (identity)
		// 
```
### 8.1) True && A => A (identity)
```
		// 8.1) True && A => A (identity)
		b = new Imply(new And(new True(),A),A);
        // complete the tactic t to prove the expression b
		
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

```
### 8.2) A => True && A (identity)
```
		// 8.2) A => True && A (identity)
		b = new Imply(A,new And(new True(),A));
        // complete the tactic t to prove the expression b
		
		System.out.println("\nproof of " + b);
		System.out.println("with " + t);
		System.out.println(t.prove(b));

// prove other propreties 
		
	}
}




```
