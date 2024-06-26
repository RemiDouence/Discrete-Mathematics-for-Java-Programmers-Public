
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `TacticOrI2` (disjunction introduction) 
package TP2;

import TP2.Bool.*;

public class TacticOrI2 extends Tactic {
	Tactic t;
	public TacticOrI2(Tactic t) {
		this.t = t;
	}
	public TacticOrI2() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticOrI2(" + t + ")";
	}
	// To prove A || B, let us prove B. 
	public boolean prove(Sequent s) {
		if (s.goal instanceof Or) {
			Or e = (Or)(s.goal);
			return t.prove(s.copy(e.b2));
		} else {
			System.out.println("TacticOrI2.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}

