#include "TestScurt.h"
#include "MD.h"
#include "IteratorMD.h"
#include <assert.h>
#include <iostream>
#include <stdlib.h>
#include <vector>

#include <exception>

using namespace std;

void testCreeaza() {
    MD m;
    assert(m.vid() == true);
    //assert(m.dim() == 0);
    //cout<<"xx";
    IteratorMD im = m.iterator();
    //assert(im.valid() == true);
    for (int i = -10; i < 30; i++) {
        assert(m.sterge(i, i) == false);
    }
    vector<TValoare> v;
    for (int i = -10; i < 30; i++) {
        v=m.cauta(i);
        assert(v.size()==0);
    }
    cout<<"test create check\n";
}

void testAdauga() {
    MD m; //adaugam elemente
    for (int i = 0; i < 10; i++) {
        m.adauga(i, i);
    }
    assert(m.vid() == false);
    //assert(m.dim() == 10);

    for (int i = -10; i < 20; i++) { //mai adaugam elemente [-10, 20), acum anumite elemente [0, 10) sunt de 2 ori
        m.adauga(i, 2*i);
    }
    assert(m.vid() == false);
    assert(m.dim() == 40);

    for (int i = -200; i < 200; i++) { //numaram de cate ori apar anumite elemente (inclusiv elemente inexistente)
        vector<TValoare> v;
        if (i < -100) {
            v=m.cauta(i);
            assert(v.size() == 0);
        }
        else if (i < -10) {
            v=m.cauta(i);
            assert(v.size() == 0);
        }
        else if (i < 0) {
            v=m.cauta(i);
            assert(v.size() == 1);
        }
        else if (i < 10) {
            v=m.cauta(i);
            assert(v.size() == 2);
        }
        else if (i < 20) {
            v=m.cauta(i);
            assert(v.size() == 1);
        }
        else if (i < 100) {
            v=m.cauta(i);
            assert(v.size() == 0);
        }
        else {
            v=m.cauta(i);
            assert(v.size() == 0);
        }
    }


    for (int i = -100; i < 100; i++) {
        //mai adaugam elemente [-100, 100), acum anumite elemente [0, 10) sunt de 3 ori, altele [-10, 0), si [10, 20) sunt de 2 ori
        m.adauga(i, 3*i);
    }
    assert(m.vid() == false);
    assert(m.dim() == 240);


    for (int i = 10000; i > -10000; i--) { //adaugam mult, si acum prima data adaugam valori mari, dupa aceea mici
        m.adauga(i, 4*i);
    }
    assert(m.dim()==10240);


    cout<<"test add check\n";

}

void testSterge() {
    MD m;
    vector<TValoare> v;
    for (int i = -100; i < 100; i++) { //stergem din colectie vida
        assert(m.sterge(i, i) == false);
    }
    assert(m.dim() == 0);
    for (int i = -100; i < 100; i = i + 2) { //adaugam elemente din 2 in 2 (numere pare)
        m.adauga(i, i);
    }
    for (int i = -100; i < 100; i++) { //stergem tot (inclusiv elemente inexistente)

        if (i % 2 == 0) {
            assert(m.sterge(i, i) == true);
            assert(m.sterge(i, 2*i) == false);
        }
        else {
            assert(m.sterge(i, i) == false);
            assert(m.sterge(i, 2*i) == false);
        }
    }
    assert(m.dim() == 0);


    //adaug multe elemente cu aceeasi cheie si apoi le sterg
    for (int i = 0; i <= 100; i++)
        m.adauga(0, i);
    //adaug un elemnet cu o alta cheie
    m.adauga(1, 100);
    //sterg toate elementele cu cheia 0
    for (int i = 0; i <= 100; i++)
        assert(m.sterge(0, i) == true);
    //va trebui sa ramana un doar o perecheie (c, v) in dictionar - (1,100)
    //vector<TValoare> v;
    v = m.cauta(1);
    assert(v.size() == 1 && v.at(0) == 100);
    v = m.cauta(0);
    assert(v.size() == 0);
    IteratorMD it = m.iterator();
    it.urmator();
    assert(it.valid() == false);
    //stergem si perechea (1,100)
    assert(m.sterge(1, 100) == true);
    //MD devine vid
    //cout << m.dim() << endl;
    assert(m.dim() == 0);
    for (int i = -100; i <= 100; i = i + 2) { //adaugam elemente din 2 in 2
        m.adauga(i, 2 * i);
    }
    for (int i = 100; i > -100; i--) { //stergem descrescator (in ordine inversa fata de ordinea adaugarii)
        if (i % 2 == 0) {
            assert(m.sterge(i, 3 * i + 1) == false);
            assert(m.sterge(i, 2 * i) == true);
        }
        else {
            assert(m.sterge(i, 3 * i + 1) == false);
        }
    }
    //cout << m.dim() << endl;
    assert(m.dim() == 1);
    bool b = m.sterge(-100, -200);
    assert(b == true);
    for (int i = -100; i < 100; i++) { //adaugam de 5 ori pe fiecare element
        if (i != 0) {
            m.adauga(i, i + 1);
            m.adauga(i, 2 * i + 1);
            m.adauga(i, 3 * i + 1);
            m.adauga(i, 4 * i + 1);
            m.adauga(i, 5 * i + 1);
        }
    }
    //cout << m.dim() << endl;
    assert(m.dim() == 995);
    for (int i = -100; i < 100; i++) {
        if (i != 0) {
            v = m.cauta(i);
            assert(v.size() == 5);
        }
    }
    for (int i = -100; i < 100; i++) { //stergem o aparitie de la fiecare
        if (i != 0)
            assert(m.sterge(i, i + 1) == true);
    }
    //cout << m.dim() << endl;
    assert(m.dim() == 796);
    for (int i = -100; i < 100; i++) {
        if (i != 0) {
            v = m.cauta(i);
            assert(v.size() == 4);
        }
    }
    for (int i = -200; i < 200; i++) { //stergem 5 aparitii de la elemente inexistente si existente (dar si acolo exista doar 4 aparitii)
        if (i < -100 || i >= 100) {
            assert(m.sterge(i, i) == false);
            assert(m.sterge(i, i) == false);
            assert(m.sterge(i, i) == false);
            assert(m.sterge(i, i) == false);
            assert(m.sterge(i, i) == false);
        }
        else
        if (i != 0) {
            assert(m.sterge(i, i + 1) == true);
            assert(m.sterge(i, 2 * i + 1) == true);
            assert(m.sterge(i, 3 * i + 1) == true);
            assert(m.sterge(i, 4 * i + 1) == true);
            assert(m.sterge(i, 5 * i + 1) == true);
        }
    }
    //cout << m.dim() << endl;
    assert(m.dim() == 0);
    for (int i = -1000; i < 1000; i++) {
        v = m.cauta(i);
        assert(v.size() == 0);
    }
    int min = -200;
    int max = 200;
    while (min < max) { //adaugam elemente, pe 0 de 2 ori
        m.adauga(min, min);
        m.adauga(max, max);
        min++;
        max--;
    }
    m.adauga(0, 100);
    m.adauga(0, 200);
    assert(m.dim() == 402);
    for (int i = -30; i < 30; i++) { //stergem o parte dintre elemente
        v = m.cauta(i);
        if (i == 0) assert(v.size() == 2);
        else assert(v.size() == 1);
        if (i != 0) assert(m.sterge(i, i) == true);
        if (i != 0) {
            v = m.cauta(i);
            assert(v.size() == 0);
        }
    }
    //cout << m.dim() << endl;
    assert(m.dim() == 343);

    cout<<"test delete check\n";
}



