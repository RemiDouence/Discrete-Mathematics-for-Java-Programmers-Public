// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence

package TP3;

import java.util.HashMap;
import java.util.LinkedList;

// `Exp` represents integer expressions  
public abstract class Exp {
	public int hashCode() {
		return 0;
	}
	// `unifiable` decide if a pattern (an expression with variables) has the same shape than another expression. 
	public abstract boolean unifiable(Exp other);
	// `unify` returns the values of the variables so that a pattern match another expression. 
	public abstract HashMap<String,Exp> unify(Exp other);
	// `substitue` replace in a pattern the variables by their values
	public abstract Exp substitute(HashMap<String,Exp> ss);
	// `apply` rewrites an expression E with the rule P1 => P2
	// by unifying P1 with E and returning P2 where variables have been substituted 
	public Exp apply(Rule eq) {
		if (eq.lhs.unifiable(this)) {
			return eq.rhs.substitute(eq.lhs.unify(this));			
		} else {
			return this;
		}
	}
	// `apply` rewrites an expression by applying the first rule of the list that matches 
	public Exp apply(LinkedList<Rule> eqs) {
		for (Rule eq: eqs) {
			if (!equals(apply(eq))) {
				return apply(eq);
			}
		}
		return this;
	}
	// `fixPoint` rewrites an expression with a list of rules until the expression does not change
	public Exp fixPoint(LinkedList<Rule> eqs) {
		Exp result = this;
		System.out.println(result); 
		while (! result.equals(result.apply(eqs))) {
			result = result.apply(eqs);
			System.out.println(result);
		}
		return result;
	}
}

