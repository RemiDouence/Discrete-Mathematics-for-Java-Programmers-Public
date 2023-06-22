// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
//
// Please do not distribute solutions but let people learn by doing the exercices.

// # `Mul` (multiplication) 
// `Mul e1 e2` represents the product `e1 * e2`. 
// `Mul` enables us to define rules for the product. 
package TP3;

import java.util.HashMap;

public class Mul extends Exp {
	Exp p1, p2;
	public Mul(Exp e1, Exp e2) {
		this.p1 = e1;
		this.p2 = e2;
	}
	public String toString() {
		return "Mul(" + p1 + "," + p2 + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Mul 
				&& p1.unifiable(((Mul)other).p1) 
				&& p2.unifiable(((Mul)other).p2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = p1.unify(((Mul)other).p1);
		result.putAll(p2.unify(((Mul)other).p2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Mul(p1.substitute(ss),p2.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Mul(p1.apply(eq),p2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Mul 
				&& p1.equals(((Mul)o).p1) 
				&& p2.equals(((Mul)o).p2);
	}
}

