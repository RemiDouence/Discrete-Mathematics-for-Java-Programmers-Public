
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `TacticImplyE` (implication elimination) 
package TP2;

import TP2.Bool.*;

public class TacticImplyE extends Tactic {
	Bool b;
	Tactic t1, t2;
	public TacticImplyE(Bool b, Tactic t1, Tactic t2) {
		this.b = b;
		this.t1 = t1;
		this.t2 = t2;
	}
	public TacticImplyE(Bool b, Tactic t1) {
		this(b, t1, new TacticHole());
	}
	public TacticImplyE(Bool b) {
		this(b, new TacticHole());
	}
	public String toString() {
		return "TacticImplyE(" + b + "," + t1 + "," + t2 + ")";
	}
	// To prove B, 
	// let us introduce a new hypothesis A => B, prove B with this hypothesis
	// then prove A.  
	public boolean prove(Sequent s) {
		return t1.prove(s.copy(new Imply(b,s.goal)))
				&& t2.prove(s.copy(b));
	}
}

