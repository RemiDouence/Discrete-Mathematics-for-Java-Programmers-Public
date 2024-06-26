// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
```
package TP1;

```
# The class `Var`
- represents variables in an expression 
- each variable has a name (an identifier) 
- the value of each variable is stored in an environment (see the method `eval`)
```

public class Var extends Bool {
	// The name of the constant. 
	final public String s;
	public Var(String s) {
		this.s = s;
	}
	public String toString() {
		return s;
	}
	// The interpreter looks up the value of the constant 
	// in the environment (received as a parameter).
	// We assume the constant is in the environment,
	// (no check, no error management).
	// Nothing prevents you from filling up your car tank with water. 
	// But you should not.
	public boolean eval(Env<Boolean> env) {
		return env.get(s);
	}
}
```
