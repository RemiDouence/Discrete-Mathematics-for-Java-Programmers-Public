// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// ## `Not` 
package TP2.Bool;

public class Not extends Bool {
	final public Bool b1;
	public Not(Bool b1) {
		this.b1 = b1;
	}
	public String toString() {
		return "(!" + b1 + ")";
	}
	public boolean equals(Object o) {
		return o instanceof Not 
			&& b1.equals(((Not)o).b1);
	}
}


