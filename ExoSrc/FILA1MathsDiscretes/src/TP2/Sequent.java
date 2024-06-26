// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence

package TP2;

import java.util.HashSet;

import TP2.Bool.*;

// A `Sequent` is a `Pair<Set Bool, Bool>` 
public class Sequent {
	// `gamma` represents the environment as a set of `Bool`
	HashSet<Bool> gamma;
	// `goal` represents the expression to prove as a `Bool`
	Bool goal;
	// By default the environment `gamma` is empty (no hypothesis). 
	public Sequent(Bool goal) {
		this.gamma = new HashSet<Bool>();
		this.goal = goal;
	}
	public Sequent(HashSet<Bool> gamma, Bool goal) {
		this.gamma = gamma;
		this.goal = goal;
	}
	// `copy` returns a copy of the sequent by a shallow copy of the environment 
	public Sequent copy(Bool goal) {
		return new Sequent(new HashSet<Bool>(gamma), goal);
	}
	// The usual syntax for a goal under a set of hypotheses. 
	public String toString() {
		return gamma + " |- " + goal;
	}
}


