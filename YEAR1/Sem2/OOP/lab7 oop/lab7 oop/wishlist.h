#pragma once
#include "agency_offer.h"
#include <vector>
#include <algorithm>
#include <chrono>
#include <random>

using std::vector;

class Wishlist {
private:
	vector<Offer> wishlistOffers;
public:
	Wishlist() = default;

	void addOffertoWishlist(const Offer& off);

	void emptyWishlist();

	

	const vector<Offer>& getAllWishlistOffers();



};