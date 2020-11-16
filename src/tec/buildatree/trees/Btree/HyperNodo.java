package tec.buildatree.trees.Btree;

public class HyperNodo {
    int datos[];
    int size;

    int keys[];
    int numkeys;

    public int getSize() {
        return size;
    }

    public int[] getDatos() {
        return datos;
    }

    public int[] getKeys() {
        return keys;
    }

    public int getNumkeys() {
        return numkeys;
    }

    public void setDatos(int[] datos) {
        this.datos = datos;
    }

    public void setKeys(int[] keys) {
        this.keys = keys;
    }
}
