#ifndef MONTICULO_H
#define MONTICULO_H
#include<vector>
#include<iostream>
#include<algorithm>
#include<AvHeap.h>

using namespace std;

template<class T>
class Monticulo{
    public:
        Monticulo();
        virtual ~Monticulo();
        int getTamanno();
        void insert(T pDato);
        std::vector<int>::iterator begin();
        std::vector<int>::iterator end();
        vector<T> getDatos();
        void print();
        T buscar(int pId);
        T get(int index);
        int getRelativePos(int pId);
        int getLeftSon(int pId);
        int getRightSon(int pId);
        int getParent(int pId);
        void activate();
        void sort();
        bool remove(int pId);
    protected:

    private:
        vector<T> datos;
};

template class Monticulo<int>;
#endif // MONTICULO_H
