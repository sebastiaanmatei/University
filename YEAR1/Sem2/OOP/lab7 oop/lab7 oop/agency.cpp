#include "agency.h"
#include "abstractrepo.h"
#include <functional>
#include <algorithm>
#include <iterator>
#include <random>
#include <assert.h>
#include "DynVectorTemplate.h"
#include "undo.h"

//tre modif

/*vector <Offer> Agency::make_copy(const VectorDinamicT<Offer>& all_offers) {
    vector<Offer>off;
    for (int i = 0; i < all_offers.size(); i++) {
        off.push_back(all_offers.get(i));
    }
    /*for (auto Offer : all_offers) {
        off.push_back(all_offers.get(i));
    }
    return off;
}

VectorDinamicT <Offer> Agency::reversed_make_copy(const vector<Offer>& all_offers) {
    VectorDinamicT<Offer>off;
    for (int i = 0; i < all_offers.size(); i++) {
        off.add(all_offers[i]);
    }
    return off;
}*/


vector <Offer> Agency::generalSort(bool (*smaller)(const Offer&, const Offer&)) {
    vector <Offer> v;
    /*v = make_copy(repo.getAll());
    for (size_t i = 0; i < v.size(); i++) {
        for (size_t j = i + 1; j < v.size(); j++) {
            if (!smaller(v[i], v[j])) {
                Offer aux = v[i];
                v[i] = v[j];
                v[j] = aux;
            }

        }
    }*/
    //v = make_copy(repo.getAll());
    v = repo.getAll();
    std::sort(v.begin(), v.end(), smaller);
    //VectorDinamicT<Offer>sorted_offers;
    //sorted_offers = reversed_make_copy(v);
    return v;
    
}


void Agency::addOffer(const string& name, const string& destination, const string& type, int price) {
    Offer off{ name, destination, type, price };
    val.validate(off);
    repo.store(off);
    //undoAct.push_back(std::make_unique<UndoAdd>(repo, off));
}


void Agency::modifyOffer(const string& name, const string& destination, const string& type, int price, int pos) {
    Offer off{ name, destination, type, price };
    val.validate(off);
    //C:\oop labs\lab7 oop\x64\Debug\lab7 oop.exe
    repo.modify(off, pos);
}

void Agency::deleteOffer(int pos) {
    if (!(pos > getAll().size() || pos <= 0))
    {
        Offer off = getAll()[pos - 1];
        cout << off.getName();
        repo.del(pos);
        //undoAct.push_back(std::make_unique<UndoDelete>(repo, off));
    }
    else
        cout << "Pozitie invalida!!!!!!";
}

vector <Offer> Agency::sortByName() {

    return generalSort(cmpName);
}


/*void Agency::addRandom(int howMany) {
    repo.addRandomOffers(howMany);
}*/

/*void Agency::undo() {
    if (!undoAct.empty()) {
        undoAct.back()->doUndo();
        undoAct.pop_back();

    }
    
}*/

vector <Offer> Agency::sortByDestination() {
    ///auto copyAll = repo.getAll();
    //std::sort(copyAll.begin(), copyAll.end(), cmpDestination);
    //return copyAll
    return generalSort(cmpDestination);
}





vector <Offer> Agency::sortByTypePrice() {
    return generalSort([](const Offer& of1, const Offer& of2) {
        if (of1.getType() == of2.getType()) {
            return of1.getPrice() < of2.getPrice();
        }
        return of1.getType() < of2.getType();

        });
}



/*VectorDinamicT <Offer> Agency::filter(function<bool(const Offer&)> fct) {
    VectorDinamicT<Offer> rez;
    VectorDinamicT<Offer> offers = repo.getAll();
    for (const auto& of : offers) {
        if (fct(of)) {
            rez.add(of);
        }
    }
    //vector<Offer>v1 = make_copy(repo.getAll());
    //vector<Offer>v2;
    return rez;
}*/


vector <Offer> Agency::filterPrice(int price) {
    /*return filter([price](const Offer& of) {
        return of.getPrice() == price;
        });*/
    //vector<Offer> v1 = make_copy(repo.getAll());
    vector<Offer>v1 = repo.getAll();
    vector<Offer> v2;
    copy_if(v1.begin(), v1.end(), back_inserter(v2), [&](const Offer& off) { return off.getPrice() == price; });
    //VectorDinamicT<Offer>sorted_offers;
    //sorted_offers = reversed_make_copy(v2);
    return v2;
}

vector <Offer> Agency::filterDestination(string destination) {
    /*return filter([destination](const Offer& of) {
        return of.getDestination() == destination;
        });*/
    //vector<Offer> v1 = make_copy(repo.getAll());
    vector<Offer>v1 = repo.getAll();
    vector<Offer> v2;
    copy_if(v1.begin(), v1.end(), back_inserter(v2), [&](const Offer& off) { return off.getDestination() == destination; });
    //VectorDinamicT<Offer>sorted_offers;
    //sorted_offers = reversed_make_copy(v2);
    return v2;

}





/*void testAddFilterSort() {
    NewRepo repo;
    OfferValidator val;
    Agency str{ repo,val };
    str.addOffer("a", "a", "a", 10);
    assert(str.getAll().size() == 1);
    str.addOffer("h", "h", "h", 1);
    //str.addOffer("a", "a", "a", 10);
    str.addOffer("g", "g", "t", 20000);
    assert(str.filterDestination("a").size() == 1);
    //cout << str.filterPrice(1).size();
    assert(str.filterPrice(1).size() == 1);
    str.addOffer("z", "z", "g", 100);
    str.addOffer("b", "x", "a", 120);
    str.addOffer("c", "f", "j", 14500);
    auto firstP = str.sortByName()[0];
    assert(firstP.getName() == "a");
    firstP = str.sortByDestination()[0];
    assert(firstP.getName() == "a");
    firstP = str.sortByTypePrice()[0];
    assert(firstP.getName() == "a");
    str.deleteOffer(1);
    //str.undo();
    str.modifyOffer("z", "z", "z", 100, 1);
    str.addOffer("h", "h", "h", 1);
    //str.addRandom(5);
    //str.undo();
    assert(repo.getAll().size() > 0);


}*/