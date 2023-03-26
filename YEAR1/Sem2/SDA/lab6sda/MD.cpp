#include "MD.h"
#include "IteratorMD.h"
#include <exception>
#include <vector>
#include <math.h>
#include <iostream>
#define NIL -INT16_MAX
using namespace std;

//typedef struct structure structure;

MD::MD() {
    /*
     * complexitate generala: teta(m)
     */
    m = MAX;
    for(int i=0;i<m;i++){
        e[i].key = NIL;
    }
}


int MD::hashCode(TCheie c) const {
    /*
     * complexitate generala: teta(1)
     */
    return abs(c % m);
}

int MD::d(TCheie c, int i) const {
    return int(hashCode(c)+ (i+pow(i, 2))/2) % m;
}


void MD::adauga(TCheie c, TValoare v) {
    /*
     * complexitate generala: o(n)
     */

    vector<TValoare> values=cauta(c);
    //cout<<hashCode(-9)<<endl;
    if(values.empty()){//daca nu exista cheia


        if(e[hashCode(c)].key==NIL){

            e[hashCode(c)].key=c;
            e[hashCode(c)].array.push_back(v);

        }else{


            bool found=false;
            int i=0;
            while(!found && i<m){
                int position=d(c, i);
                if(e[position].key==NIL){
                    e[position].key=c;
                    e[position].array.push_back(v);
                    found=true;


                    break;
                }else{
                    i++;
                }
            }
        }

    }else {

        vector<TValoare> values;
        int pos = cautaindex(c);
        //values.push_back(v);
        e[pos].array.push_back(v);

    }



}


bool MD::sterge(TCheie c, TValoare v) {
    /*
     * complexitate generala: o(n)
     * */

    vector<TValoare> values;
    values=cauta(c);
    int position= cautaindex(c);

    if(values.size()==0){
        //cout<<"nu e cheie\n"<<c<<endl;
        return false;
    }else{
        //cout<<"E CHEie\n"<<c<<endl;
        if(values.size()==1 && values.at(0)==v){
            //cout<<v<<endl;
            e[position].key=NIL;
            e[position].array.clear();
            return true;
        }else if(values.size()>1){
            //cout<<"bla";
            for(int i=0;i<values.size();i++){
                if(values.at(i)==v){
                    e[position].array.erase(e[position].array.begin()+i-1);
                }
            }
            return true;
        }else{
            return false;
        }


    }

}

int MD::cautaindex(TCheie c) const {
    /*
     * complexitate generala: o(n)
     */
    bool found=false;
    int pos=-1;
    int i=0;
    while(!found && i<m){

        int position=d(c, i);

        if(e[position].key==c){
            return position;

        }else{
            i++;
        }
    }
    return -1;


}


vector<TValoare> MD::cauta(TCheie c) const {
    /*
     * complexitate generala: o(n)
     */
    bool found=false;
    int pos=-1;
    int i=0;
    while(!found && i<m){

        int position=d(c, i);

        if(e[position].key==c){
            pos=position;
            found=true;
            break;

        }else{
            i++;
        }
    }
    if(pos!=-1){

        return e[pos].array;
    }else{

        return {};
    }


}


int MD::dim() const {
    /*
     * complexitate generala: teta(n)
     */
    int frecv[100000], count=0;
    for(int i=0;i<m;i++){
        count+=e[i].array.size();
    }

    return count;
}


bool MD::vid() const {
    /*
     * complexitate generala: teta(1)
     */
    return dim()==0;
}

IteratorMD MD::iterator() const {
    return IteratorMD(*this);
}


MD::~MD() {
    /* de adaugat */
}

