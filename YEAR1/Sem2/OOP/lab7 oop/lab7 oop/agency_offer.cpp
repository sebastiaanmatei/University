#include "agency_offer.h"

bool cmpName(const Offer& o1, const Offer& o2) {
    return o1.getName() < o2.getName();
}

bool cmpDestination(const Offer& o1, const Offer& o2) {
    return o1.getDestination() < o2.getDestination();
}


