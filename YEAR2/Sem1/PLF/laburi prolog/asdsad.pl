del(1,[_|T],T1):-T1=T,!.

del(I,[H|T],[H|T1]):-
    I>1,
    N1=I-1,
    del(N1, T, T1).

