package buildatree;

public class Partida {
    int tiempo;
    String arbolactual;
    Boolean tiempoAcabado = false;
    String[] listaajugaadores;
    int[] puntajes;

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getArbolactual() {
        return arbolactual;
    }

    public void setArbolactual(String arbolactual) {
        this.arbolactual = arbolactual;
    }

    public Boolean getTiempoAcabado(){ return this.tiempoAcabado;}

    public void setTiempoAcabado(Boolean condicion){ this.tiempoAcabado = condicion;}

    public String[] getListaajugaadores() {
        return listaajugaadores;
    }

    public void setListaajugaadores(String[] listaajugaadores) {
        this.listaajugaadores = listaajugaadores;
    }

    public int[] getPuntajes() {
        return puntajes;
    }

    public void setPuntajes(int[] puntajes) {
        this.puntajes = puntajes;
    }
}
