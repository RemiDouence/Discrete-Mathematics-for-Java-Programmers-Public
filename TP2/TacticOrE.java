// # `TacticOrE` (disjunction elimination) 
package TP2;

import TP2.Bool.*;

public class TacticOrE extends Tactic {
	Bool b1, b2;
	Tactic t1, t2, t3;
	public TacticOrE(Bool b1, Bool b2, Tactic t1, Tactic t2, Tactic t3) {
		super();
		this.b1 = b1;
		this.b2 = b2;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}
	public TacticOrE(Bool b1, Bool b2, Tactic t1, Tactic t2) {
		this(b1, b2, t1, t2, new TacticHole());
	}
	public TacticOrE(Bool b1, Bool b2, Tactic t1) {
		this(b1, b2, t1, new TacticHole());
	}
	public TacticOrE(Bool b1, Bool b2) {
		this(b1, b2, new TacticHole());
	}
	public String toString() {
		return "TacticOrE(" + b1 + "," + b2 + "," + t1 + "," + t2 + "," + t3 + ")";
	}
	// To prove A, 
	// let us introduce a new hypothesis B, prove A with the hypothesis B
	// let us introduce a new hypothesis C, prove A with the hypothesis C
	// and prove B || C
	public boolean prove(Sequent s) {
		Sequent s2 = s.copy(s.goal);
		s2.gamma.add(b1);
		Sequent s3 = s.copy(s.goal);
		s3.gamma.add(b2);
		return t1.prove(s.copy(new Or(b1,b2)))
				&& t2.prove(s2)
				&& t3.prove(s3);
	}
}

