#include <Monticulo.h>


template<class T>
Monticulo<T>::Monticulo(){

}
template<class T>
Monticulo<T>::~Monticulo(){

}
template<class T>
int Monticulo<T>::getTamanno(){
    return datos.size();
}

template<class T>
vector<T> Monticulo<T>::getDatos(){
    return datos;
}

template<class T>
void Monticulo<T>::activate(){
    std::make_heap(datos.end(), datos.begin());
}

template <class T>
std::vector<int>::iterator Monticulo<T>::begin(){
    return datos.begin();
}

template<class T>
std::vector<int>::iterator Monticulo<T>::end(){
    return datos.end();
}

template<class T>
T Monticulo<T>::get(int index){
    return datos[index];
}
template<class T>
int Monticulo<T>::getRelativePos(int pId){
    for(unsigned int i = 0; i<datos.size(); i++){
        if(datos[i] == pId){
            return i;
        }
    }
    return -1;
}

template<class T>
void Monticulo<T>::sort(){

}

template<class T>
T Monticulo<T>::buscar(int pId){
    for(unsigned int i = 1; i<datos.size(); i++){
        if(datos[i] == pId){
            return datos[i];
        }
    }
    return NULL;
}

template<class T>
bool Monticulo<T>::remove(int pId){
    for(unsigned int i = 1; i<datos.size(); i++){
        if(datos[i] == pId){
            datos.erase(datos.begin()+i);
            return true;
        }
    }
    return false;
}

template<class T>
void Monticulo<T>::insert(T pDato){
    datos.push_back(pDato);
}

template<class T>
int Monticulo<T>::getLeftSon(int pId){
    int value = 2*(pId+1);
    if(value>datos.size()){
        return -1;
    }
    return datos[value-1];
}
template<class T>
int Monticulo<T>::getRightSon(int pId){
    int value = 2*(pId+1)+1;
    if(value>datos.size()){
        return -1;
    }
    return datos[value-1];
}
template<class T>
int Monticulo<T>::getParent(int pId){
    if(pId == 0){
        return -1;
    }
    return datos[(++pId/2)-1];
}

template<class T>
void Monticulo<T>::print(){
    string retorno = "[";
    for(unsigned int i = 1; i<datos.size(); i++){
        retorno = retorno + to_string(datos[i])+",";
    }
    if(datos.size() > 1){
        retorno.pop_back();
    }
    cout << retorno << "]"<<endl;
}
