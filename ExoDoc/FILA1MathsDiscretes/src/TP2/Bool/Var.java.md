
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// ## `Var` 
package TP2.Bool;

public class Var extends Bool {
	final public String s;
	public Var(String s) {
		this.s = s;
	}
	public String toString() {
		return s;
	}
	// `equals` compares the sequence of characters (not the address of the `String`)
	public boolean equals(Object o) {
		return o instanceof Var 
			&& s.equals(((Var)o).s);
	}
}


