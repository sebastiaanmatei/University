#pragma once
#include "agency_offer.h"
#include <unordered_map>
#include <random>
#include <vector>
using namespace std;


class RepoOffer {

	unordered_map <int, Offer> all;
	vector <Offer> Wishlist;


public:
	RepoOffer() = default;

	size_t getSize();
	vector <Offer>& getAll();
	vector <Offer>& getRec();
	void store(const Offer& off);
	void del(int pos);
	void modify(const Offer& off, int pos);
	//virtual void addMedRec(const string& nume);
	void delRec();
	//void addRandomOffers(int nr);
	//virtual RepoOffer() = default;

	
};

class Exception {

public:
	Exception(const string& msg) : msg_(msg) {}
	~Exception() {}

	string getMessage() const { return(msg_); }
private:
	string msg_;
};

class NewRepo : public RepoOffer {
private:
	int random;
public:
	int getRandom() {
		std::mt19937 mt{ std::random_device{}() };
		std::uniform_int_distribution<> dist(0, 1);
		this->random = dist(mt);
		return random;
	}
	size_t getSize() {
		if (getRandom() == 0)
			throw Exception("Error!\n");
		return RepoOffer::getSize();
	}
	vector <Offer>& getAll(){
		if (getRandom() == 0)
			throw Exception("Error!\n");
		return RepoOffer::getAll();
	}
	vector <Offer>& getRec() {
		if (getRandom() == 0)
			throw Exception("Error!\n");
		return RepoOffer::getRec();
	}
	void store(const Offer& off) {
		if (getRandom() == 0)
			throw Exception("Error!\n");
		RepoOffer::store(off);
	}
	void del(int pos) {
		if (getRandom() == 0)
			throw Exception("Error!\n");
		RepoOffer::del(pos);
	}
	void modify(const Offer& off, int pos){
		if (getRandom() == 0)
			throw Exception("Error!\n");
		RepoOffer::modify(off, pos);
	}
	/*void addMedRec(const string& nume) override {
		if (getRandom() == 0)
			throw Exception("Error!\n");
		RepoMedicamente::addMedRec(nume);
	}
	*/
	void delRec() {
		if (getRandom() == 0)
			throw Exception("Error!\n");
		RepoOffer::delRec();
	}
	/*void addRandomOffers(int nr) {
		if (getRandom() == 0)
			throw Exception("Error!\n");
		RepoOffer::addRandomOffers(nr);
	}*/
};