void testIterator() { // nu stim reprezentarea MD, putem testa doar anumite lucruri generale, nu stim in ce ordine vor fi afisate elementele.

    MD m;
    IteratorMD im = m.iterator(); //iterator pe MD vid
    assert(im.valid() == false);

    for (int i = 0; i < 100; i++) {  //adaug 100 de elemente, fiecare e cheia si valoarea 33
        m.adauga(33, 33);

    }

    IteratorMD im2 = m.iterator(); //daca iterez doar 33 poate sa-mi dea iteratorul
    assert(im2.valid() == true);

    TElem elem = im2.element();
    im2.prim(); //resetam pe primul elemente
    assert(im2.valid() == true);
    for (int i = 0; i < 100; i++) {
        TElem elem = im2.element();
        TElem elem2 = im2.element();
        im2.urmator();
    }
    assert(im2.valid() == false);

    MD m2;
    for (int i = -100; i < 100; i++) { //adaug 200 de elemente, fiecare de 3 ori
        m2.adauga(i, 2*i);
        m2.adauga(i, 3*i);
        m2.adauga(i, 4*i);
    }
    //cout<<"inca pe iterator";
    IteratorMD im3 = m2.iterator();
    assert(im3.valid() == true); //nu avem garantia ca elementele afisate vor fi egale, (adica ca vom avea acelasi element de 3 ori consecutiv), testam doar ca sunt 600 de elemente
    for (int i = 0; i < 600; i++) {
        TElem e1 = im3.element();
        im3.urmator();
    }
    assert(im3.valid() == false);
    im3.prim();
    assert(im3.valid() == true);
    MD m3;
    for (int i = 0; i < 200; i= i + 4) { //adaugam doar multipli de 4
        m3.adauga(i, 5*i);
    }
    IteratorMD im4 = m3.iterator();
    assert(im4.valid() == true);
    int count = 0;
    while (im4.valid()) { //fiecare element e multiplu de 4 si sunt in total 50 de elemente
        TElem e = im4.element();
        im4.urmator();
        count++;
    }
    assert(count == 50);
    MD m4; // adaugam niste elemente in Md
    for (int i = 0; i < 100; i++) {
        m4.adauga(i, i);
        m4.adauga(i, i * (-2));
        m4.adauga(i, i * 2);
        m4.adauga(i, i / 2);
        m4.adauga(i, i / (-2));
    }
    //iteram peste MD si adaugam elementele intr-un vector
    vector<TElem> elemente;
    IteratorMD im5 = m4.iterator();
    while (im5.valid()) {
        TElem e = im5.element();
        elemente.push_back(e);
        im5.urmator();
    }
    assert(elemente.size() == m4.dim());
    for (unsigned int i = 0; i < elemente.size(); i++) {
        TElem lastElem = elemente.at(elemente.size() - i  - 1);
        vector<TValoare> v1;

    }
    cout<<"test iterator check\n";
}

void testQuantity() {//scopul e sa adaugam multe date
    //cout<<"intra quantity";
    MD m;
    for (int i = 10; i >= 1; i--) {
        for (int j = -30000; j < 30000; j = j + i) {
            m.adauga(j, j);
        }
    }
    vector<TValoare> v;
    //cout<<m.dim()<<endl;


    v=m.cauta(-30000);
    //cout<<v.size()<<endl;
    assert(m.dim() == 52094);
    assert(v.size() == 10);


    //assert(im.valid() == false);
    cout<<"test quantity check\n";
}


void testAllExtins() {
    testCreeaza();
    testAdauga();
    testQuantity();
    testIterator();
    testSterge();

}
