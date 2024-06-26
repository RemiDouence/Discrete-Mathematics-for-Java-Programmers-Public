```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `Z` (zero) 
// `Z` represents the natural zero. 
package TP3;

import java.util.HashMap;

public class Z extends Exp {
	public String toString() {
		return "Z";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Z;
	}
	public HashMap<String, Exp> unify(Exp other) {
		return new HashMap<String,Exp>();
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return this;
	}
	public boolean equals(Object o) {
		return o != null && o instanceof Z;
	}
}

```
