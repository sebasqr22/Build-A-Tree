package buildatree;

public class Jugador {
    int tipoArbol = 0;
    int largoArbol = 0;

    public int getTipoArbol(){
        return tipoArbol;
    }

    public void setTipoArbol(int tipo){
        this.tipoArbol = tipo;
    }

    public int getLargoArbol(){
        return largoArbol;
    }

    public void setLargoArbol(int largo){
        largoArbol += largo;
    }
}
