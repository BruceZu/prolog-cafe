package com.googlecode.prolog_cafe.builtin;
import com.googlecode.prolog_cafe.lang.*;
import java.io.*;
/**
   <code>put_char/2</code><br>
   @author Mutsunori Banbara (banbara@kobe-u.ac.jp)
   @author Naoyuki Tamura (tamura@kobe-u.ac.jp)
   @version 1.0
*/
public class PRED_put_char_2 extends Predicate.P2 {
    public PRED_put_char_2(Term a1, Term a2, Operation cont) {
        arg1 = a1;
        arg2 = a2;
        this.cont = cont;
    }

    public Operation exec(Prolog engine) {
        engine.setB0();
        Term a1, a2;
        a1 = arg1;
        a2 = arg2;
	String str;
	char c;
	Object stream = null;

	// Char
	a2 = a2.dereference(); 
	if (a2.isVariable())
	    throw new PInstantiationException(this, 2);
	if (! a2.isSymbol())
	    throw new IllegalTypeException(this, 2, "character", a2);
	// S_or_a
	a1 = a1.dereference(); 
	if (a1.isVariable()) {
	    throw new PInstantiationException(this, 1);
	} else if (a1.isSymbol()) {
	    if (! engine.getStreamManager().containsKey(a1))
		throw new ExistenceException(this, 1, "stream", a1, "");
	    stream = ((JavaObjectTerm) engine.getStreamManager().get(a1)).object();
	} else if (a1.isJavaObject()) {
	    stream = ((JavaObjectTerm) a1).object();
	} else {
	    throw new IllegalDomainException(this, 1, "stream_or_alias", a1);
	}
	if (! (stream instanceof PrintWriter))
	    throw new PermissionException(this, "output", "stream", a1, "");
	// print single character
	str = ((SymbolTerm)a2).name();
	if (str.length() != 1)
	    throw new IllegalTypeException(this, 2, "character", a2);
	c = str.charAt(0);
	if (! Character.isDefined(c))
	    throw new RepresentationException(this, 2, "character");
	((PrintWriter) stream).print(c);
	return cont;
    }
}
