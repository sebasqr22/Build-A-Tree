package buildatree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Contador {
    Timer contador;
    static int segundos;
    static int segundosChallenge;
    Timer challenge;


    public void CrearNuevaPartida(int cantidadJugadores) {

        segundos = 0;
        segundosChallenge = 0;
        ComenzarTimer();
        contador.start();
        challenge.start();


    }

    public void ComenzarTimer() {
        contador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (segundos > 19) {
                    System.out.println("Timer 10 " + segundos);
                    segundos = 0;
                    System.out.println("Reiniciando");
                    //listoParaEnviar = false;
                }
                segundos++;
                System.out.println("Timer 10 " + segundos);
            }
        });
    }
}
