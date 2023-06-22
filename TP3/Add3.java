// # `Add1` (addition) 
// `Add1 e1 e2` represents the sum `e1 + e2`. 
// `Add1` enables us to define alternative rules for the addition. 
package TP3;

import java.util.HashMap;

public class Add3 extends Exp {
	Exp e1, e2;
	public Add3(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public String toString() {
		return "Add3(" + e1 + "," + e2 + ")";
	}
	public boolean unifiable(Exp other) {
		return other instanceof Add3 
				&& e1.unifiable(((Add3)other).e1) 
				&& e2.unifiable(((Add3)other).e2);
	}
	public HashMap<String, Exp> unify(Exp other) {
		HashMap<String,Exp> result = e1.unify(((Add3)other).e1);
		result.putAll(e2.unify(((Add3)other).e2));
		return result;
	}
	public Exp substitute(HashMap<String, Exp> ss) {
		return new Add3(e1.substitute(ss),e2.substitute(ss));
	}
	public Exp apply(Rule eq) {
		if (! equals(super.apply(eq))) {
			return super.apply(eq);
		} else {
			return new Add3(e1.apply(eq),e2.apply(eq));			
		}
	}
	public boolean equals(Object o) {
		return o != null 
				&& o instanceof Add3 
				&& e1.equals(((Add3)o).e1) 
				&& e2.equals(((Add3)o).e2);
	}
}

