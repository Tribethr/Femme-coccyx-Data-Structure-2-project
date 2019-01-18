#ifndef GRAPH_H
#define GRAPH_H
#include<Monticulo.h>
#include<avl_array.h>
#include<cstddef>
#include<iostream>
#include<CJMessenger.h>
#include<PriorityQueue.h>
class Graph{
    public:
        Graph(avl_array<int,int,std::uint_least32_t,80000,true> *avl, Monticulo<int> *monticulo, CJMessenger *pMessenger);
        virtual ~Graph();
        void sendMSTToJava(int pOrigin);

    private:
};
#endif // GRAPH_H
