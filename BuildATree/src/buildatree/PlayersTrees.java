package buildatree;

import java.util.ArrayList;

public class PlayersTrees {

    String arbol;
    int ganador;//jugador ganador de la ronda

    ArrayList<Player> players = new ArrayList<>();//guarda las instancias de la clase jugador
    public void setArbol(String data){
        arbol = data;
    }

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

    public void resetAll(){
        for (Player a:players) {
            a.borrarArbol();
        }
    }

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

    public int getGanador() {
        return ganador;
    }
}
