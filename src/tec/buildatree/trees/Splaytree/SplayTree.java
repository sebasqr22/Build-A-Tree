package tec.buildatree.trees.Splaytree;

import tec.buildatree.trees.Splaytree.SplayNode;

/**
 * Clase que crea el árbol con los métodos necesarios para el juego
 * y las diferentes rotaciones
 */

public class SplayTree {
    SplayNode root;
    int size;

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

    /**
     * Rotación simple de un hijo izquierdo
     * @param child nodo que se quiere rotar
     * @param parent nodo que se sustituirá
     */
    private void zigleft(SplayNode child,SplayNode parent){
        if((child==null) || (parent==null)||(parent.getLeft()!=child)||(child.getParent()!=parent)){//revisa si el nodo child es el verdadero hijo izquierdo
            System.out.println("Error en la rotación, los nodos dados son incorrectos");
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
        child.setParent(parent.getParent());//se hace el intercambio de parent por child
        parent.setParent(child);//el padre de parent ahora será child
        parent.setLeft(child.getRight());//el hijo izquierdo de parent será el hijo derecho de child
        child.setRight(parent);//el hijo izquierdo de child será parent
    }
    /**
     * Rotación simple de un hijo derecho
     * @param child nodo que se quiere rotar
     * @param parent nodo que se sustituirá
     */
    private void zigright(SplayNode child, SplayNode parent){
        if((child==null)||(parent==null)||(parent.getRight()!=child)||(child.getParent()!=parent)){
            System.out.println("Error en la rotación, los nodos dados son incorrectos");
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
                        zigleft(parent,grandparent);//sustituye a grandparent por parent dándole sus hijos si los tuviera
                        zigleft(rootaux,parent);//sustituye el parent nuevo por el nodo que se quiere en la raíz
                    }else {
                        /**rotacion zig zag izquierda derecha**/
                        zigleft(rootaux,rootaux.getParent());
                        zigright(rootaux,rootaux.getParent());
                    }
                }else {//si el nodo a rotar es un hijo derecho de parent
                    if(parent== grandparent.getLeft()){//si parent es un hijo izquierdo
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

}
