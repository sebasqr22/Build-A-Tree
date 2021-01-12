package buildatree;

import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
    String arbol;
    PlayersTrees contenedor;


    private String[] tree_list = { "BST", "AVL", "SPLAY", "BTREE"};//challenges disponibles
    int[] puntajes;//puntajes de los jugadores en orden

    Random random = new Random();

    private boolean on = true;


    public Server(int jugadores){//inicia el servidor
        partida = new Partida();//crea un nuevo objeto de tipo partida
        arbol = tree_list[random.nextInt(tree_list.length)];

        partida.setArbolactual(arbol);//elige el arbol para iniciar la partida

        partida.setPuntajes(new int[]{0,0,0,0});// puntajes iniciales de los jugadores

        //inciar los arboles de lo jugadores segun el tipo elegido

        contenedor = new PlayersTrees();
        contenedor.setArbol(arbol);
        contenedor.comenzar(jugadores);

        this.puntajes = new int[]{0,0,0,0};
        this.numjugadores = jugadores;//numero de jugadores reportados
        Controltiempo();//inicia el contador


    }


    @Override
    public void run() {
        try {
            System.out.println("Server Iniciado");
            serverSocket = new ServerSocket(puerto);
            Socket socket;


            while (on){
                socket = serverSocket.accept();//crea una conexión

                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));//lee el mensaje del jugador

                String data = (entrada.readLine());//convierte el mensaje a texto
                Jugador recibido = gson.fromJson(data,Jugador.class);

                //empezar el procedimiento de validación

                String nombre = recibido.getNombre();
                String tipo = recibido.getTipo();
                int valor = recibido.getValor();

                if(nombre.equals("Player1")){
                    if (tipo.equals(arbol)){
                        contenedor.agregarNodo(0, valor);
                    }
                    else{
                        contenedor.reset(0);
                    }
                }
                else if(nombre.equals("Player2")){
                    if (tipo.equals(arbol)){
                        contenedor.agregarNodo(1, valor);
                    }else{
                        contenedor.reset(1);
                    }
                }
                else if(nombre.equals("Player3")){
                    if (tipo.equals(arbol)){
                        contenedor.agregarNodo(2, valor);
                    }else{
                        contenedor.reset(2);
                    }
                }
                else{
                    if (tipo.equals(arbol)){
                        contenedor.agregarNodo(3, valor);
                    }else{
                        contenedor.reset(3);
                    }
                }
                //termina proceso de validación

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

                if(contenedor.Status()){//si un jugador completó el challenge
                    puntajes[contenedor.getGanador()] += 30;

                    //cambia los arboles y resetea el tiempo para el nuevo cambio

                    arbol = tree_list[random.nextInt(tree_list.length)];//arbol elegido
                    partida.setArbolactual(arbol);//arbol al que se cambia
                    refreshPlayers();
                    cambioarbol = 20;
                }



                if(enviarToken == 0){//envia la indicacion crear un token
                    partida.setTiempoAcabado(true);
                    enviarToken = 5;
                }
                if(cambioarbol == 0){//cuando acaba el contador se resetea el arbol y se dan los puntos

                    puntajes[contenedor.best()]+=20;//le dá los puntos a el mejor jugador de la ronda

                    arbol = tree_list[random.nextInt(tree_list.length)];//arbol elegido
                    partida.setArbolactual(arbol);//arbol al que se cambia


                    partida.setPuntajes(puntajes);

                    refreshPlayers();//actualiza los arboles de los jugadores

                    //se le dan los puntos a cada jugador
                    //resetean
                    //se le vuelven a dar los segun

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

    public void refreshPlayers(){
        contenedor.resetAll();//resetea los arboles de los jugadores
        contenedor.setArbol(arbol);//cambia los arboles de los jugadores
        contenedor.comenzar(numjugadores);//inicia de nuevo los arboles de los jugadores
    }

}
