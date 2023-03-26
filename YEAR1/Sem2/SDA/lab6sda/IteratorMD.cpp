#include "IteratorMD.h"
#include "MD.h"
#define NIL -1
using namespace std;

IteratorMD::IteratorMD(const MD& _md): md(_md) {
    curent =0;
    //deplasare();
}

TElem IteratorMD::element() const{

    return md.e[curent];

}

bool IteratorMD::valid() const {
    /* de adaugat */
    return (curent<md.dim());
}

void IteratorMD::urmator() {
    curent++;
    deplasare();
}

void IteratorMD::prim() {
    curent=0;
    deplasare();
}

void IteratorMD::deplasare(){
    while(valid() && (md.e[curent].key == NIL))
        curent++;
}

