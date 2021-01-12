package buildatree;

public class Player {
    /***
     * Clase para llevar el control de los arboles de los jugadores, funciona como centro de insercion
     *  y eliminación de árboles
     */
    int indicador;//indica a cual jugador pertenece el arbol

    String arbol;
    int size;

    AVLTree avl;

    Btree arbolB;

    SplayTree arbolSplay;




    public void setArbol(String data){
        arbol = data;
    }

    public void crearArbol(){//crea los arboles del jugador
        if (arbol.equals("AVL")){
            avl = new AVLTree();
        }
        else if (arbol.equals("BST")){

        }
        else if (arbol.equals("SPLAY")){
            arbolSplay = new SplayTree();
        }
        else{
            arbolB = new Btree(3);
        }
    }

    public void borrarArbol(){//borra el nodo del arbol actual
        if (arbol.equals("AVL")){
            avl.clear();
        }
        else if (arbol.equals("BST")){

        }
        else if (arbol.equals("SPLAY")){
            arbolSplay.clear();
        }
        else{
            arbolB.clear();
        }
    }

    public void agregarNodos(int num){//agrega nodos al arbol actual
        if (arbol.equals("AVL")){
            avl.Insert(num);
        }
        else if (arbol.equals("BST")){

        }
        else if (arbol.equals("SPLAY")){
            arbolSplay.Insert(num);
        }
        else{
            arbolB.insert(num);
        }
    }

    public int verificaarbol(){//revisa el tamaño de cada árbol
        if (arbol.equals("AVL")){
            size = avl.getSize();
        }
        else if (arbol.equals("BST")){
            //return bst.size();

        }
        else if (arbol.equals("SPLAY")){
            size = arbolSplay.getSize();
        }
        else {
            size = arbolB.getSize();
        }
        return size;
    }

    public void Setindicador(int pos){
        indicador = pos;
    }

    public int getIndicador() {
        return indicador;
    }
}
