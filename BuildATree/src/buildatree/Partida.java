package buildatree;

public class Partida {
    int tiempo;
    String arbolactual;
    Boolean tiempoAcabado = false;
    String[] listaajugaadores;
    int[] puntajes;
    String[] arbol1;
    String[] arbol2;
    String[] arbol3;
    String[] arbol4;

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

    public void setArbol1(String[] arbol1) {
        this.arbol1 = arbol1;
    }

    public void setArbol2(String[] arbol2) {
        this.arbol2 = arbol2;
    }

    public void setArbol3(String[] arbol3) {
        this.arbol3 = arbol3;
    }

    public void setArbol4(String[] arbol4) {
        this.arbol4 = arbol4;
    }
}
