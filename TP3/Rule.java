// # `Rule` (a rewriting rule) 
// `Rule` a rule is a pair of patterns 
package TP3;

import java.util.HashMap;

public class Rule {
	public Exp lhs, rhs;
	public Rule(Exp lhs, Exp rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public String toString() {
		return lhs + " -> " + rhs;
	}
	public Rule substitute(HashMap<String,Exp> ss) {
		return new Rule(lhs.substitute(ss),rhs.substitute(ss));
	}
}

