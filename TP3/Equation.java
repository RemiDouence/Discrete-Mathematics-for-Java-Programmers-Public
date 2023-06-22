// # `Equation` (equation) 
// `Equation e1 e2` represents the equality `e1 = e2`. 
package TP3;

import java.util.HashMap;

public class Equation {
	public Exp lhs, rhs;
	public Equation(Exp lhs, Exp rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public String toString() {
		return lhs + " == " + rhs;
	}
	public Equation substitute(HashMap<String,Exp> ss) {
		return new Equation(lhs.substitute(ss),rhs.substitute(ss));
	}
	public Equation apply(Rule r) {
		lhs = lhs.apply(r);
		rhs = rhs.apply(r);
		return this;
	}
	public boolean isTrivial() {
		return lhs.equals(rhs);
	}
	public void print() {
		System.out.println(this);
	}
}

