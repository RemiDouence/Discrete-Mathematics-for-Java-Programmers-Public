```
// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `S` (successor) 
// `S n` represents the natural `n+1`. 
package TP3;

import java.util.HashMap;

public class S extends Exp {
	Exp p;
	public S(Exp p) {
		this.p = p;
	}
	public String toString() {
		return "S(" + p + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof S && p.unifiable(((S)other).p);
	}
	public HashMap<String, Exp> unify(Exp other) {
		return p.unify(((S)other).p);
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new S(p.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new S(p.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null && o instanceof S && p.equals(((S)o).p);
	}
}

```
