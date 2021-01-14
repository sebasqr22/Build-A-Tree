package buildatree;

import java.util.ArrayList;

public class PlayersTrees {
    /**
     * Clase que almacena a los jugadores y cambia la información que almacenan en los arboles,
     * puede modificar los arboles de los jugadores que almacene en la lista "players"
     */

    String arbol;
    int ganador;//jugador ganador de la ronda

    ArrayList<Player> players = new ArrayList<>();//guarda las instancias de la clase jugador
    public void setArbol(String data){
        arbol = data;
    }

    /**
     * Crea los arboles para los jugadores según el árbol actual de la partida
     *
     * @param numeroJugadores la cantida de jugadores en la partida
     */
    public void comenzar(int numeroJugadores){
        if (numeroJugadores == 2){
            for (int i=0;i<3 ;i++) {
                Player nuevo = new Player();
                nuevo.Setindicador(i);//indicador de el jugador
                players.add(nuevo);//agrega el jugadro a la lista
            }

            for (Player a:players) {
                a.setArbol(this.arbol);//indica el arbol a cada jugador
                a.crearArbol();
            }
        }

        else if (numeroJugadores == 3){
            for (int i=0;i<4 ;i++) {
                players.add(new Player());
            }
            for (Player a:players) {
                a.setArbol(this.arbol);//indica el arbol a cada jugador
                a.crearArbol();
            }
        }

        else{
            for (int i=0;i<5 ;i++) {
                players.add(new Player());
            }
            for (Player a:players) {
                a.setArbol(this.arbol);//indica el arbol a cada jugador
                a.crearArbol();
            }
        }
    }

    public ArrayList getLista(){
        return players;
    }

    public void agregarNodo(int numeroJugador, int numero){
        players.get(numeroJugador).agregarNodos(numero);
    }

    public void reset(int numeroJugador){
        players.get(numeroJugador).borrarArbol();
    }

    public void resetAll() {
        for (Player a : players) {
            a.borrarArbol();
        }
    }

    /***
     * Verifica si algún jugador completó el challenge.
     *
     * @return true o false si encontró o no a un ganador
     */

    public boolean Status(){//revisa si algún jugador completó el challenge
        boolean validador = false;
        for (Player a:players) {
            if(a.verificaarbol()>15){
                ganador = a.getIndicador();
                validador=true;
            }
        }
        if(validador){
            return true;
        }else{
            return false;
        }
    }

    /***
     * Busca el jugador con el árbol más grande.
     * @return el indice donde se ubica el jugador ganador
     */

    public int best(){//retorna la posición del mejor jugador de la ronda
        int pts = 0;
        int dir = 0;
        for (Player a:players) {
            if(a.size>pts){
                pts = a.size;
                dir = a.getIndicador();
            }
        }
        return dir;
    }

    public boolean ExisteGanador(){
        boolean win = false;
        int primero = players.get(0).size;
        for (Player a:players) {
            if(a.size!=primero){
                win = true;
            }
        }
        if(win){
            return true;
        }
        return false;
    }

    public int getGanador() {
        return ganador;
    }
}
