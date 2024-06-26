```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `TacticNotE` (negation elimination)  
package TP2;

import TP2.Bool.*;

public class TacticNotE extends Tactic {
	Bool b;
	Tactic t1, t2;
	public TacticNotE(Bool b, Tactic t1, Tactic t2) {
		this.b = b;
		this.t1 = t1;
		this.t2 = t2;
	}
	public TacticNotE(Bool b, Tactic t1) {
		this(b, t1, new TacticHole());
	}
	public TacticNotE(Bool b) {
		this(b, new TacticHole());
	}
	public String toString() {
		return "TacticNotE(" + b + "," + t1 + "," + t2 + ")";
	}
	// When the goal is false, 
	// this tactic attempts to show a contradiction 
	// by proving both a property `b` and its negation. 
	public boolean prove(Sequent s) {
		if (s.goal instanceof False) {
			return   t1.prove(s.copy(b)) 
				   & t2.prove(s.copy(new Not(b)));
		} else {
			System.out.println("TacticNotE.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}

```
