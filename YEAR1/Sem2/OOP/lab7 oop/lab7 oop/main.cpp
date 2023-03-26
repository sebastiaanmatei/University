#include <iostream>
#include "validator.h"
#include "agency.h"
#include "agency_repo.h"
#include "abstractrepo.h"
#include "agency_offer.h"
#include "DynVectorTemplate.h"
#include <stdlib.h>
#include "console.h"
#include "wishlist.h"





int main()
{
    //testAddFilterSort();
    //testRepo();
    //testValidator();
    //Agency_Repo repo;
    //OfferFileRepository repoFile{ "offers.txt" };
    NewRepo repo;
    OfferValidator val;
    Agency str( repo, val );
    //Agency str{ repo, val };
    Wishlist wsh;
    str.addOffer("a", "Ibiza", "bb", 5000);
    str.addOffer("b", "Malaga", "all-inclusive", 3000);
    str.addOffer("c", "Hawaii", "pension", 10000);
    str.addOffer("d", "Miami", "condo", 4000);

    Console ui{ str, wsh };
    ui.start();
}