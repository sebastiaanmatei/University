#pragma once
#include<vector>
#include<utility>

#define MAX 10000

using namespace std;

typedef int TCheie;
typedef int TValoare;

//typedef std::pair<TCheie, TValoare> TElem;

typedef struct structure{
    TCheie key;
    vector<TValoare> array;
}TElem;

//typedef struct structure structure;

class IteratorMD;

class MD
{
    friend class IteratorMD;

private:
    /* aici e reprezentarea */
    int m;

    TElem e[MAX];

    int d(TCheie c, int i) const;//functia de dispersie

public:
    // constructorul implicit al MultiDictionarului
    MD();

    int getm();

    int hashCode(TCheie c) const;
    // adauga o pereche (cheie, valoare) in MD
    void adauga(TCheie c, TValoare v);

    //cauta o cheie si returneaza vectorul de valori asociate
    vector<TValoare> cauta(TCheie c) const;

    int cautaindex(TCheie c)const;

    //sterge o cheie si o valoare
    //returneaza adevarat daca s-a gasit cheia si valoarea de sters
    bool sterge(TCheie c, TValoare v);

    //returneaza numarul de perechi (cheie, valoare) din MD
    int dim() const;

    //verifica daca MultiDictionarul e vid
    bool vid() const;

    // se returneaza iterator pe MD
    IteratorMD iterator() const;

    // destructorul MultiDictionarului
    ~MD();



};

