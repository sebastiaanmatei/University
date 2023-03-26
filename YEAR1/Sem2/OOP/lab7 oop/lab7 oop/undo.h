/*#pragma once
#include "agency_offer.h"
#include "agency_repo.h"
#include "abstractrepo.h"
class UndoAction
{
public:
	virtual void doUndo() = 0;
	virtual ~UndoAction() = default;
};

class UndoAdd : public UndoAction
{
private:
	Offer addedOffer;
	Agency_Repo& repo;
public:
	UndoAdd(Agency_Repo& rep, const Offer& o) : repo{ rep }{}
	void doUndo() override {
		int n = repo.getAll().size();
		repo.del(n);
	}

};

class UndoUpdate : public UndoAction
{
private:
	Offer modifiedOffer;
	Agency_Repo& repo;
public:
	UndoUpdate(const Offer& m, Agency_Repo& rep) : repo{ rep }, modifiedOffer{ m }{}
	void doUndo() override;

};

class UndoDelete : public UndoAction
{
private:
	Offer deletedOffer;
	Agency_Repo& repo;
public:
	UndoDelete(Agency_Repo& rep, const Offer& m) : repo{ rep }, deletedOffer{ m }{}
	void doUndo() override {
		repo.store(deletedOffer);
	}


};*/