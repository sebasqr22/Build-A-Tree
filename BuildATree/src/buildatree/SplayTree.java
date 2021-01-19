package buildatree;



import java.util.ArrayList;

/**
 * Clase que crea el árbol con los métodos necesarios para el juego
 * y las diferentes rotaciones
 *
 * basado en https://www.sanfoundry.com/java-program-implement-splay-tree/
 */

public class SplayTree {
    private SplayNode root;
    private int size;
    private ArrayList<String> datos = new ArrayList<>();

    public boolean isEmpty(){
        return root == null;
    }//obtiene si el árbol esta vació
    public int getSize() { return size; }//retorna la cantidad de elementos del árbol

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
        rootaux = new SplayNode();
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

    /**
     * Rotación simple de un hijo izquierdo
     * @param child nodo que se quiere rotar
     * @param parent nodo que se sustituirá
     */
    private void zigleft(SplayNode child,SplayNode parent){
        if((child==null) || (parent==null)||(parent.getLeft()!=child)||(child.getParent()!=parent)){//revisa si el nodo child es el verdadero hijo izquierdo
            System.out.println("Error en la rotación zigleft, los nodos dados son incorrectos");
        }
        if(parent.getParent()!=null){//si parent es un nodo hijo
            if(parent == parent.getParent().getLeft()){//si parent es el hijo izquierdo
                parent.getParent().setLeft(child);//se le otorga child como hijo izquierdo a el padre de parent
            }else{//si parent es un hijo derecho
                parent.getParent().setRight(child);//se le otorga child como hijo derecho a el padre de parent
            }
        }
        if(child.getRight()!=null){//si child tiene hijos
            child.getRight().setParent(parent);//se le otorga el hijo de child a parent como hijo derecho
        }
        child.setParent(parent.getParent());
        parent.setParent(child);
        parent.setLeft(child.getRight());
        child.setRight(parent);
    }
    /**
     * Rotación simple de un hijo derecho
     * @param child nodo que se quiere rotar
     * @param parent nodo que se sustituirá
     */
    private void zigright(SplayNode child, SplayNode parent){
        if((child==null)||(parent==null)||(parent.getRight()!=child)||(child.getParent()!=parent)){
            System.out.println("Error en la rotación zigright, los nodos dados son incorrectos");
        }

        if(parent.getParent()!=null){//si parent es un hijo
            if(parent==parent.getParent().getLeft()){
                parent.getParent().setLeft(child);
            }else {
                parent.getParent().setRight(child);
            }
        }

        if (child.getRight()!=null){//si child tiene un hijo derecho lo dona a parent
            child.getRight().setParent(parent);
        }

        child.setParent(parent.getParent());
        parent.setParent(child);
        parent.setRight(child.getLeft());
        child.setLeft(parent);
    }

    /**
     * Se encarga de hacer las rotaciones necesarias para dejar el nodo en la raíz
     * @param rootaux reccibe el nodo que se quiere mover a la root
     */
    private void Splay(SplayNode rootaux) {//se encarga de hacer las rotaciones en cada operación

        while(rootaux.getParent()!=null){//mientras el nodo tenga un padre

            SplayNode parent = rootaux.getParent();//una varible para guardar el nodo padre
            SplayNode grandparent = parent.getParent();//variable para guardar el padre del nodo padre

            if(grandparent==null){//si el padre de parent es nulo
                /***rotaciones simples**/
                if(rootaux==parent.getLeft()){//si el nodo que se quiere mover es un hijo izquierdo
                    zigleft(rootaux,parent);//sustituyo el lugar de parent por rootaux
                }else {
                    zigright(rootaux,parent);
                }
            }else {//si existe el padre de parent
                if(rootaux == parent.getLeft()){//si el nodo a rotar es el hijo izquierdo de parent

                    if(parent==grandparent.getLeft()){//si gran parent es el hijo izquierdo
                        /**rotación zig zig izquierda**/
                        zigleft(parent,grandparent);
                        zigleft(rootaux,parent);
                    }else {
                        /**rotacion zig zag izquierda derecha**/
                        zigleft(rootaux,rootaux.getParent());
                        zigright(rootaux,rootaux.getParent());
                    }
                }else {//si el nodo a rotar es un hijo derecho de parent
                    if(parent== grandparent.getLeft()){
                        /**rotación zig zag derecha izquierda **/
                        zigright(rootaux,rootaux.getParent());
                        zigleft(rootaux, rootaux.getParent());
                    }else{
                        /**rotación zig zig drecha**/
                        zigright(parent,grandparent);
                        zigright(rootaux,parent);
                    }
                }
            }
        }
        this.root = rootaux;//se crea el nuevo root depués de las diferentes rotaciones
    }
    /**
     * Método que busca un elemnto en el árbol y lo pone en el root luego de encontrarlo*/
    public SplayNode search(int dato){
        SplayNode aux = null;//guarda el nodo anterior
        SplayNode rootaux = this.root;

        while (rootaux!=null){
            aux = rootaux;
            if(dato>rootaux.getDato()){//si el dato es mayor se sigue buscando a la derecha
                rootaux = rootaux.getRight();
            }else if(dato<rootaux.getDato()) {//si el dato es menor se va a la izquierda
                rootaux = rootaux.getLeft();
            }else if(dato==rootaux.getDato()){//si es igual retorna el nodo y lo pone en el root
                Splay(rootaux);
                return rootaux;
            }
        }
        if (aux!=null){//si aun existe el nodo anterior
            Splay(aux);//es el último nodo que se comparó y va a la raíz.
            return null;
        }
        return null;//no lo encontró
    }
    /**Método que remueve un nodo**/
    public void remove(int dato){
        SplayNode nodo = search(dato);

        if(nodo==null){//si el nodo no existe se termina la operación
            return;
        }
        Splay(nodo);//lo hago raíz
        if((nodo.getLeft() != null)&&(nodo.getRight()!=null)){//si nodo tiene dos hijos
            SplayNode low = nodo.getLeft();
            while (low.getRight()!=null){
                low = low.getLeft();
            }
            low.setRight(nodo.getRight());//la derecha del menor será la derecha del nodo
            nodo.getRight().setParent(low);//le da el hijo izquierdo a la raíz
            nodo.getLeft().setParent(null);//le dá el hijo izquierdo a la raíz
            this.root = nodo.getLeft();//la raíz será el elemento izquierdo del nodo
        }else if(nodo.getRight()!=null){
            nodo.getRight().setParent(null);
            this.root = nodo.getRight();
        }else if(nodo.getLeft()!=null){
            nodo.getLeft().setParent(null);
            this.root = nodo.getLeft();
        }else {
            this.root = null;
        }

        nodo.setParent(null);
        nodo.setLeft(null);
        nodo.setRight(null);
        nodo = null;
        this.size--;//le resta a la cantidad de nodos
    }
    public void clear(){
        this.root = null;
        this.size = 0;
    }
    public void recorrer(SplayNode nodo){
        if(nodo!=null){
            this.recorrer(nodo.getLeft());
            System.out.println("["+nodo.getDato()+"]");
            datos.add(Integer.toString(nodo.getDato()));
            this.recorrer(nodo.getRight());
        }
    }

    public ArrayList<String> getDatos(){
        recorrer(this.root);
        ArrayList<String> data = this.datos;
        this.datos = new ArrayList<>();
        return data;
    }

    public SplayNode getRoot() {
        return root;
    }
}
