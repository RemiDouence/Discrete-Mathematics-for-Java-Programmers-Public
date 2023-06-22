// ## `And` 
package TP2.Bool;

public class And extends Bool {
	final public Bool b1, b2;
	public And(Bool b1, Bool b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
	public String toString() {
		return "(" + b1 + " && " + b2 + ")";
	}
	public boolean equals(Object o) {
		return o instanceof And 
			&& b1.equals(((And)o).b1) 
			&& b2.equals(((And)o).b2);
	}
}


