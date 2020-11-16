package tec.buildatree.trees.Splaytree;

/***
 * Clase nodo para el árbol splay, se crea con los indicadores de izquierda y
 * derecha nulos y el dato se inicializa en 0.
 *
 */


public class SplayNode {
    private int dato;
    private SplayNode right,left,parent;

    public SplayNode(){
        right= null;
        left = null;
        parent = null;
        dato= 0;
    }

    public int getDato() {
        return dato;
    }

    public SplayNode getLeft() {
        return left;
    }

    public SplayNode getRight() {
        return right;
    }
    public SplayNode getParent() {
        return parent;
    }
    public void setDato(int dato) {
        this.dato = dato;
    }
    public void setParent(SplayNode parent) {
        this.parent = parent;
    }
    public void setRight(SplayNode right) {
        this.right = right;
    }
    public void setLeft(SplayNode left) {
        this.left = left;
    }
}
