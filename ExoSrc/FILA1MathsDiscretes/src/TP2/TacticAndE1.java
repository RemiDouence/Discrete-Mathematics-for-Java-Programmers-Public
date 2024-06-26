// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence

package TP2;

import TP2.Bool.*;

public class TacticAndE1 extends Tactic {
	Tactic t1;
	Bool e2;
	public TacticAndE1(Tactic t, Bool e2) {
		this.t1 = t;
		this.e2 = e2;
	}
	public TacticAndE1(Bool e2) {
		this(new TacticHole(),e2);
	}
	public String toString() {
		return "TacticAndE1(" + t1 + "," + e2 + ")";
	}
	// To prove A, let us prove A && B
	public boolean prove(Sequent s) {
		return t1.prove(s.copy(new And(s.goal,e2)));
	}
}

