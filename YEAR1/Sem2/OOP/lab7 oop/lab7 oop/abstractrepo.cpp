#include "abstractrepo.h"

size_t RepoOffer::getSize() {
	return this->all.size();
}

vector <Offer>& RepoOffer::getAll() {
	vector<Offer> offers;
	for (int i = 0; i < all.size(); i++) {
		offers.push_back(all[i]);
	}
	return offers;
}

/*vector<Offer>& RepoOffer::getRec() {
	return Wishlist;
}*/

void RepoOffer::store(const Offer& off) {
	int n = getSize();
	/*for (auto& med : all)
		if (med.second == off) {
			throw (Exception("Medicament deja existent!\n"));
		}*/
	this->all.insert({ n+1, off });
	
}

void RepoOffer::del(int pos) {
	int ok = 0, i = -1;
	/*for (auto& med : RepoMed) {
		i++;
		if (med.second.getId() == id) {
			medicament = med.second;
			RepoMed.erase(med.second.getId());
			ok = 1;
			break;
		}
	}
	if (!ok)
		throw (Exception("Medicament inexistent!\n"));*/
	all.erase(pos);
}

void RepoOffer::modify(const Offer& off, int pos) {
	int ok = 0, i = -1;
	all.at(i) = off;
	
}

/*void RepoMedicamente::addMedRec(const string& nume) {
	int ok = 0;
	for (auto& med : RepoMed)
		if (med.second.getDenumire() == nume) {
			ok = 1;
			Reteta.push_back(med.second);
			break;
		}
	if (!ok)
		throw (Exception("Medicament inexistent!\n"));
}*/

/*void RepoOffer::delRec() {
	Wishlist.clear();
}

void RepoOffer::addRandomOffers(int nr) {
	if (all.size() == 0)
		throw ((Exception)("Nu exista medicamente de adaugat in reteta!\n"));
	std::mt19937 mt{ std::random_device{}() };
	std::uniform_int_distribution<> dist(0, int(all.size()) - 1);

	for (int i = 0; i < nr; i++) {
		int val = dist(mt);
		Wishlist.push_back(all[val]);
	}
}*/