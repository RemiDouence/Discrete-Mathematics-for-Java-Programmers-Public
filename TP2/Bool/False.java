// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// ## `False` 
package TP2.Bool;

public class False extends Bool {
	final public String toString() {
		return "false";
	}
	public boolean equals(Object o) {
		return o instanceof False;
	}
}

