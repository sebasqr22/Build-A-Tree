package buildatree;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
public class Client<T>{
    /**
     * Crea un cliente generico que recibe una clase cual sea y la convierte a una clase Json y la envía a un
     * puerto indicado.
     */
    private int puerto;
    public static Gson gson = new Gson();

    public Client(int puerto, T objeto){
        this.puerto = puerto;

        try {
            Socket conector = new Socket("localhost",puerto);
            String json = gson.toJson(objeto);

            System.out.println(json);

            OutputStreamWriter salida = new OutputStreamWriter(conector.getOutputStream(),"UTF-8");

            salida.write(json);
            salida.flush();

            System.out.println("Objeto enviado");

            conector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
