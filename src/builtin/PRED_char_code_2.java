package com.googlecode.prolog_cafe.builtin;
import  com.googlecode.prolog_cafe.lang.*;
/**
 * <code>char_code/2</code><br>
 * @author Mutsunori Banbara (banbara@kobe-u.ac.jp)
 * @author Naoyuki Tamura (tamura@kobe-u.ac.jp)
 * @version 1.0
 */
public class PRED_char_code_2 extends Predicate.P2 {
    public PRED_char_code_2(Term a1, Term a2, Operation cont) {
	arg1 = a1;
	arg2 = a2;
	this.cont = cont;
    }

    public Operation exec(Prolog engine) {
        engine.setB0();
	Term a1, a2;
	a1 = arg1;
	a2 = arg2;

	a1 = a1.dereference();
	a2 = a2.dereference();
	if (a1.isVariable()) { // char_code(-Char, +CharCode)
	    if (a2.isVariable()) {
		throw new PInstantiationException(this, 2);
	    } else if (! a2.isInteger()) {
		throw new IllegalTypeException(this, 2, "integer", a2);
	    }
	    int i = ((IntegerTerm)a2).intValue();
	    if (! Character.isDefined(i))
		throw new RepresentationException(this, 2, "character_code");
	    if (! a1.unify(SymbolTerm.create((char)i), engine.trail))
		return engine.fail();
	} else if (a1.isSymbol()) { // char_code(+Char, ?CharCode)
	    String s = ((SymbolTerm)a1).name();
	    if (s.length() != 1)
		throw new IllegalTypeException(this, 1, "character", a1);
	    if(! a2.unify(new IntegerTerm((int)s.charAt(0)), engine.trail)) 
		return engine.fail();
	} else {
	    return engine.fail();
	}
	return cont;
    }
}
