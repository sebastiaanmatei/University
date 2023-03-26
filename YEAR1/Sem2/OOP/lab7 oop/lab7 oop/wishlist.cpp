#include "wishlist.h"
using std::shuffle;

void Wishlist::addOffertoWishlist(const Offer& off) {
	this->wishlistOffers.push_back(off);
}

void Wishlist::emptyWishlist() {
	this->wishlistOffers.clear();
}

const vector<Offer>& Wishlist::getAllWishlistOffers() {
	return this->wishlistOffers;
}

