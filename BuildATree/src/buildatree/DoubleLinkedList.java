package buildatree;

public class DoubleLinkedList {
    static NodeDoubleLinked head = null;

    public static void addNode(){ //Entre los parentesis agregar los atributos que se quieren agregar al nodo
        if (head==null){
            NodeDoubleLinked new_node = new NodeDoubleLinked();
            //Agregar los atributos aqui como new_node.<atributo> = <atributo nodo>
            new_node.next = new_node.prev = new_node;
            head = new_node;
        }
        else{
            NodeDoubleLinked last = head.prev;
            NodeDoubleLinked new_node = new NodeDoubleLinked();
            new_node.next = head;
            head.prev = new_node;
            new_node.prev = last;
            last.next = new_node;
        }
    }

    public void deleteNode(NodeDoubleLinked del){ //Aqui se pasa el nodo que se desea borrar
        if (head==null || del==null){
            return;
        }
        if (head==del){
            head = del.next;
        }
        if (del.next != null){
            del.next.prev = del.prev;
        }
        if (del.prev != null){
            del.prev.next = del.next;
        }
    }
        /*
        Por si quieren revisar los nodos y su orden
        public static void printNodes(){
            NodeDoubleLinked temp = head;
            while (temp.next != head){
                System.out.println(); //Agregar el atributo dentro de los parentesis que se quiere imprimir como temp.<atributo>
                temp = temp.next;
            }
            NodeDoubleLinked last = head.prev;
            temp = last;
            while (temp.prev != last){
                System.out.println(); //Agregar el atributo dentro de los parentesis que se quiere imprimir como temp.<atributo>
                temp = temp.prev;
            }
        }
        */
}
