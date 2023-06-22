// ## `Or` 
package TP2.Bool;

public class Or extends Bool {
	final public Bool b1, b2;
	public Or(Bool b1, Bool b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
	public String toString() {
		return "(" + b1 + " || " + b2 + ")";
	}
	public boolean equals(Object o) {
		return o instanceof Or 
			&& b1.equals(((Or)o).b1) 
			&& b2.equals(((Or)o).b2);
	}
}


