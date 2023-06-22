// # `Proof` (proof by induction) 
// `Proof(e,x)` represents a proof of an equation `e` by induction on `x`. 
// - `caseZ` is the equation where `x` is instantiated with `Z`. 
// - `caseS` is the equation where `x` is instantiated with `S i`. 
// - `hyp` is the equation where `x` is instantiated with `i`.
package TP3;

import java.util.HashMap;

public class Proof {
	public Equation caseZ;
	public Equation caseS;
	public Rule hyp;
	public Proof(Equation e,String x) {
		HashMap<String,Exp> sZ = new HashMap<String,Exp>();
		sZ.put(x, new Z());
		this.caseZ = e.substitute(sZ);
		HashMap<String,Exp> sS = new HashMap<String,Exp>();
		sS.put(x, new S(new Var("i")));
		this.caseS = e.substitute(sS);
		HashMap<String,Exp> sH = new HashMap<String,Exp>();
		sH.put(x, new Var("i"));
		this.hyp = new Rule(e.lhs.substitute(sH),e.rhs.substitute(sH));
	}
	public String toString() {
		return "" + hyp + "\n" + caseZ + "\n" + caseS;
	}
	// `cqfd` checks that the rewritten equations are trivial. 
	// Hence, a proof is over. 
	public boolean cqfd() {
		return caseZ.isTrivial() && caseS.isTrivial();
	}
}

