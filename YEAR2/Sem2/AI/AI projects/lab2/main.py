import greedyCommunity
import networkx as netx
import test


if __name__ == '__main__':

    test.testAll()


    opt = int(input('Enter option : '))
    if opt==1:
        dolphins = netx.read_gml('dolphins.gml', destringizer=int)
        x=greedyCommunity.girvan(dolphins)
        print('dolphins:')
        for k, v in x.items():
            print(k, v)
        print('\n')



    if opt==2:
        football = netx.read_gml('football.gml', destringizer=int)
        x=greedyCommunity.girvan(football)

        print('fotoball:')
        for k, v in x.items():
            print(k, v)
        print('\n')

    if opt==3:
        karate = netx.read_gml('karate.gml')
        x=greedyCommunity.girvan(karate)

        print('karate:')
        for k, v in x.items():
            print(k, v)
        print('\n')

    if opt==4:
        krebs = netx.read_gml('krebs.gml')
        x=greedyCommunity.girvan(krebs)

        print('krebs:')
        for k, v in x.items():
            print(k, v)
        print('\n')

    if opt==5:
        ex1 = netx.read_gml('example1.gml')
        x=greedyCommunity.girvan(ex1)

        print('ex1:')
        for k, v in x.items():
            print(k, v)
        print('\n')

    if opt==6:
        l1 = netx.read_gml('letters.gml')
        x=greedyCommunity.girvan(l1)

        print('letters1:')
        for k, v in x.items():
            print(k, v)
        print('\n')

    if opt==7:
        l2 = netx.read_gml('letters2.gml')
        x=greedyCommunity.girvan(l2)

        print('letters2:')
        for k, v in x.items():
            print(k, v)
        print('\n')

    if opt==8:
        n = netx.read_gml('names.gml')
        x=greedyCommunity.girvan(n)

        print('names:')
        for k, v in x.items():
            print(k, v)
        print('\n')

    if opt==9:
        f = netx.read_gml('fruits.gml')
        x=greedyCommunity.girvan(f)

        print('fruits:')
        for k, v in x.items():
            print(k, v)
        print('\n')

    if opt==10:
        rp = netx.read_gml('random.gml')
        x=greedyCommunity.girvan(rp)

        print('rappers:')
        for k, v in x.items():
            print(k, v)
        print('\n')









