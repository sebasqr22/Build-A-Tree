package buildatree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Partida{
    Timer contador;
    static int segundos;
    static int segundosChallenge;
    Timer challenge;
    Boolean listoParaEnviar = false;

    int randomNumber = SeleccionarArbol();

    public void CrearNuevaPartida(int cantidadJugadores){

        segundos = 0;
        segundosChallenge = 0;
        ComenzarTimer();
        TimerChallenge();
        contador.start();
        challenge.start();
        CrearArbol(randomNumber);


    }

   public void ComenzarTimer(){
        contador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(segundos > 9){
                    listoParaEnviar = true;
                    System.out.println("Timer 10 " + segundos);
                    ReturnTimer(segundos);
                    segundos = 0;
                    System.out.println("Reiniciando");
                    //listoParaEnviar = false;
                }
               segundos ++;
                ReturnTimer(segundos);
                System.out.println("Timer 10 " + segundos);
            }
        });
   }
   public int ReturnTimer(int timer){
        return timer;
   }
   public void TimerChallenge(){
       challenge = new Timer(1000, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(segundosChallenge > 19){
                   System.out.println("Timer challenge" + segundosChallenge);
                   TimerChallengeReturn(segundosChallenge);
                   segundosChallenge = 0;
                   System.out.println("Reiniciando");
                   randomNumber = SeleccionarArbol();
                   CrearArbol(randomNumber);
               }
               segundosChallenge ++;
               System.out.println("Timer challenge" + segundosChallenge);
               TimerChallengeReturn(segundosChallenge);
           }
       });
   }
   public int TimerChallengeReturn(int timer){
        return timer;
   }
    public int SeleccionarArbol() {

        Random puertoNuevo = new Random();

        int numero = 0;

        while(numero <= 1) {

            numero = puertoNuevo.nextInt(4);
        }

        System.out.println("Tipo de arbol: " + numero);
        return numero;
    }
    public void CrearArbol(int numero){
        if(numero == 1){
            //crear bst
        }

        else if(numero == 2){
            //crear b tree
        }

        else if (numero == 3){
            //crear avl
        }

        else{
            //crear splay
        }
    }
    public Boolean getEnviar(){
        return listoParaEnviar;
    }
}
