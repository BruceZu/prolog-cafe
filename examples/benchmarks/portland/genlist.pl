:- dynamic seed/1.

/*  VAX C-Prolog Benchmark Package  */
/*  Copyright 1985 by Tektronix, Inc., and Portland State University  */

make_list(0,[]) :- !.

make_list(N,[X|L]) :-
	rnd(100,X),
	N1 is N-1,
	make_list(N1,L).

seed(13).

rnd(R,N) :-
	retract(seed(S)), N is (S mod R) + 1,
	Newseed is (125*S+1) mod 4096,
	asserta(seed(Newseed)), !.
