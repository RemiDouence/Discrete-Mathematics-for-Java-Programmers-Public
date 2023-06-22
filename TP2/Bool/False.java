// ## `False` 
package TP2.Bool;

public class False extends Bool {
	final public String toString() {
		return "false";
	}
	public boolean equals(Object o) {
		return o instanceof False;
	}
}

