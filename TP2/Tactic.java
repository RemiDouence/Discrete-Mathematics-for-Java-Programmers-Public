// # `Tactic` 
package TP2;

import TP2.Bool.*;

public abstract class Tactic {
	// A tactic attempts to prove it sequent argument. 
	// A tactic returns `true` if the proof is in complete.
	// A tactic returns `false` if the proof is in progress.
	// An invalid tactic halts the program with an error message.
	public abstract boolean prove(Sequent s); 
	// The proof of a goal starts from an initial sequent (with no hypothesis). 
	public boolean prove(Bool goal) {
		return prove(new Sequent(goal));
	}
	// The printer returns an ascii representation of the tree of composed tactics 
}

