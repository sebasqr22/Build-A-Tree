package buildatree;

import java.util.ArrayList;

public class PlayersTrees {

    String arbol;

    ArrayList<Player> players = new ArrayList<>();//guarda las instancias de la clase jugador
    public void setArbol(String data){
        arbol = data;
    }

    public void comenzar(int numeroJugadores){
        if (numeroJugadores == 2){
            for (int i=0;i<3 ;i++) {
                players.add(new Player());
            }

            for (Player a:players) {
                a.setArbol(this.arbol);//indica el arbol a cada jugador
            }
            if (arbol.equals("AVL")){

            }
            else if (arbol.equals("BST")){

            }
            else if (arbol.equals("SPLAY")){

            }
            else{

            }
        }

        else if (numeroJugadores == 3){
            for (int i=0;i<4 ;i++) {
                players.add(new Player());
            }
            for (Player a:players) {
                a.setArbol(this.arbol);//indica el arbol a cada jugador
            }
            if (arbol.equals("AVL")){

            }
            else if (arbol.equals("BST")){

            }
            else if (arbol.equals("SPLAY")){

            }
            else{

            }
        }

        else{
        }
    }
}
