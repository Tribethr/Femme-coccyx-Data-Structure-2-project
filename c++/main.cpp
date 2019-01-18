#include<avl_array.h>
#include<Monticulo.h>
#include<Graph.h>
#include<PriorityQueue.h>
#include<CJMessenger.h>
#include<AvHeap.h>
#include<iostream>

int main(){
    avl_array<int,int,std::uint_least32_t,80000,true> avl;
    Monticulo<int> monticulo;
    int dataReciever = 0;
    CJMessenger messenger;
    messenger.waitForConection();
    messenger.waitForData(&dataReciever);
    std::cout<<"Recibi: "<<dataReciever<<std::endl;
    for(int index = 0; index<dataReciever; index++){
        avl.insert(index,index);
        monticulo.insert(index);
    }
    monticulo.activate();
    Graph graph(&avl,&monticulo,&messenger);
    //std::cout<<"Cantidad de nodos creados: "<<monticulo.getTamanno()<<std::endl;
    return 0;
}
