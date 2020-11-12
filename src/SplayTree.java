/**
 * Clase que crea el árbol con los métodos necesarios para el juego
 * y las diferentes rotaciones
 */

public class SplayTree {
    SplayNode root;
    int size;

    public boolean isEmpty(){
        return root == null;
    }

    public int getSize() {
        return size;
    }

    public void Insert(int dato){
        SplayNode rootaux = root;//crea un nodo que sea la raíz
        SplayNode aux = null;//nodo auxiliar


        //---------------------------------Empieza proceso de comparacion--------------------------------
        while (rootaux!=null) {
            aux = rootaux;

            if (dato > aux.getDato()) {//compara con el dato de la raíz
                rootaux = rootaux.getRight();//si el mayor pasa a comparar con el de la derecha
            } else {
                rootaux = rootaux.getLeft();//si es menor se empiza a comparar con los de la izquierda

            }
        }
        //---------------------------------termina el proceso de comparación-------------------------------
        rootaux = new SplayNode();//al finalizar el proceso de comparación resetea rootaux
        rootaux.setDato(dato);//le dá el dato al nuevo nodo
        rootaux.setParent(aux);//le da un padre al nuevo nodo que será la útima declaración del ciclo anterior

        if (aux==null){//si el padre dado es nulo
            root = rootaux;//la raíz será la raíz auxiliar
        }else if(dato>aux.getDato()){//si el dato es mayor al dato de el padre, la raíz auxiliar pasa a la derecha
            aux.setRight(rootaux);
        }else {//si es menor pasa a la izquierda
            aux.setLeft(rootaux);
        }
        Splay(rootaux);//método que realiza las rotaciones necesarias para acomodar el nodo
        size++;
    }

    private void Splay(SplayNode rootaux) {


    }

}
