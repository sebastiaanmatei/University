memberx(X, [X|_]).
memberx(X, [_|T]):-memberx(X,T).

list_insert(X,L,R):-list_delete(X,R,L).

list_delete(X, [X|LIST1], LIST1).
list_delete(X, [Y|LIST], [Y|LIST1]):-
    list_delete(X, LIST, LIST1).
