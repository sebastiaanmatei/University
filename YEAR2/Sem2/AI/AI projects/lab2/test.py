import os
import networkx as netx
import numpy as np
import greedyCommunity
from matplotlib import pyplot as plt


def plotNetwork(G, communities):
    np.random.seed(123)  # to freeze the graph's view (networks uses a random view)
    pos = netx.spring_layout(G)  # compute graph layout
    plt.figure(figsize=(4, 4))  # image is 8 x 8 inches
    netx.draw_networkx_nodes(G, pos, node_size=50, cmap=plt.cm.RdYlBu, node_color=list(communities.values()))
    netx.draw_networkx_edges(G, pos, alpha=0.3)
    netx.draw_networkx_labels(G, pos)

    plt.show()


def testd():
    crtDir = os.getcwd()
    filePath = os.path.join(crtDir, 'real', 'dolphins', 'dolphins.gml')
    Graph = netx.read_gml(filePath, label='id')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))


def testf():
    crtDir = os.getcwd()
    filePath = os.path.join(crtDir, 'real', 'football', 'football.gml')
    Graph = netx.read_gml(filePath, label='id')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))


def testkr():
    crtDir = os.getcwd()
    filePath = os.path.join(crtDir, 'real', 'karate', 'karate.gml')
    Graph = netx.read_gml(filePath, label='id')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))


def testk():
    crtDir = os.getcwd()
    filePath = os.path.join(crtDir, 'real', 'krebs', 'krebs.gml')
    Graph = netx.read_gml(filePath, label='id')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))

def testmio():
    Graph=netx.read_gml('example1.gml')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))

def testlett():
    Graph=netx.read_gml('letters.gml')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))

def testlett2():
    Graph=netx.read_gml('letters2.gml')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))

def testnames():
    Graph=netx.read_gml('names.gml')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))

def testfruits():
    Graph=netx.read_gml('fruits.gml')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))

def testrand():
    Graph=netx.read_gml('random.gml')
    G_copy = Graph.copy()
    plotNetwork(G_copy, greedyCommunity.girvan(Graph))

def testAll():
    testd()
    testf()
    testkr()
    testk()
    testmio()
    testlett()
    testlett2()
    testnames()
    testfruits()
    testrand()