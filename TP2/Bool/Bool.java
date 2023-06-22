// # `Bool`
// The class `Bool` (and its subclasses) represents an abstract syntax tree. 
// This structure has no behavior (i.e., no method) beside `toString` and `equals`
package TP2.Bool;

public abstract class Bool {
	// `public String toString();` is inherited from `Object`. 
	// The printers are compliant with the syntax of Java for boolean expressions
	
	// `hasCode` is required by `HashSet`. 
	// Note that `HashSet.contains` calls `static Object.equals` calls `Object.equals` 
	public int hashCode() { 
		return 0;
	}
}

