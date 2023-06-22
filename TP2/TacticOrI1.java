// # `TacticOrI1` (disjunction introduction) 
package TP2;

import TP2.Bool.*;

public class TacticOrI1 extends Tactic {
	Tactic t;
	public TacticOrI1(Tactic t) {
		this.t = t;
	}
	public TacticOrI1() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticOrI1(" + t + ")";
	}
	// To prove A || B, let us prove A. 
	public boolean prove(Sequent s) {
		if (s.goal instanceof Or) {
			Or e = (Or)(s.goal);
			return t.prove(s.copy(e.b1));
		} else {
			System.out.println("TacticOrI1.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}

