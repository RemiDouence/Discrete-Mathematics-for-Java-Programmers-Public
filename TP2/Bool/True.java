// ## `True` 
package TP2.Bool;

public class True extends Bool {
	public String toString() {
		return "true";
	}
	public boolean equals(Object o) {
		return o instanceof True;
	}
}

