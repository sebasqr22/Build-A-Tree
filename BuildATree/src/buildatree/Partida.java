package buildatree;

public class Partida {
    int tiempo;
    String arbolactual;
    Boolean tiempoAcabado = false;

    public Partida(){
        tiempo = 210;
        arbolactual = "BST";
    }

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
}
