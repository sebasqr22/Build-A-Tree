import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class Cliente {
     public static void main(String[] args) throws IOException {
         Cliente objeto = new Cliente();

         Socket cliente = new Socket("127.0.0.1", 10101);

         DataInputStream input = new DataInputStream(cliente.getInputStream());

         DataOutputStream out = new DataOutputStream(cliente.getOutputStream());

         byte[] envio = objeto.CrearDatos("Hola".getBytes("UTF8"));

         out.write(envio);
     }
     private byte[] CrearDatos(byte[] datos) throws UnsupportedEncodingException {
         byte[] paqueteEnvio = null;

         byte[] lista = new byte[1];
         lista[0] = 2;

         byte[] separador = new byte[1];
         separador[0] = 4;

         byte[] largo = String.valueOf(datos.length).getBytes("UTF8");

         int tamaño3 = lista.length + largo.length + separador.length;

         paqueteEnvio = new byte[lista.length + separador.length + largo.length + datos.length];

         System.arraycopy(lista, 0, paqueteEnvio, 0, lista.length);

         System.arraycopy(largo, 0, paqueteEnvio, 0, lista.length + largo.length);

         //System.arraycopy(separador, 0, paqueteEnvio, 0, lista.length + separador.length);

         System.arraycopy(datos, 0, paqueteEnvio, 0, lista.length + largo.length + separador.length + datos.length);

         return paqueteEnvio;
     }
}
