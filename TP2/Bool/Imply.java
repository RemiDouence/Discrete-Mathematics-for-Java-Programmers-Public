// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// ## `Imply` 
package TP2.Bool;

public class Imply extends Bool {
	final public Bool b1, b2;
	public Imply(Bool b1, Bool b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
	public String toString() {
		return "(" + b1 + " => " + b2 + ")";
	}
	public boolean equals(Object o) {
		return o instanceof Imply 
			&& b1.equals(((Imply)o).b1) 
			&& b2.equals(((Imply)o).b2);
	}
}


