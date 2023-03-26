#pragma once
#include<string>
#include<iostream>

using std::string;
using std::cout;

class Offer {
    std::string name;
    std::string destination;
    std::string type;
    int price;
public:
    Offer() = default;
    Offer(const string n, const string d, const string t, int p) :name{ n }, destination{ d }, type{ t }, price{ p } {}

    //Offer(const Offer& ot) :name{ ot.name }, destination{ ot.destination }, type{ ot.type }, price{ ot.price } {
        //cout << "!!!!!!!!!!!!!!!!!!!!\n";
    //}
    string getName()const {
        return name;
    }
    string getDestination()const {
        return destination;
    }
    string getType()const {
        return type;
    }
    int getPrice()const noexcept {
        return price;
    }
};

bool cmpName(const Offer& o1, const Offer& o2);
bool cmpDestination(const Offer& o1, const Offer& o2);
//bool cmpType(const Offer& o1, const Offer& o2);
//bool cmpPrice(const Offer& o1, const Offer& o2);
