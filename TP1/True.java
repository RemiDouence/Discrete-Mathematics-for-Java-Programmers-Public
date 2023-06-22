// # The True constant
package TP1;

public class True extends Bool {
	// Printer compliant with Java syntax. 
	public String toString() {
		return "true";
	}
	// Trivial evaluation by returning the `true` Java constant
	public boolean eval(Env<Boolean> env) {
		return true;
	}
}

