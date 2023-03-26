#include "agency_repo.h"
#include <iostream>
#include <sstream>
#include <string.h>
#include <cstring>
#include <assert.h>
#include <algorithm>
#include <chrono>
#include <functional>
#include <iterator>
#include <vector>
#include <random>
#include <vector>
#include <fstream>
using namespace std;

using std::ostream;
using std::stringstream;


/// trebe modif

void Agency_Repo::store(const Offer& of)
{
    if (!exist(of)) {
        //throw Agency_Repo_Exception("Offer already exists!");
        all.push_back(of);
    }
    

}


void Agency_Repo::addRandomOffers(int howMany) {
    //VectorDinamicT<Offer>x;
    vector<Offer>x;
    x.push_back({ "a", "Roma", "condo", 1000});
    x.push_back({ "b", "Madrid", "all-inclusive", 5000 });
    x.push_back({ "c", "Zagreb", "BB", 4500 });
    x.push_back({ "d", "Berlin", "suite", 6000 });
    x.push_back({ "e", "Florenza", "condo", 5000 });
    x.push_back({ "f", "Budapest", "complete pension", 3700 });
    x.push_back({ "g", "Stockholm", "BB", 2500 });


    mt19937 mt{ std::random_device{}() };
    uniform_int_distribution<> dist(0, int(x.size()) - 1);

    
    for (int i = 0; i < howMany; i++) {
        int val = dist(mt);
        store(x[val]);
    }

    
}


void Agency_Repo::modify(const Offer& of, int pos) {
   
    all.at(pos - 1)=of;
    
}

void Agency_Repo::del(int pos) {
    Offer off = all.at(pos - 1);
    all.erase(all.begin()+pos-1);
    
}

bool Agency_Repo::exist(const Offer& of)const {
    try {
        find((of.getName()), of.getDestination(), of.getType());
        return true;
    }
    catch (Agency_Repo_Exception&) {
        return false;}
}

const Offer& Agency_Repo::find(string name, string destination, string type) const {
    for (int i = 0; i < all.size(); i++) {
        const Offer& of = all[i];
        if (of.getName() == name && of.getDestination() == destination && of.getType() == type) {
            return of;
        }

    }
    //vector <Offer> v = getAll();
    //auto it = find_if(all.begin(), all.end(), [=](const Offer& off) { return off.getName() == name && off.getDestination() == destination && off.getType() == type; } );
    throw Agency_Repo_Exception("there is no offer with name" + name + "destination" + destination + "type" + type);}




const vector<Offer>& Agency_Repo::getAll() const noexcept {
    return all;
}

/*ostream& operator<<(ostream& out, const Agency_Repo_Exception& ex) {
    out<<ex.msg;
    return out;
}*/

void OfferFileRepository::loadFromFile() {
    ifstream OfferFile(this->filename);
    string line;
    while (getline(OfferFile, line)) 
    {
        string name, destination, type;
        int price;

        stringstream linestream(line);
        string current_item;
        int item_no = 0;

        while (getline(linestream, current_item, ',')) {
            if (item_no == 0)name = current_item;
            if (item_no == 1)destination = current_item;
            if (item_no == 2)type = current_item;
            if (item_no == 3)price = stod(current_item);

            item_no++;


        }
        Offer off{ name, destination, type, price };
        Agency_Repo::store(off);

    }
    OfferFile.close();
}

void OfferFileRepository::SaveToFile() {
    ofstream OfferOutput(this->filename);
    for (auto& offer : getAll()) {
        OfferOutput << offer.getName() << "," << offer.getDestination() << "," << offer.getType() << "," << offer.getPrice() << endl;

    }
    OfferOutput.close();
}

void OfferFileRepository::store(const Offer& off) {
    Agency_Repo::store(off);
    SaveToFile();
}


void testRepo() {
    Agency_Repo repo;
    repo.store(Offer({ "a", "a", "a", 5 }));
    assert(repo.getAll().size() == 1);
    assert(repo.find("a", "a", "a").getName() == "a");

    repo.store(Offer({ "b", "b", "b", 5 }));
    assert(repo.getAll().size() == 2);

    auto p = repo.find("a", "a", "a");
    assert(p.getName() == "a");
    assert(p.getDestination() == "a");
    
    Offer of("x", "x", "x", 20);
    repo.store(of);
    assert(repo.exist(of) == true);
    //repo.modify("a", "b", "c", 100, 10000);jasdlnsadlnsadlksandlnasdlnlaskdkallnadsl
    //repo.del(10000);


}




