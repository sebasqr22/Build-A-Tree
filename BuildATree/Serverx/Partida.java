public class Partida {
    int tiempo;
    String arbolactual;

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
}
