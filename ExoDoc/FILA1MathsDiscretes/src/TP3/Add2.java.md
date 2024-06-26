// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
```
package TP3;

import java.util.HashMap;

public class Add2 extends Exp {
	Exp e1, e2;
	public Add2(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public String toString() {
		return "Add2(" + e1 + "," + e2 + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Add2 
				&& e1.unifiable(((Add2)other).e1) 
				&& e2.unifiable(((Add2)other).e2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = e1.unify(((Add2)other).e1);
		result.putAll(e2.unify(((Add2)other).e2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Add2(e1.substitute(ss),e2.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Add2(e1.apply(eq),e2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Add2 
				&& e1.equals(((Add2)o).e1) 
				&& e2.equals(((Add2)o).e2);
	}
}

```
