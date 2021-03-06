/*  VAX C-Prolog Benchmark Package  */
/*  Copyright 1985 by Tektronix, Inc., and Portland State University  */

:- dynamic n/1.

/*********************************************************/
/* Prolog fragment that demonstrates how much time is    */
/* consumed in the loop housekeeping alone that is       */
/* being used in some of the benchmarks.                 */
/*************************************************RLA*****/

loop(Max) :-
	asserta(n(0)),
	repeat,
	retract(n(N)),
	N1 is N+1,
	asserta(n(N1)),
	N1>=Max, !.
