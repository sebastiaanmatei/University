import networkx as netx

def girvan(network):

    # nr componente conexe
    init = netx.number_connected_components(network)

    # daca inca exista nod care nu e intr-o comunitate
    while netx.number_connected_components(network) <= init:

        # calculeaza modularitatea pentru toate muchiile
        btn = netx.edge_betweenness_centrality(network)

        # sterge muchia cu modularitatea maxima
        mx = max(btn, key = btn.get)
        network.remove_edge(*mx)

    # construire dictionar de forma (nod, ccomunitatea lui)
    communities = {}
    maximum = 0
    for i, c in enumerate(netx.connected_components(network)):
        for node in c:
            communities[node] = i + 1
            if (maximum < i + 1) :
                maximum = i + 1

    # afisare comunitati
    # print('number of comunities', maximum)

    return communities








