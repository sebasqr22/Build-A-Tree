package buildatree;

import buildatree.BSTNode;

import java.util.LinkedList;
import java.util.Queue;

public class BSTTree {
    private BSTNode root;
    private int size;

    //Funcion que verifica si el arbol es vacio o no
    public boolean isEmpty() {
        return (this.root == null);
    }
    //Funcion que retorna la raiz del arbol
    public BSTNode getRoot() {
        return this.root;
    }
    //Funcion para insertar un valor
    public void insert(Integer data) {
        if(root == null) {
            this.root = new BSTNode(data);
            return;
        }
        insertNode(this.root, data);
        size++;
    }
    //Funcion que agarra el valor insertado y lo convierte en nodo
    private BSTNode insertNode(BSTNode root, Integer data) {
        BSTNode tmpNode = null;
        if(root.getData() >= data) {
            if(root.getLeft() == null) {
                root.setLeft(new BSTNode(data));
                return root.getLeft();
            } else {
                tmpNode = root.getLeft();
            }
        } else {
            if(root.getRight() == null) {
                root.setRight(new BSTNode(data));
                return root.getRight();
            } else {
                tmpNode = root.getRight();
            }
        }
        return insertNode(tmpNode, data);
    }
    //Funcion para eliminar un valor
    public void delete(Integer data) {
        deleteNode(this.root, data);
    }
    //Funcion que agarra el valor a eliminar y elimina el nodo que lo contiene
    private BSTNode deleteNode(BSTNode root, Integer data) {

        if(root == null) return root;

        if(data < root.getData()) {
            root.setLeft(deleteNode(root.getLeft(), data));
        } else if(data > root.getData()) {
            root.setRight(deleteNode(root.getRight(), data));
        } else {
            if(root.getLeft() == null && root.getRight() == null) {
                return null;
            } else if(root.getLeft() == null) {
                return root.getRight();
            } else if(root.getRight() == null) {
                return root.getLeft();
            } else {
                Integer minValue = minValue(root.getRight());
                root.setData(minValue);
                root.setRight(deleteNode(root.getRight(), minValue));
            }
        }
        return root;
    }
    //Funcion para determinar el nodo con menor valor
    private Integer minValue(BSTNode node) {
        if(node.getLeft() != null) {
            return minValue(node.getLeft());
        }
        return node.getData();
    }
    //Funcion para recorrer el arbol transversalmente
    public void inOrderTraversal() {
        doInOrder(this.root);
    }
    //Funcion para recorrer el arbol en orden dado una raiz
    private void doInOrder(BSTNode root) {
        if(root == null) return;
        doInOrder(root.getLeft());
        System.out.print(root.getData()+" ");
        doInOrder(root.getRight());
    }
    public void Clear(){
        this.root = null;
        size=0;
    }

    public int getSize() {
        return size;
    }
}
