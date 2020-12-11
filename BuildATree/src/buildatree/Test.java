package buildatree;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {
        Thread hilo = new Thread(new Server());
        hilo.start();
    }
}
