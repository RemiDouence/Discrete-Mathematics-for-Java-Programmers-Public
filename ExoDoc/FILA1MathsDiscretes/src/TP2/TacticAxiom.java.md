
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `TacticAxiom`  
package TP2;

public class TacticAxiom extends Tactic {
	public String toString() {
		return "TacticAxiom";
	}
	public boolean prove(Sequent s) {
		// Checks if the goal is one of the hypotheses. 
		if (s.gamma.contains(s.goal)) {
			return true;
		} else {
			System.out.println("TacticAxiom.prove mismatch " + s);
			System.exit(0);
			return false; // dummy
		}
	}
}

