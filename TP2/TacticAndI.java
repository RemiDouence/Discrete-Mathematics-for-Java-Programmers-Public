// # `TacticAndI` (conjunction introduction) 
package TP2;

import TP2.Bool.*;

public class TacticAndI extends Tactic {
	Tactic t1, t2;
	public TacticAndI(Tactic t1, Tactic t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
	public TacticAndI(Tactic t1) {
		this(t1, new TacticHole());
	}
	public TacticAndI() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticAndI(" + t1 + "," + t2 + ")";
	}
	// To prove a conjunction, you have to make two proofs. 
	public boolean prove(Sequent s) {
		if (s.goal instanceof And) {
			And e = (And)s.goal;
			return   t1.prove(s.copy(e.b1)) 
				   & t2.prove(s.copy(e.b2));
		} else {
			System.out.println("TacticAndI.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}

