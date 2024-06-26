```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `TacticNotI` (negation introduction)
package TP2;

import TP2.Bool.*;

public class TacticNotI extends Tactic {
	Tactic t;
	public TacticNotI(Tactic t) {
		this.t = t;
	}
	public TacticNotI() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticNotI(" + t + ")";
	}
	// when the goal is a negation, 
	// this tactic adds the positive goal (without the negation) to the hypothesis
	// and attempts to prove false. 
	// This is also known as a proof by contradiction (preuve par l'absurde en francais). 
	public boolean prove(Sequent s) {
		if (s.goal instanceof Not) {
			Sequent s1 = s.copy(new False());
			s1.gamma.add(((Not)(s.goal)).b1);
			return t.prove(s1);
		} else {
			System.out.println("TacticNotI.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}

```
