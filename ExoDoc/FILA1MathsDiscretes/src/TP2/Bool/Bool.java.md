// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
```
package TP2.Bool;

```
# Package `Bool` and Class `Bool`
- this package represents abstract syntax tree (AST) of boolean expressions
- methods: constructor, printer, equals (structurally = same shape, addresses/references could be different) 
```

public abstract class Bool {
	// `public String toString();` is inherited from `Object`. 
	// The printers are compliant with the syntax of Java for boolean expressions
	
	// `hasCode` is required by `HashSet`. 
	// Note that `HashSet.contains` calls `static Object.equals` calls `Object.equals` 
	public int hashCode() { 
		return 0;
	}
}

```
