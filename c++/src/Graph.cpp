#include <Graph.h>

Graph::Graph(avl_array<int,int,std::uint_least32_t,80000,true> *pAvl, Monticulo<int> *pMonticulo, CJMessenger *pMessenger){
    int relPos = 0;
    int weight = 0;
    int parent = 0;
    int leftSon = 0;
    int rightSon = 0;
    for(int index = 0; index< pMonticulo->getTamanno();index++){
        relPos = pAvl->getRelativePos(index);
        weight = std::abs(relPos-index);
        parent = pAvl->getParent(index);
        leftSon = pAvl->getLeftSon(index);
        rightSon = pAvl->getRightSon(index);
        pMessenger->sendData(&index);
        pMessenger->sendData(&parent);
        pMessenger->sendData(&leftSon);
        pMessenger->sendData(&rightSon);
        pMessenger->sendData(&weight);
    }
}

void Graph::sendMSTToJava(int pOrigin){
   /* Container contatiner(0,0,0,0,0);
    queue.enqueue(0, true, pOrigin, true, 0);
    int parent = -1;
    int leftSon = -1;
    int rightSon = -1;
    int stopTime = monticulo->getTamanno()*2;
    for(int condition = 0; condition<stopTime; condition++){
        contatiner = queue.dequeue();
        actualDataStructure = contatiner.isActualFromAvl();
        if(bothCheckedElements[actualDataStructure][contatiner.getActual()]){
            break;
        }
        bothCheckedElements[actualDataStructure][contatiner.getActual()] = true;
        if(actualDataStructure){
            parent = avl->getParent(contatiner.getActual());
            leftSon = avl->getLeftSon(contatiner.getActual());
            rightSon = avl->getRightSon(contatiner.getActual());
        }else{
            parent = monticulo->getParent(contatiner.getActual());
            leftSon = monticulo->getLeftSon(contatiner.getActual());
            rightSon = monticulo->getRightSon(contatiner.getActual());
        }
        if(parent != -1){
            if(bothCheckedElements[actualDataStructure][parent]){}
            else{
                queue.enqueue(contatiner.getActual(),contatiner.isActualFromAvl(),parent,actualDataStructure,1);
                std::cout<<"Parent: "<<parent<<std::endl;
            }
        }
        if(leftSon != -1){
            if(bothCheckedElements[actualDataStructure][leftSon]){}
            else{
                queue.enqueue(contatiner.getActual(),contatiner.isActualFromAvl(),leftSon,actualDataStructure,1);
                std::cout<<"LeftSon: "<<leftSon<<std::endl;
            }
        }
        if(rightSon != -1){
            if(bothCheckedElements[actualDataStructure][rightSon]){}
            else{
                queue.enqueue(contatiner.getActual(),contatiner.isActualFromAvl(),rightSon,actualDataStructure,1);
                std::cout<<"RightSon: "<<rightSon<<std::endl;
            }
        }
        if(!bothCheckedElements[!actualDataStructure][contatiner.getActual()]){
            queue.enqueue(contatiner.getActual(),contatiner.isActualFromAvl(),contatiner.getActual(),!actualDataStructure,weights[contatiner.getActual()]);
            std::cout<<(actualDataStructure?"Monticulo: ":"Avl: ")<<contatiner.getActual()<<std::endl;
        }
        std::cout<<"Soy "<<contatiner.getActual()<<(contatiner.isActualFromAvl()?" Avl":" Monticulo")<<" y vine de "<<(contatiner.isPreviousFromAvl()?"Avl ":"Monticulo ")<<contatiner.getPrevious()<<" con un peso de "<<contatiner.getWeight()<<std::endl;
        std::cout<<contatiner.getActual()<<std::endl;
    }
    */
}

Graph::~Graph(){
    //dtor
}
