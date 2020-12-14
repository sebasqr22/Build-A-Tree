package buildatree;

public class Jugador {
    /***
     * Envia recoge la información de los tokens recibidos por el jugador
     */

    int valor;
    String tipo;
    String nombre;

    public int getValor() {
        return valor;
    }

    public void setValor(int puntaje) {
        this.valor = puntaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
