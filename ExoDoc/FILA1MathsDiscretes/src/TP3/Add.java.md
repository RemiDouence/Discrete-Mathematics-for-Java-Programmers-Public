// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
```
package TP3;

import java.util.HashMap;

public class Add extends Exp {
	Exp e1, e2;
	public Add(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public String toString() {
		return "Add(" + e1 + "," + e2 + ")";
	}
	// We assume a variable has a single occurrence in a pattern.
	// We assume the same variable does not occurs in `e1` and `e2` at the same time. 
	// We assume patterns are linear. 
	// So there is no need to check the substitution for `e1` are compatible with the substitution for `e2`. 
	public boolean unifiable(Exp other) {
		return other instanceof Add 
				&& e1.unifiable(((Add)other).e1) 
				&& e2.unifiable(((Add)other).e2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = e1.unify(((Add)other).e1);
		result.putAll(e2.unify(((Add)other).e2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Add(e1.substitute(ss),e2.substitute(ss));
	}
	// If we cannot rewrite an `Add` expression, we try to rewrite its subexpressions. 
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Add(e1.apply(eq),e2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Add 
				&& e1.equals(((Add)o).e1) 
				&& e2.equals(((Add)o).e2);
	}
}

```
