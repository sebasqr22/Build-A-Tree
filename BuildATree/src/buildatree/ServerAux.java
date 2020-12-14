package buildatree;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAux {
    /**
     *Se crea al iniciar el juego, lleva el control del tiempo de la partida e indica en qué momento se deben enviar
     * los tokens al juego.
     */

    public int puerto ;
    private ServerSocket serverSocket;
    public static Gson gson = new Gson();
    public Mensaje objeto;

    private boolean on = true;
    public ServerAux(int puerto){
        this.puerto = puerto;
    }
    public String Iniciar(){
        try {
            System.out.println("Server Iniciado");
            serverSocket = new ServerSocket(puerto);
            Socket socket;


            while (on){
                socket = serverSocket.accept();//crea una conexión

                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String data = (entrada.readLine());
                System.out.println(data);




                objeto = gson.fromJson(data,Mensaje.class);
                on = !on;


                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.objeto.GetMensaje();
    }
}
