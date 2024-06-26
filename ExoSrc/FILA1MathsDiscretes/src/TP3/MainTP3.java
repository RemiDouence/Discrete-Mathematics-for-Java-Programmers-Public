// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence

package TP3;

import java.util.LinkedList;

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
		
		// ## PART 1: REWRITING
		
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

		// ## PART 2: PROOF

		// Proof `Z` neutral right for `add`
		Proof p11 = new Proof(new Equation(new Add(x,Z),x), "x");
		System.out.println("\nProof of p11 for add1: " + p11.hyp);
		System.out.println("Proof:");
		// complete before print() 
		p11.caseZ.apply(add1Z).print();
		p11.caseS.apply(add1S).apply(p11.hyp).print();
		System.out.println(p11.cqfd());
		//System.exit(0);

		// ## TODO 
		// ### Proof `add` associative 
		Exp z = new Var("z");
		Proof p12 = new Proof(new Equation(new Add(new Add(x,y),z),new Add(x,new Add(y,z))), "x");
		System.out.println("\nProof of p12 for add1: " + p12.hyp);
		System.out.println("Proof:");
		p12.caseZ.apply(add1Z).print();
		
		p12.caseS.apply(add1S).apply(add1S).apply(p12.hyp).print();
		
		System.out.println(p12.cqfd());
		//System.exit(0);

		// ### Proof `S` jumps args in `add`
		Proof p13 = new Proof(new Equation(new Add(x,new S(y)),new Add(new S(x),y)), "x");
		System.out.println("\nProof of p13 for add1: " + p13.hyp);
		System.out.println("Proof:");
		p13.caseZ.apply(add1Z).apply(add1S).apply(add1Z).print();
		
		p13.caseS.apply(add1S).apply(p13.hyp).print(); 

		System.out.println(p13.cqfd());
		//System.exit(0);
		
		// ### Proof `add` commutative
		Proof p14 = new Proof(new Equation(new Add(x,y),new Add(y,x)), "x");
		System.out.println("\nProof of p14 for add1: " + p14.hyp);
		System.out.println("Proof:");
		p14.caseZ.apply(add1Z).apply(p11.hyp).print();
		
		p14.caseS.apply(add1S).apply(p13.hyp).apply(add1S).print();
		p14.caseS.rhs = p14.caseS.rhs.apply(p14.hyp); 

		System.out.println(p14.cqfd());
		//System.exit(0);
		
		
		// ### Another definition of addition 
		// - `add2(x,Z) -> x` 
		// - `add2(x,S(y)) -> S(add2(x,y))`
		Rule add2Z = new Rule(new Add2(x,Z),x);
		
		Rule add2S = new Rule(new Add2(x, new S(y)), new S(new Add2(x,y)));
		
		LinkedList<Rule> prog2 = new LinkedList<Rule>();
		prog2.add(add2Z);
		prog2.add(add2S);
		System.out.println();
		System.out.println("definition of add: " + prog2);
		twoPlusOne = new Add2(Two, One);
		System.out.println("expression to evaluate: " + twoPlusOne);
		twoPlusOne.fixPoint(prog2);
		//System.exit(0);

		// ### Proof `Z` neutral left for `add2`
		Proof p21 = new Proof(new Equation(new Add2(Z,y),y), "y");
		System.out.println("\nProof of p21 for add2: " + p21);
		System.out.println("Proof:");
		p21.caseZ.apply(add2Z).print();
		
		p21.caseS.apply(add2S).apply(p21.hyp).print();

		System.out.println(p21.cqfd());
		//System.exit(0);

		// ### Proof `add2` associative 
		Proof p22 = new Proof(new Equation(new Add2(new Add2(x,y),z),new Add2(x,new Add2(y,z))), "z");
		System.out.println("\nProof of p22 for add2: " + p22);
		System.out.println("Proof:");
		p22.caseZ.apply(add2Z).print();
		
		p22.caseS.apply(add2S).apply(add2S).apply(p22.hyp).print();

		System.out.println(p22.cqfd());
		//System.exit(0);

		// ### Proof `S` jumps args in `add2`
		Proof p23 = new Proof(new Equation(new Add2(x,new S(y)),new Add2(new S(x),y)), "y");
		System.out.println("\nProof of p23 for add2: " + p23);
		System.out.println("Proof:");
		p23.caseZ.apply(add2Z).apply(add2S).apply(add2Z).print();
		
		p23.caseS.apply(add2S).apply(p23.hyp).print(); 

		System.out.println(p23.cqfd());
		//System.exit(0);
		
		// ### Proof `add2` commutative
		Proof p24 = new Proof(new Equation(new Add2(x,y),new Add2(y,x)), "y");
		System.out.println("\nProof of p24 for add2: " + p24);
		System.out.println("Proof:");
		p24.caseZ.apply(add2Z).apply(p21.hyp).print();
		
		p24.caseS.apply(add2S).apply(p24.hyp).apply(add2S).print();
		p24.caseS.rhs = p24.caseS.rhs.apply(p24.hyp); 

		System.out.println(p24.cqfd());
		//System.exit(0);
				
		// ### Yet another definition of addition
		// define 2 rules equivalent to: `while (x != 0) { x--; y++; } return y;`
		// - `add3(Z,y) -> y` 
		// - `add3(S(x),y) -> add3(x,S(y))`

		Rule add3Z = new Rule(new Add3(Z,y),y);
		
		Rule add3S = new Rule(new Add3(new S(x),y), new Add3(x,new S(y))); 

		LinkedList<Rule> prog3 = new LinkedList<Rule>();
		prog3.add(add3Z);
		prog3.add(add3S);
		System.out.println("definition of add: " + prog3);
		twoPlusOne = new Add3(new S(new S(Z)), new S(Z));
		System.out.println("expression to evaluate: " + twoPlusOne);
		System.out.println("Evaluation");
		twoPlusOne.fixPoint(prog3);
		//System.exit(0);
		
		// ### Proof `S` jumps from second arg of `add3`
		Proof p33 = new Proof(new Equation(new Add3(x,new S(y)),new S(new Add3(x,y))), "x");
		System.out.println("\nProof of p33 for add3: " + p33.hyp);
		System.out.println("Proof:");
		p33.caseZ.apply(add3Z).print();
		
		p33.caseS.apply(add3S).apply(p33.hyp).apply(p33.hyp).print();  
		
		System.out.println(p33.cqfd());
		//System.exit(0);

		// ### Proof `Z` neutral right for `add3`
		Proof p31 = new Proof(new Equation(new Add3(x,Z),x), "x");
		System.out.println("\nProof of p31 for add3: " + p31.hyp);
		System.out.println("Proof:");
		p31.caseZ.apply(add3Z).print();
		
		p31.caseS.apply(add3S).apply(p33.hyp).apply(p31.hyp).print(); 
		
		System.out.println(p31.cqfd());
		//System.exit(0);

		// ### Proof `add3` associative 
		Proof p32 = new Proof(new Equation(new Add3(new Add3(x,y),z),new Add3(x,new Add3(y,z))), "x");
		System.out.println("\nProof of p32 for add3: " + p32.hyp);
		System.out.println("Proof:");
		p32.caseZ.apply(add3Z).print();
		
		p32.caseS.apply(add3S).apply(p33.hyp).apply(add3S).apply(p33.hyp).apply(p32.hyp).print();
		
		System.out.println(p32.cqfd());
		//System.exit(0);

		// ### Proof `add3` commutative
		Proof p34 = new Proof(new Equation(new Add3(x,y),new Add3(y,x)), "x");
		System.out.println("\nProof of p34 for add3: " + p34.hyp);
		System.out.println("Proof:");
		p34.caseZ.apply(add3Z).apply(p31.hyp).print(); 
		
		p34.caseS.apply(add3S).apply(p33.hyp).print(); 
		p34.caseS.rhs = p34.caseS.rhs.apply(p34.hyp); 

		System.out.println(p34.cqfd());
		//System.exit(0);

		// ### Proof of equivalence of `add` and `add3`
		Proof p35 = new Proof(new Equation(new Add(x,y),new Add3(x,y)),"x");
		System.out.println("\nProof of p35 for add/add3: " + p35.hyp);
		System.out.println("Proof:");
		p35.caseZ.apply(add1Z).apply(add3Z).print(); 
		
		p35.caseS.apply(add1S).apply(add3S).apply(p33.hyp).apply(p35.hyp).print(); 

		System.out.println(p35.cqfd());
		//System.exit(0);

		// ### Yet another definition of addition
		// - `add4(Z,y) -> y`
		Rule add4Z = new Rule(new Add4(Z,y),y);

		// - `add4(S(x),y) -> S(add4(y,x))`
		Rule add4S = new Rule(new Add4(new S(x),y), new S(new Add4(y,x)));
		
		LinkedList<Rule> prog4 = new LinkedList<Rule>();
		prog4.add(add4Z);
		prog4.add(add4S);
		System.out.println("definition of add: " + prog4);
		twoPlusOne = new Add4(new S(new S(Z)), new S(Z));
		System.out.println("expression to evaluate: " + twoPlusOne);
		twoPlusOne.fixPoint(prog4);
		//System.exit(0);
		
		// ### Proof `Z` neutral right for `add4`
		Proof p41 = new Proof(new Equation(new Add4(x,Z),x), "x");
		System.out.println("\nProof of p41 for add4: " + p41.hyp);
		System.out.println("Proof:");
		p41.caseZ.apply(add4Z).print(); 
		
		p41.caseS.apply(add4S).apply(add4Z).print(); 

		System.out.println(p41.cqfd());
		//System.exit(0);

		/*
		// TODO
		// proof S jumps args in add
		Proof p45 = new Proof(new Equation(new Add(new S(x),y),new S(new Add(x,y))), "x");
		System.out.println("\nProof of p45 for add4: " + p45.hyp);
		System.out.println("Proof:");
		// complete before print() 
		p45.caseZ.apply(add4S).apply(add4Z).apply(p41.hyp).print(); // .caseZ.apply(add4Z).apply(add4S).apply(p41.hyp).print(); 
		p45.caseS.apply(add4S).print(); // .caseS.apply(add4S).apply(add4S).print();  
		System.out.println(p45.cqfd());
		System.exit(0);
		//
		// TODO
		// proof S jumps args in add
		Proof p43 = new Proof(new Equation(new Add(x,new S(y)),new Add(new S(x),y)), "x");
		System.out.println("\nProof of p43 for add4: " + p43.hyp);
		System.out.println("Proof:");
		// complete before print() 
		p43.caseZ.apply(add4Z).apply(add4S).apply(p41.hyp).print(); 
		p43.caseS.apply(add4S).apply(add4S).print();  
		System.out.println(p43.cqfd());
		//System.exit(0);
		//
		// TODO
		// proof add commutative
		Proof p44 = new Proof(new Equation(new Add(x,y),new Add(y,x)), "x");
		System.out.println("\nProof of p44 for add1: " + p44.hyp);
		System.out.println("Proof:");
		// complete before print() 
		p44.caseZ.apply(add4Z).apply(p41.hyp).print(); // apply(add1Z).apply(p11.hyp).print();
		p44.caseS.apply(add4S).print(); // apply(add1S).apply(p13.hyp).apply(add1S).print();
		//p44.caseS.rhs = p14.caseS.rhs.apply(p14.hyp); 
		System.out.println(p44.cqfd());
		//System.exit(0);
		//
		// TOD
		// proof add associative 
		Proof p42 = new Proof(new Equation(new Add(new Add(x,y),z),new Add(x,new Add(y,z))), "x");
		System.out.println("\nProof of p42 for add1: " + p42.hyp);
		System.out.println("Proof:");
		// complete before print() 
		p42.caseZ.apply(add4Z).print(); // print();
		p42.caseS.apply(add4S).apply(add4S).print(); // apply(add1S).apply(add1S).apply(p12.hyp).print();
		System.out.println(p42.cqfd());
		System.exit(0);
		 */
		
		// ### Another version of addition (not well founded)
		// - `add5(Z,y) -> y`
		Rule add5Z = new Rule(new Add5(Z,y),y);

		// - `add5(S(x),y) -> add5(S(y),x)`
		Rule add5S = new Rule(new Add5(new S(x),y), new Add5(new S(y),x));
		
		LinkedList<Rule> prog5 = new LinkedList<Rule>();
		prog5.add(add5Z);
		prog5.add(add5S);
		System.out.println("definition of add: " + prog5);
		twoPlusOne = new Add5(new S(new S(Z)), new S(Z));
		System.out.println("expression to evaluate: " + twoPlusOne);
		System.out.println(twoPlusOne.fixPoint(prog5));
		// what is the problem with these rules set?
		//System.exit(0);
		
		// ### Definition of `mul`
		// - `mul(Z,y) -> Z` 
		// - `mul(S(x),y) = add(y,mul(x,y))`
		Rule mul1Z = new Rule(new Mul(Z,y),Z);
		Rule mul1S = new Rule(new Mul(new S(x),y),new Add(y,new Mul(x,y)));
		
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
		
		// ### Proof `mul` associative
		Proof p62 = new Proof(new Equation(new Mul(new Mul(x,y),z),new Mul(x,new Mul(y,z))), "x");
		System.out.println("\nProof of p62 for mul: " + p62.hyp);
		System.out.println("Proof:");
		p62.caseZ.apply(mul1Z).apply(mul1Z).print(); 
		
		p62.caseS.apply(mul1S).apply(p62.hyp).print();  

		System.out.println(p62.cqfd());
		//System.exit(0);
		
	}
}

