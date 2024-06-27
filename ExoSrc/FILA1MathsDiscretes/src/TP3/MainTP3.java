// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence

package TP3;

import java.util.LinkedList;



// In TP3 we represent an arithmetic expressions as an Abstract Syntax Tree.
// An arithmetic expression is composed of 
// - natural numbers (peano Zero and Succ)
// - function constants such as Add
// - variables 
// We define a list of equation and interpret it as rewriting rules.
// This enables us to execute programs (perform computation).
// This also enables us to proof properties by induction.

public class MainTP3 {
		
	public static void main(String[] args) {

		// Natural number grammar
		// 
		// `N ::= Zero | Suc N | Add N N` 

		// Pattern grammar
		// 
		// `P ::= Zero | Suc P | Add P P | Var` 

		// Rule grammar
		// 
		// `R ::= P -> P` 
		// 
		// Examples of `Add`
		// - `add Z     y -> y`			[ADDZ]  
		// - `add (S x) y -> S (add x y)`	[ADDS]
		// 
		// Sequence of rewrites for 2 + 1:
		// - `add (S (S Z)) (S Z)	->` [ADDS] 
		// - `S (add (S Z) (S Z))  ->` [ADDS]
		// - `S (S (add Z (S Z)))  ->` [ADDZ]
		// - `S (S (S Z))`

		// Questions : 
	    // - is the order of rules important? 
		// - is the place (redex) of the rewrites important?
		

		
		// Definition of add with rewriting rules 
		// - `add(Z,y) -> y` 
		// - `add(S(x),y) -> S(add(x,y))`
		
		// In Java we would program:
		// - `Exp Z.add(Exp y) { return y; }`
		// - `Exp S.add(Exp y) { return new S(this.p.add(y)); }`
		Exp Z = new Z();
		Exp One = new S(Z);
		Exp Two = new S(One);
		Exp Three = new S(Two);
		Exp x = new Var("x");
		Exp y = new Var("y");
		Rule add1Z = new Rule(new Add(Z,y),y);
		Rule add1S = new Rule(new Add(new S(x),y),new S(new Add(x,y)));
		LinkedList<Rule> prog1 = new LinkedList<Rule>();
		prog1.add(add1Z);
		prog1.add(add1S);
		System.out.println("definition of add: " + prog1);
		Exp twoPlusOne = new Add(Two, One);
		System.out.println("expression to evaluate: " + twoPlusOne);
		System.out.println("Evaluation of add1");
		twoPlusOne.fixPoint(prog1);
		//System.exit(0);



		// Proof `Z` neutral right for `add`
		Proof p11 = new Proof(new Equation(new Add(x,Z),x), "x");
		System.out.println("\nProof of p11 for add1: " + p11.hyp);
		System.out.println("Proof:");
		// complete before print() 
		p11.caseZ.apply(add1Z).print();
		p11.caseS.apply(add1S).apply(p11.hyp).print();
		System.out.println(p11.cqfd());
		//System.exit(0);


        Proof p12 = null; // Proof `add` associative 
		
		System.out.println(p12.cqfd());
		//System.exit(0);


        Proof p13 = null; // Proof `S` jumps args in `add`

		System.out.println(p13.cqfd());
		//System.exit(0);
		

        Proof p14 = null; // Proof `add` commutative

		System.out.println(p14.cqfd());
		//System.exit(0);
		
		

		// - `add2(x,Z) -> x` 
		// - `add2(x,S(y)) -> S(add2(x,y))`
        Rule add2Z = null; Rule add2S = null;
		
		LinkedList<Rule> prog2 = new LinkedList<Rule>();
		prog2.add(add2Z);
		prog2.add(add2S);
		System.out.println();
		System.out.println("definition of add: " + prog2);
		twoPlusOne = new Add2(Two, One);
		System.out.println("expression to evaluate: " + twoPlusOne);
		twoPlusOne.fixPoint(prog2);
		//System.exit(0);


        Proof p21 = null; // Proof `Z` neutral left for `add2`

		System.out.println(p21.cqfd());
		//System.exit(0);


        Proof p22 = null; // Proof `add2` associative 

		System.out.println(p22.cqfd());
		//System.exit(0);


        Proof p23 = null; // Proof `S` jumps args in `add2`

		System.out.println(p23.cqfd());
		//System.exit(0);
		

        Proof p24 = null; // Proof `add2` commutative

		System.out.println(p24.cqfd());
		//System.exit(0);
				

		// define 2 rules equivalent to: `while (x != 0) { x--; y++; } return y;`
		// - `add3(Z,y) -> y` 
		// - `add3(S(x),y) -> add3(x,S(y))`
        Rule add3Z = null; add3S = null; 

		LinkedList<Rule> prog3 = new LinkedList<Rule>();
		prog3.add(add3Z);
		prog3.add(add3S);
		System.out.println("definition of add: " + prog3);
		twoPlusOne = new Add3(new S(new S(Z)), new S(Z));
		System.out.println("expression to evaluate: " + twoPlusOne);
		System.out.println("Evaluation");
		twoPlusOne.fixPoint(prog3);
		//System.exit(0);
		

        Proof p33 = null; // Proof `S` jumps from second arg of `add3`
		
		System.out.println(p33.cqfd());
		//System.exit(0);


        Proof p31 = null; // Proof `Z` neutral right for `add3`
		
		System.out.println(p31.cqfd());
		//System.exit(0);


        Proof p32 = null; // Proof `add3` associative 
		
		System.out.println(p32.cqfd());
		//System.exit(0);


        Proof p34 = null; // Proof `add3` commutative

		System.out.println(p34.cqfd());
		//System.exit(0);


        Proof p35 = null; // Proof of equivalence of `add` and `add3`

		System.out.println(p35.cqfd());
		//System.exit(0);


		// - `add4(Z,y) -> y`
		// - `add4(S(x),y) -> S(add4(y,x))`
        Rule add4Z = null; Rule add4S = null; 
		
		LinkedList<Rule> prog4 = new LinkedList<Rule>();
		prog4.add(add4Z);
		prog4.add(add4S);
		System.out.println("definition of add: " + prog4);
		twoPlusOne = new Add4(new S(new S(Z)), new S(Z));
		System.out.println("expression to evaluate: " + twoPlusOne);
		twoPlusOne.fixPoint(prog4);
		//System.exit(0);
		

        Proof p41 = null; //Proof `Z` neutral right for `add4`

		System.out.println(p41.cqfd());
		//System.exit(0);

        // prove add is associative, commutative
		

		// - `add5(Z,y) -> y`
		// - `add5(S(x),y) -> add5(S(y),x)`
        Rule add5Z = null; Rule add5S = null; 
		
		LinkedList<Rule> prog5 = new LinkedList<Rule>();
		prog5.add(add5Z);
		prog5.add(add5S);
		System.out.println("definition of add: " + prog5);
		twoPlusOne = new Add5(new S(new S(Z)), new S(Z));
		System.out.println("expression to evaluate: " + twoPlusOne);
		System.out.println(twoPlusOne.fixPoint(prog5));
		// what is the problem with these rules set?
		//System.exit(0);
		

		// - `mul(Z,y) -> Z` 
		// - `mul(S(x),y) = add(y,mul(x,y))`
        Rule mul1Z = null; Rule mul1S = null; // Definition of `mul`
		
		LinkedList<Rule> prog7 = new LinkedList<Rule>();
		prog7.add(add1Z);
		prog7.add(add1S);
		prog7.add(mul1Z);
		prog7.add(mul1S);
		System.out.println();
		System.out.println("definition of add and mul: " + prog7);
		Exp twoTimesThree = new Mul(Two,Three);
		System.out.println("expression to evaluate: " + twoTimesThree);
		System.out.println("Evaluation");
		twoTimesThree.fixPoint(prog7);
		//System.exit(0);
		

        // Proof `mul` associative
		
	}
}

