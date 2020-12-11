import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Server implements Runnable{
    /**
     *Se crea al iniciar el juego, lleva el control del tiempo de la partida e indica en qué momento se deben enviar
     * los tokens al juego.
     */

    public static final int puerto = 8000;
    private ServerSocket serverSocket;
    private int[] clients;
    public static Gson gson = new Gson();
    public Partida partida;

    int contador = 210;

    private boolean on = true;
    public Server(){
        partida = new Partida();
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
                Jugador alan = gson.fromJson(data,Jugador.class);

                System.out.println(alan.getPuerto());

                socket.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void Controltiempo(){
        TimerTask timerTask = new TimerTask()
        {
            public void run()
            {
                contador-=1;
                partida.setTiempo(contador);
                partida.setArbolactual("BST");

                new Client(9010, partida);
            }
        };
        // Aquí se pone en marcha el timer cada segundo.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

}
