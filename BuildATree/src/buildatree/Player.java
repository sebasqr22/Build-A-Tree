package buildatree;

import java.util.ArrayList;

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
    BSTTree arbolbst;
    ArrayList<String> datos = new ArrayList<>();


    /**
     * Método que cambia el tipo de cambio
     * @param data
     */
    public void setArbol(String data){
        arbol = data;
    }

    /**
     * Método que crea el árbol de un jugador
     */
    public void crearArbol(){//crea los arboles del jugador
        if (arbol.equals("AVL")){
            avl = new AVLTree();
        }
        else if (arbol.equals("BST")){

            arbolbst = new BSTTree();
        }
        else if (arbol.equals("SPLAY")){
            arbolSplay = new SplayTree();
        }
        else{
            arbolB = new Btree(3);
        }
    }

    /**
     * Método para borrar un árbol de un jugador
     */
    public void borrarArbol(){//borra el nodo del arbol actual
        if (arbol.equals("AVL")){
            avl.clear();
        }
        else if (arbol.equals("BST")){
            arbolbst.Clear();
        }
        else if (arbol.equals("SPLAY")){
            arbolSplay.clear();
        }
        else{
            arbolB.clear();
        }
    }

    /**
     * Método para agregar nodos al árbol de un jugador
     * @param num
     */
    public void agregarNodos(int num){//agrega nodos al arbol actual
        if (arbol.equals("AVL")){
            avl.Insert(num);
        }
        else if (arbol.equals("BST")){
            arbolbst.insert(num);
        }
        else if (arbol.equals("SPLAY")){
            arbolSplay.Insert(num);
        }
        else{
            arbolB.insert(num);
        }
    }

    /**
     * Método que vetifica el tamaño de cada árbol
     * @return
     */
    public int verificaarbol(){//revisa el tamaño de cada árbol
        if (arbol.equals("AVL")){
            size = avl.getSize();
        }
        else if (arbol.equals("BST")){
            return arbolbst.getSize();

        }
        else if (arbol.equals("SPLAY")){
            size = arbolSplay.getSize();
        }
        else {
            size = arbolB.getSize();
        }
        return size;
    }

    /***
     * Enseña los datos del árbol del jugador
     * @return un string con los datos del arbol
     */
    public String[] getDatosarbol(){
        System.out.println("Viendo datos");
        if (arbol.equals("AVL")){
            datos = avl.getDatos();
        }
        else if (arbol.equals("BST")){
            datos = arbolbst.getDatos();
        }
        else if (arbol.equals("SPLAY")){
            datos = arbolSplay.getDatos();
        }
        else {
            datos = arbolB.getDatos();
        }
        String data[] = new String[datos.size()+1];
        for (int i =0 ; i< datos.size();i++) {
            data[i] = datos.get(i);
            System.out.println(datos.get(i));
        }
        this.datos = new ArrayList<>();

        return data;
    }

    /**
     * Método que cambia el indicador de la posición
     * @param pos
     */
    public void Setindicador(int pos){
        indicador = pos;
    }

    /**
     * Método que retorna el indicador de un árbol
     * @return indicador del árbol
     */
    public int getIndicador() {
        return indicador;
    }
}
