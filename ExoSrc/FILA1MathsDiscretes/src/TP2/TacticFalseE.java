```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `TacticFalseE` (false elimination)  
package TP2;

import TP2.Bool.*;

public class TacticFalseE extends Tactic {
	Tactic t;
	public TacticFalseE(Tactic t) {
		this.t = t;
	}
	// a proof in progress contains holes 
	public TacticFalseE() {
		this(new TacticHole());
	}
	public String toString() {
		return "TacticFalseE(" + t + ")";
	}
	// let us (try to) prove false.
	public boolean prove(Sequent s) {
		return t.prove(s.copy(new False()));
	}
}

```
