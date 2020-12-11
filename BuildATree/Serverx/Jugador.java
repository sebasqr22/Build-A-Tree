public class Jugador {
    int puerto;
    int puntaje;

    public Jugador(){
        puerto = 8000;
        puntaje = 600;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
