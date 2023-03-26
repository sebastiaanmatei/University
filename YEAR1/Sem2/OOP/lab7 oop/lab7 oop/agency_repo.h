#pragma once
#include "agency_offer.h"
#include "DynVectorTemplate.h"
#include <vector>
#include <string>
#include <ostream>

using std::vector;
using std::string;
using std::ostream;

class Agency_Repo {

    //VectorDinamicT<Offer> all;
    vector<Offer>all;
    

public:

    Agency_Repo() = default;

    bool exist(const Offer& o) const;

    Agency_Repo(const Agency_Repo& ot) = delete;

    virtual void store(const Offer& o);

    const Offer& find(string name, string destination, string type) const;

    const vector<Offer>& getAll() const noexcept;

    void modify(const Offer& of, int pos);

    void del(int pos);

    void addRandomOffers(int howMany);
};

class Agency_Repo_Exception {

    string msg;
public:

    Agency_Repo_Exception(string m) :msg{ m } {}

    friend ostream& operator<<(ostream& out, const Agency_Repo_Exception& ex);

};

ostream& operator<<(ostream& out, const Agency_Repo_Exception& ex);

class OfferFileRepository : public Agency_Repo {

private:
    string filename;

    void loadFromFile();

    void SaveToFile();
public:
    void store(const Offer& off) override;

    OfferFileRepository(string fname) : Agency_Repo(), filename{ fname }{
        loadFromFile();
    }
};


void testRepo();
