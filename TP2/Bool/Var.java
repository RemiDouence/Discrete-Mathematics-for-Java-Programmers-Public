// ## `Var` 
package TP2.Bool;

public class Var extends Bool {
	final public String s;
	public Var(String s) {
		this.s = s;
	}
	public String toString() {
		return s;
	}
	// `equals` compares the sequence of characters (not the address of the `String`)
	public boolean equals(Object o) {
		return o instanceof Var 
			&& s.equals(((Var)o).s);
	}
}


