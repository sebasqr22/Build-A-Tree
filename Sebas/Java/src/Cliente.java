import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class Cliente{
    public static void main(String[] args){
        try {
            Cliente objeto = new Cliente();
            Socket client = new Socket("127.0.0.1", 9200);
            DataInputStream entrada = new DataInputStream(client.getInputStream());
            DataOutputStream salida = new DataOutputStream(client.getOutputStream());
            byte[] lista = objeto.PaqueteEnvio("Hola".getBytes("UTF8"));
            salida.write(lista);
            salida.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private byte[] PaqueteEnvio(byte[] datos){
        byte[] paquete = null;
        try {
            byte[] inicial = new byte[1];
            inicial[0] = 2;
            byte[] separador = new byte[1];
            separador[0] = 4;
            byte[] largoDatos = String.valueOf(datos.length).getBytes("UTF8");
            paquete = new byte[inicial.length + separador.length + largoDatos.length + datos.length];

            System.arraycopy(inicial, 0, paquete, 0, inicial.length);
            System.arraycopy(largoDatos, 0, paquete, inicial.length, largoDatos.length);
            System.arraycopy(separador, 0, paquete, inicial.length + largoDatos.length, separador.length);
            System.arraycopy(datos, 0, paquete, inicial.length + largoDatos.length + separador.length, datos.length);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return paquete;
    }
}
