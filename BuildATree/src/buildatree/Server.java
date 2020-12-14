package buildatree;

import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Server implements Runnable{
    /**
     *Se crea al iniciar el juego, lleva el control del tiempo de la partida e indica en qué momento se deben enviar
     * los tokens al juego.
     */

    public static final int puerto = 8000;//puerto para el serversocket
    private ServerSocket serverSocket;



    public static Gson gson = new Gson();//conversor para json
    public Partida partida;//partida del servidor

    int contador = 210;
    int enviarToken = 5;
    int cambioarbol = 20;
    int numjugadores;//total de jugadores en partida


    private String[] tree_list = { "BST", "AVL", "SPLAY", "BTREE"};//challenges disponibles
    int[] puntajes;//puntajes de los jugadores en orden

    Random random = new Random();

    private boolean on = true;


    public Server(int jugadores){
        partida = new Partida();
        partida.setArbolactual(tree_list[random.nextInt(tree_list.length)]);
        partida.setPuntajes(new int[]{0,0,0,0});
        this.puntajes = new int[]{0,0,0,0};
        this.numjugadores = jugadores;
        Controltiempo();
    }


    @Override
    public void run() {
        try {
            System.out.println("Server Iniciado");
            serverSocket = new ServerSocket(puerto);
            Socket socket;


            while (on){
                socket = serverSocket.accept();//crea una conexión

                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String data = (entrada.readLine());
                Jugador recibido = gson.fromJson(data,Jugador.class);

                System.out.println(data);


                socket.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void Controltiempo(){//Lleva el control del tiempo de la partida y el spawn de los tokens
        TimerTask timerTask = new TimerTask()
        {
            public void run()
            {
                contador-=1;
                enviarToken -=1;
                cambioarbol-=1;
                partida.setTiempo(contador);

                if(enviarToken == 0){
                    partida.setTiempoAcabado(true);
                    enviarToken = 5;
                }
                if(cambioarbol == 0){//cuando acaba el contador se resetea el arbol y se dan los puntos
                    int total = numjugadores;
                    partida.setArbolactual(tree_list[random.nextInt(tree_list.length)]);
                    partida.setPuntajes(puntajes);
                    cambioarbol = 20;
                }

                new Client(9010, partida);

                partida.setTiempoAcabado(false);
            }
        };
        // Aquí se pone en marcha el timer cada segundo.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private void treeControl(){

    }

}
