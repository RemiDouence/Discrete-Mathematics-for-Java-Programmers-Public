// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// ## `True` 
package TP2.Bool;

public class True extends Bool {
	public String toString() {
		return "true";
	}
	public boolean equals(Object o) {
		return o instanceof True;
	}
}

