// # `TacticImplyI` (implication introduction) 
package TP2;

import TP2.Bool.*;

public class TacticImplyI extends Tactic {
	Tactic t;
	public TacticImplyI(Tactic t) {
		this.t = t;
	}
	public TacticImplyI() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticImplyE(" + t + ")";
	}
	// To prove A => B, let us add A to the hypotheses and prove B
	public boolean prove(Sequent s) {
		if (s.goal instanceof Imply) {
			Imply b = (Imply)(s.goal);
			Sequent s1 = s.copy(b.b2);
			s1.gamma.add(b.b1);
			return t.prove(s1);
		} else {
			System.out.println("TacticImplyI.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}

