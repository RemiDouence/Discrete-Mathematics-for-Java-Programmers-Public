// # `TacticTrueI` (true introduction) 
package TP2;

import TP2.Bool.*;

public class TacticTrueI extends Tactic {
	public String toString() {
		return "TacticTrueI";
	}
	public boolean prove(Sequent s) {
		// Checks if the goal is trivially true. 
		if (s.goal instanceof True) {
			return true;
		} else {
			System.out.println("TacticTrueI.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}

