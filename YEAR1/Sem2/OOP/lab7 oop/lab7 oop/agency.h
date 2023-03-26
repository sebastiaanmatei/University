#pragma once

#include "agency_offer.h"
#include "agency_repo.h"
#include "abstractrepo.h"
#include "undo.h"
#include <string>
#include <vector>
#include "DynVectorTemplate.h"
#include <functional>
#include <memory>
#include "validator.h"

using std::vector;
using std::function;
using std::unique_ptr;

class Agency {


    //Agency_Repo& repo;

    NewRepo& repo;

    OfferValidator& val;

    vector <Offer> generalSort(bool (*smaller)(const Offer&, const Offer&));

    vector <Offer> filter(function <bool(const Offer&)> fct);

    //unordered_map <int, Offer> generalSort(bool (*smaller)(const Offer&, const Offer&));

    //vector <Offer> filter(function <bool(const Offer&)> fct);

    //tinem pointeri la actiuneundo pt ca tr apel polimorfic
    //punem unique_ptr pentru ca avem responsabilitatea de a elibera memoria pentru
    
    

public:
    Agency() = default;
    Agency(NewRepo& repo, OfferValidator& val) :repo{ repo }, val{ val }{}

    vector <Offer> make_copy(const VectorDinamicT <Offer>& all_offers);
    VectorDinamicT <Offer>reversed_make_copy(const vector<Offer>& all_offers);

    Agency(const Agency& ot) = delete;

    //vector<unique_ptr<UndoAction>> undoAct;


    const vector<Offer>& getAll() noexcept {
        return repo.getAll();
    }

    void addOffer(const string& name, const string& destination, const string& type, int price);

    void modifyOffer(const string& name, const string& destination, const string& type, int price, int pos);

    void deleteOffer(int pos);

    vector <Offer> sortByName();

    vector <Offer> sortByDestination();

    vector <Offer> sortByTypePrice();

    vector <Offer> filterDestination(string destination);

    vector <Offer> filterPrice(int price);

    const Offer& finding(const string& destination);

    //void addRandom(int howMany);

    //void undo();

};
void testAddFilterSort();