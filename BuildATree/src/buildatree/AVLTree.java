package buildatree;

/**
 * Clase para ver los nodos derecho e izquierdo
 */
class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

/**
 * Clase para crear el árbol AVL
 */
public class AVLTree {
    Node root;

    // Funcion que retorna la altura del arbol

    /**
     *
     * @param N
     * @return altura del árbol
     */
    int height(Node N) {
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
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Hacer la rotacion
        x.right = y;
        y.left = T2;

        // Actualizar la altura
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Retornar la nueva raiz
        return x;
    }

    // Funcion que rota a la izquierda el subarbol

    /**
     *
     * @param x
     * @return rotación a la derecha del subárbol
     */
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Hacer la rotacion
        y.left = x;
        x.right = T2;

        // Actualizar las alturas
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Retornar la nueva raiz
        return y;
    }

    // Retorna el factor de balance apartir del nodo N

    /**
     *
     * @param N
     * @return factor de balance a partir del nodo N
     */
    int getBalance(Node N) {
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
    Node minValueNode(Node node)
    {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    // Funcion para insertar un nodo al arbol

    /**
     *
     * @param node
     * @param key
     * @return introducir nodo al árbol
     */
    Node insert(Node node, int key) {

        // Primero se realiza la insercion igual a BST
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // Duplicate keys not allowed
            return node;

        // Actualizar la altura del nodo anterior
        node.height = 1 + max(height(node.left),
                height(node.right));

        // Verificar el factor de balance del nodo para ver si se desbalanceo
        int balance = getBalance(node);

        // Si el nodo se desbalancea se tienen 4 casos
        // Izquierda izquierda
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Derecha derecha
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Izquierda derecha
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Derecha izquierda
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Funcion para eliminar un nodo

    /**
     *
     * @param root
     * @param key
     * @return arból con un nodo eliminado
     */
    Node deleteNode(Node root, int key)
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
                Node temp = null;
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
                Node temp = minValueNode(root.right);

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
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

}
