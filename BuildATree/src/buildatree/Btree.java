package buildatree;

import java.util.ArrayList;

/**
 * Clase para crear un árbol B normal
 *
 * basado en https://turing.plymouth.edu/~zshen/Webfiles/notes/CS322/CodeSample/com/mhhe/clrs2e/BTree.java]
 * basado en https://www.programiz.com/dsa/insertion-into-a-b-tree
 * @author Bryan
 */
public class Btree {
    private Bnode root;
    public int size;

    private int grado;//el numero minimo de claves
    private int maxclaves;//cantidad maxima de datos

    private ArrayList<String> datos = new ArrayList<>();

    /** Contiene un array con los datos del nodo y sus referencias**/
    class Bnode {
        private int[] claves;
        private Bnode[] childs;
        private int size;
        private boolean leaf=true;

        /**Constructor por defecto del nodo**/
        public Bnode(int n) {
            this.size = n;
            this.claves = new int[maxclaves];
            this.childs = new Bnode[2*grado];
        }
        /**lee el disco**/
        public void readdisk(){
            // lee el disco
        }
        /**escribe en el disco**/
        public void writedisk(){
            //escribe en disco
        }
    }
    /**Constructor de el árbol**/
    public Btree(int grado){
        this.grado = grado;
        this.maxclaves = 2*grado-1;
        this.root = new Bnode(0);//es una hoja
        //se escribe en el disco
    }
    /**Busca una clave dentro de el nodo**/
    private Bnode search(Bnode nodo,int clave){
        int index = 0;
        if(nodo==null){
            return nodo;
        }
        for (index=0;index<nodo.size;index++){
            if(clave<nodo.claves[index]){
                break;
            }
            if (clave==nodo.claves[index]){
                return nodo;
            }
        }
        if(nodo.leaf){
            return null;
        }
        else {
            return search(nodo.childs[index],clave);
        }

    }
    /**Divide el nodo si está lleno**/
    public void Split(Bnode parent, int pos,Bnode temp){
        Bnode aux = new Bnode(grado-1);
        aux.leaf = temp.leaf;

        //copia las claves de la mitad del nodo parent
        for (int j = 0;j< grado-1; j++){
            aux.claves[j] = temp.claves[j+grado];
        }
        //si el nodo no es una hoja, copia el hijo en el indice del grado
        // al las posiciones 2*grado-1
        if(!aux.leaf){
            for (int j=0;j<grado;j++){
                aux.childs[j] = temp.childs[j+grado];
            }
        }
        temp.size = grado-1;

        //mueve los hijos de parent que están a la derecha de la mitadç
        //una posición a la derecha
        for (int j = parent.size;j>=pos+1;j--){
            parent.childs[j+1] = parent.childs[j];
        }
        parent.childs[pos+1] = aux;

        //mueve los hijos que faltan a la derecha una posición
        for (int j = parent.size-1;j>=pos;j--){
            parent.claves[j+1] = parent.claves[j];
        }
        parent.claves[pos] = temp.claves[grado-1];
        parent.size = parent.size+1;

        //writedisk()
        //aux.writedisk()
        //parent.writedisk()
    }

    /**Inserta un elemento
     * @param clave la clave a insertar
     * **/
    public void insert(int clave){
        Bnode rootaux = root;
        if(rootaux.size==maxclaves){//si rootaux está al máximo
            Bnode node = new Bnode(0);
            this.root = node;
            node.leaf = false;
            node.childs[0] = rootaux;
            Split(node,0,rootaux);
            insertvalue(node,clave);
        }else{
            insertvalue(rootaux,clave);
        }
        size++;
    }
    /**Inserta una clave en un nodo específico**/
    private void insertvalue(Bnode x,int clave){
        if(x.leaf){
            int index = 0;
            //mueve las claves mayores a la clave a insertar una posción a la derecha
            for(index = x.size-1; index>= 0  && clave<x.claves[index];index--){
                x.claves[index+1] = x.claves[index];
            }
            x.claves[index+1]= clave;//inserta clave
            x.size = x.size+1;
            //se escribe en disco
        }else {
            int index = x.size-1;
            //encuentra el al que decendemos
            while (index>=0&&clave<x.claves[index]){
                index--;
            }
            index++;
            Bnode temp = x.childs[index];
            if(temp.size == maxclaves){
                //el hijo estaba lleno por lo que se divide y posiblemente
                //se actualize el indice para llegar a un nuevo hijo
                Split(x,index,temp);
                if(clave>x.claves[index]){
                    index++;
                }
            }
            insertvalue(x.childs[index],clave);
        }
    }
    /**Indica si el árbol contiene un elemento**/
    public boolean contains(int clave){
        if(this.search(root,clave)!=null){
            return true;
        }else {
            return false;
        }
    }

    /**Muestra el contenido del 'arbol**/
    public void Show(){
        show(this.root);
    }

    public int getSize() {
        return size;
    }

    public void show(Bnode aux){
        assert (aux==null);

        for(int i = 0 ; i < aux.size;i++){
            System.out.print(aux.claves[i]+" ");
            datos.add(Integer.toString(aux.claves[i]));
        }
        if (!aux.leaf){
            for (int i = 0; i<aux.size+1;i++){
                show(aux.childs[i]);
            }
        }
    }

    /**
     *
     * @return una lista con los datos del árbol
     */
    public ArrayList<String> getDatos(){
        show(this.root);
        ArrayList<String> data = this.datos;
        this.datos = new ArrayList<>();
        return data;
    }

    /**
     * Método para limpiar el árbol
     */
    public void clear(){
        this.root = null;
        this.datos = new ArrayList<>();
        this.root = new Bnode(0);
        this.size = 0;
    }
}
