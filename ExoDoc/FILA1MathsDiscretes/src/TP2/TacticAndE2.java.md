// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
```
package TP2;

import TP2.Bool.*;

public class TacticAndE2 extends Tactic {
	Bool e1;
	Tactic t2;
	public TacticAndE2(Bool e1, Tactic t2) {
		this.e1 = e1;
		this.t2 = t2;
	}
	public TacticAndE2(Bool e1) {
		this(e1, new TacticHole());
	}
	public String toString() {
		return "TacticAndE2(" + e1 + "," + t2 + ")";
	}
	// To prove A, let us prove B && A
	public boolean prove(Sequent s) {
		return t2.prove(s.copy(new And(e1,s.goal)));
	}
}

```
