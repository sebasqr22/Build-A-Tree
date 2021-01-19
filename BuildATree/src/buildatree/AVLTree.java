package buildatree;

import java.util.ArrayList;

/**
 * Clase para ver los nodos derecho e izquierdo
 */
class AVLNode {
    public int key, height;
    public AVLNode left, right;
    

    public AVLNode(int d) {
        key = d;
        height = 1;
    }
}

/**
 * Clase para crear el árbol AVL
 */
public class AVLTree {
    AVLNode root;
    int size;
    ArrayList<String> datos = new ArrayList<>();

    // Funcion que retorna la altura del arbol

    /**
     *
     * @param N
     * @return altura del árbol
     */
    int height(AVLNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // Funcion que retorna el maximo de dos ints

    /**
     *
     * @param a
     * @param b
     * @return máximo de dos números enteros
     */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Funcion que rota a la derecha el subarbol

    /**
     *
     * @param y
     * @return rotación a la derecha del subárbol
     */
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Hacer la rotacion
        x.right = y;
        y.left = T2;

        // Actualizar la altura
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Retornar la nueva raiz
        return x;
    }
    private AVLNode doublerightrotate(AVLNode node){
        AVLNode aux;

        node.right = leftRotate(node.right);
        aux = rightRotate(node);
        return aux;
    }

    // Funcion que rota a la izquierda el subarbol

    /**
     *
     * @param x
     * @return rotación a la derecha del subárbol
     */
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Hacer la rotacion
        y.left = x;
        x.right = T2;

        // Actualizar las alturas
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Retornar la nueva raiz
        return y;
    }
    private AVLNode doubleleftrotate(AVLNode node){
        AVLNode aux ;

        node.left = rightRotate(node.left);
        aux = leftRotate(node);
        return aux;
    }

    // Retorna el factor de balance apartir del nodo N

    /**
     *
     * @param N
     * @return factor de balance a partir del nodo N
     */
    int getBalance(AVLNode N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    //Funcion que retorna el nodo de menor valor en el arbol

    /**
     *
     * @param node
     * @return nodo de menor valor en el árbol
     */
    AVLNode minValueNode(AVLNode node)
    {
        AVLNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    // Funcion para insertar un nodo al arbol

    public void Insert(int num){
        AVLNode nuevo = new AVLNode(num);

        if (this.root == null) {
            this.root = nuevo;
        } else {
            this.root = insert(nuevo, this.root);
        }

    }

    /**
     *
     * @param nuevo
     * @param subtree
     * @return introducir nodo al árbol
     */
    AVLNode insert(AVLNode nuevo,AVLNode subtree) {
        AVLNode rootaux = subtree;
        // Primero se realiza la insercion igual a BST
        if (nuevo.key <  subtree.key) {
            if (subtree.left == null) {
                subtree.left = nuevo;
            } else {
                subtree.left = insert(nuevo, subtree.left);

                int balance = getBalance(subtree.left) - getBalance(subtree.right);

                if (balance == 2) {
                    if (nuevo.key < subtree.left.key) {
                        rootaux = this.leftRotate(subtree);
                    } else {
                        rootaux = doubleleftrotate(subtree);
                    }
                }
            }
        }else if (nuevo.key>subtree.key) {
            if (subtree.right == null) {
                subtree.right = nuevo;
            } else {
                subtree.right = insert(nuevo, subtree.right);
                int balance = getBalance(subtree.right) - getBalance(subtree.left);
                if (balance == 2) {
                    if (nuevo.key > subtree.right.key) {
                        rootaux = rightRotate(subtree);
                    } else {
                        rootaux = doublerightrotate(subtree);
                    }
                }
            }
        }else {
            System.out.println("Nodo duplicado");
        }


        if ((subtree.left==null)&&(subtree.right!=null)) {
            subtree.height = subtree.right.height +1;
        }else if ((subtree.right==null)&&(subtree.left!=null)) {
            subtree.height = subtree.left.height+1;
        }else {
            subtree.height = max(getBalance(subtree.left),getBalance(subtree.right));
        }
        size++;
        return rootaux;
    }

    // Funcion para eliminar un nodo

    /**
     *
     * @param root
     * @param key
     * @return arból con un nodo eliminado
     */
    AVLNode deleteNode(AVLNode root, int key)
    {
        // Hacer la eliminacion igual al BST
        if (root == null)
            return root;

        // Si el nodo a eliminar es menor a la raiz entonces esta a la izquierda
        if (key < root.key)
            root.left = deleteNode(root.left, key);

            // Si el nodo a eliminar es mayor a la raiz entonces estaria a la derecha
        else if (key > root.key)
            root.right = deleteNode(root.right, key);

            // Si el nodo a eliminar es igual a la raiz entonces se elimina la raiz
        else
        {
            // Caso donde el nodo solo tiene un hijo o ninguno
            if ((root.left == null) || (root.right == null))
            {
                AVLNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No tiene hijo
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                // Con solo un hijo
                else
                    root = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {
                // Caso cuando el nodo tiene dos hijo, el sucesor seria el menor del subarbol derecho
                AVLNode temp = minValueNode(root.right);

                // Copiar la informacion del sucesor en este nodo
                root.key = temp.key;

                // Eliminar el sucesor
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // Si el arbol solo tenia un nodo entonces retornar
        if (root == null)
            return root;

        // Actualizar la altura del nodo actual
        root.height = max(height(root.left), height(root.right)) + 1;

        // Verificar el factor de balance del nodo para ver si se desbalanceo
        int balance = getBalance(root);

        // Si el nodo se desbalancea se tienen 4 casos
        // Izquierda izquierda
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Izquierda derecha
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Derecha derecha
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Derecha izquierda
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Funcion para imprimir el orden trasversal del arbol

    /**
     * Se imprime el orden transversal del árbol
     * @param node
     */
    public void inOrder(AVLNode node) {
        if (node != null) {

            inOrder(node.left);
            //System.out.print(node.key+",");
            datos.add(Integer.toString(node.key));
            inOrder(node.right);
        }
    }
    public ArrayList<String> getDatos(){
        inOrder(this.root);
        ArrayList<String> data = this.datos;
        this.datos = new ArrayList<>();
        return data;
    }

    public int getSize() {
        return size;
    }

    /**
     * Método para limpiar el árbol
     */
    public void clear(){
        this.root = null;
        this.size = 0;
        this.datos = new ArrayList<>();
    }

    public AVLNode getRoot() {
        return root;
    }
}
