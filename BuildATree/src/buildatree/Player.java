package buildatree;

public class Player {

    String arbol;

    AVLTree avl;

    Btree arbolB;

    SplayTree arbolSplay;


    public void setArbol(String data){
        arbol = data;
    }

    public void crearArbol(){
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

    public void borrarArbol(){
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

    public void agregarNodos(int num){
        if (arbol.equals("AVL")){
            avl.clear();
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
}
