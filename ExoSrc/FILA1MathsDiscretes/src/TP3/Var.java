// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence

package TP3;

import java.util.HashMap;

public class Var extends Exp {
	String s;
	public Var(String s) {
		this.s = s;
	}
	public String toString() {
		return s;
	}
	public boolean unifiable(Exp other) {
		return true;
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = new HashMap<String,Exp>();
		result.put(s, other);
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		if (ss.containsKey(s)) {
			return ss.get(s);			
		} else {
			return this;
		}
	}
	public boolean equals(Object o) {
		return o != null && o instanceof Var && s.equals(((Var)o).s);
	}
}

