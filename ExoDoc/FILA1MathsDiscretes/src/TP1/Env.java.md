// This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs License
// https://creativecommons.org/licenses/by-nc-nd/4.0/
// Remi Douence
```
package TP1;

import java.util.HashMap;

```
# The Environment Class `Env` 
- an expression can contain variables 
- when a variable is bound it has a value
- an environment stores the value of the (bound) variables
- an environment is based on the `HashMap`
```

// The implementation is based on hash maps. 
// The type of values is generic. 
public class Env<A> extends HashMap<String,A> {
	public Env() {
		super();
	}
	public Env(Env<A> that) {
		super(that);
	}
	public A get(String k) {
		return super.get(k);
	}
	// Environments are immutable: `set` returns a new environment. 
	public Env<A> set(String k,A v) {
		// Beware of Java shallow copy in constructors. 
		// Here the values are shared but it is fine. 
		// (And String are immutable in Java). 
		Env<A> result = new Env<A>(this);
		result.put(k, v);
		return result;
	}
	private static final long serialVersionUID = 1L;
	// Testing code 
	public static void main(String[] args) {
		Env<Boolean> e = new Env<Boolean>();
		System.out.println(e);
		Env<Boolean> e1 = e.set("x",true);
		System.out.println(e1);
		Env<Boolean> e2 = e.set("x",false).set("y", true);
		System.out.println(e2);
		System.out.println(e1);
	}
}

```
