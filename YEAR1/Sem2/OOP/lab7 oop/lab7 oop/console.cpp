#include "console.h"
#include "agency_offer.h"
#include "wishlist.h"
#include <iostream>
#include <string>
#include <string.h>
#include <vector>
#include "DynVectorTemplate.h"

using namespace std;

///vezi de modif daca trebe
using std::cout;
using std::cin;
using std::vector;

void Console::display(const vector<Offer>& offers) {
    cout << "Offers:\n";
    int pos = 0;
    for (int i = 0; i < offers.size(); i++) {
        pos++;
        Offer of = offers.at(i);
        cout << "Name: " << of.getName() << " Destination: " << of.getDestination() << " Type: " << of.getType() << " Price: " << of.getPrice() << ".\n";
    }
}

void Console::adaugaUI() {
    string name, destination, type, pr;
    //int ok = 0;
    int valid = 0, valid2 = 0;
    int price;
    cin.sync();
    cin.get();
    cout << "name: ";
    getline(cin, name);
    cout << "destination: ";
    getline(cin, destination);
    cout << "type: ";
    getline(cin, type);
    cout << "price: ";
    getline(cin, pr);
    if (pr.length() < 7) {
        for (int i = 0; i < pr.length(); i++) {
            if (!isdigit(pr[i])) {
                valid = 1;
                break;
            }
        }
        if (name.length() < 20 && destination.length() < 20 && type.length() < 20 && valid == 0) {
            valid2 = 1;
            price = stoi(pr);
            str.addOffer(name, destination, type, price);
        }
    }

    if (valid2 == 0) {
        cout << "invalid data!\n";
    }



}

void Console::start() {
    string choose;
    
    while (true) {
        cout << "1.basic\n2.batch\n:";
        cin >> choose;
        if (choose == "1") {
            while (true) {
                cout << "Menu:\n";
                cout << "1.Add\n2.display\n3.sort by feature\n4.filter by price\n5.delete\n6.modify\n7.add random offers\n0.exit\n";
                string cmd;
                cout << "command: ";
                cin >> cmd;
                string feature;

                if (cmd == "1") {
                    adaugaUI();
                }
                else if (cmd == "2") {
                    display(str.getAll());
                }
                else if (cmd == "3") {
                    cout << "1.sort by name\n2.sort by destination\n3.sort by type and price\n";
                    //string feature;
                    cin >> feature;
                    if (feature == "1") {
                        display(str.sortByName());
                    }
                    else if (feature == "2") {
                        display(str.sortByDestination());
                    }
                    else if (feature == "3") {
                        display(str.sortByTypePrice());
                    }
                    else {
                        cout << "invalid feature!\n";
                    }
                }
                else if (cmd == "4") {
                    cout << "1.filter by destination\n2.filter by price\n";
                    //string feature;
                    cin >> feature;
                    if (feature == "1") {
                        cout << "give destination: ";
                        string filter_destination;
                        cin >> filter_destination;
                        //vector<Offer> list=str.filterDestination(filter_destination)
                        display(str.filterDestination(filter_destination));
                    }
                    else if (feature == "2") {
                        cout << "give price: ";
                        int filter_price;
                        cin >> filter_price;
                        display(str.filterPrice(filter_price));
                    }
                }
                else if (cmd == "5") {
                    int pos;
                    cout << "give position: ";
                    cin >> pos;
                    str.deleteOffer(pos);


                }
                else if (cmd == "6") {
                    string name2, destination2, type2, pr2;
                    int ok2 = 0, valid22 = 0, valid222 = 0;
                    int price2, position;
                    cout << "new name: ";
                    cin >> name2;
                    cout << "new destination: ";
                    cin >> destination2;
                    cout << "new type: ";
                    cin >> type2;
                    cout << "new price: ";
                    cin >> pr2;
                    cout << "position: ";
                    cin >> position;

                    if (pr2.length() < 7) {
                        for (int i = 0; i < pr2.length(); i++) {
                            if (!isdigit(pr2[i])) {
                                valid22 = 1;
                                break;
                            }
                        }
                        if (name2.length() < 20 && destination2.length() < 20 && type2.length() < 20 && valid22 == 0) {
                            valid222 = 1;
                            price2 = stoi(pr2);
                            str.modifyOffer(name2, destination2, type2, price2, position);
                        }
                    }
                    if (valid222 == 0) {
                        cout << "invalid data!\n";
                    }
                }
                else if (cmd == "7") {
                    cout << "how many: ";
                    int howm;
                    cin >> howm;
                    //str.addRandom(howm);

                }
                else if (cmd == "8") {
                    cout << "offer number: ";
                    int no;
                    cin >> no;
                    //VectorDinamicT<Offer> vect = str.getAll();
                    vector<Offer>vect = str.getAll();
                    vector<Offer>wishList;
                    Offer off = vect.at(no - 1);
                    wsh.addOffertoWishlist(off);
                    wishList = wsh.getAllWishlistOffers();
                    //VectorDinamicT<Offer> vfinal = str.reversed_make_copy(wishList);
                    display(wishList);
                }
                else if (cmd == "9") {
                    //str.undo();
                    cout << "C";
                }
                else if (cmd == "0") {
                    return;
                }
                else {
                    cout << "invalid comand!\n";
                }
                /*catch (const Agency_Repo_Exception& ex) {
                    cout << ex <<'\n';
                }*/
                /*catch (const Agency_Repo_Exception& ex) {
                    cout << ex << '\n';
                }*/

            }

        }
        else if (choose == "2") {
            //BATCH MODE

            vector<string> comenzi;
            comenzi.push_back("adauga");
            comenzi.push_back("tipareste");
            comenzi.push_back("sterge");
            comenzi.push_back("modifica");
            
            while (true) {
                cout << "\nMeniu:\n";
                for (int i = 0; i < 3; i++)
                {
                    cout << comenzi.at(i) << " ";
                }
                cout << "\nPentru a parasi aplicatia, apasati 0\n:";
                string citesc;
                std::getline(std::cin, citesc);
                if (citesc == "0") {
                    return;
                }
                constexpr char space_char = ' ';
                vector<string> words{};

                std::stringstream sstream(citesc);
                string word;
                while (std::getline(sstream, word, space_char)) {
                    word.erase(std::remove_if(word.begin(), word.end(), ispunct), word.end());
                    words.push_back(word);
                }
                int dim = 0;
                /// <summary>
                /// 
                /// </summary>
                while (dim < words.size()) {
                    auto it = std::find(comenzi.begin(), comenzi.end(), words.at(dim));
                    if (it != comenzi.end()) {
                        const int d1 = dim + 1, d2 = dim + 2, d3 = dim + 3, d4 = dim + 4;
                        if (words.at(dim) == "adauga") {

                            if (d4 < words.size()) {

                                const string name = words.at(d1);
                                const string destination = words.at(d2);
                                const string type = words.at(d3);
                                const int price = stoi(words.at(d4));
                                str.addOffer(name, destination, type, price);
                            }
                        }
                        if (words.at(dim) == "tipareste") {
                            display(str.getAll());
                            //return;
                        }
                        if (words.at(dim) == "sterge") {
                            if (d1 < words.size()) {
                                const int pos = stoi(words.at(d1));
                                str.deleteOffer(pos);
                            }
                        }

                    }
                    dim++;
                }
            }
            return;
        }
        else {
            cout << "invalid input\n";
        }
    
    }
    
}

