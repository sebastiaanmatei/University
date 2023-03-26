#pragma once
#include "agency.h"
#include "agency_offer.h"
#include "wishlist.h"
#include <vector>
class Console {

    Agency& str;
    Wishlist& wsh;

    void adaugaUI();

    void display(const vector<Offer>& offers);

public:

    Console(Agency& str, Wishlist& wsh) :str{ str }, wsh { wsh }{}

    Console(const Console& ot) = delete;

    void start();
};