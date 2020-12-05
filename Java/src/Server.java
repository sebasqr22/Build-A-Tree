import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] args){
        try {
            ServerSocket servidor = new ServerSocket(9200);
            while(true){
                new Thread(new Envio(servidor.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Envio implements Runnable{
    private Socket target;
    private DataInputStream entrada;
    private DataOutputStream salida;


    public Envio(Socket enviar){
        target = enviar;
        try {
            entrada = new DataInputStream(target.getInputStream());
            salida = new DataOutputStream(target.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    @Override
    public void run() {
        while(true){
            byte[] inicial = new byte[1];
            try {
                entrada.read(inicial, 0, inicial.length);
                if(inicial[0] == 2){
                    System.out.println(new String(Leer()));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private byte[] Leer(){
        byte[] data = null;
            try {
                int contador = 0;
                String largoBuf = "";
                while((contador = entrada.read())!=4){
                    largoBuf += (char)contador;
                }
                int largoData = Integer.parseInt(largoBuf);
                data = new byte[Integer.parseInt(largoBuf)];
                int leerByte = 0;
                int byte_offset = 0;
                while(byte_offset < largoData){
                    entrada.read(data, byte_offset, largoData-byte_offset);
                    byte_offset += leerByte;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        return data;
    }
}