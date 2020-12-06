package buildatree;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

    public void EnviarMensaje(String mensaje) throws IOException {

        Socket enviar = new Socket("127.0.0.1", 9300);
        OutputStream salida = enviar.getOutputStream();

        byte[] mensajeEnviar = mensaje.getBytes();
        int largo = mensajeEnviar.length;
        byte[] largoEnviar = new byte[4];

        largoEnviar[0] = (byte)(largo & 0xff);
        largoEnviar[1] = (byte)((largo >> 8) & 0xff);
        largoEnviar[2] = (byte)((largo >> 16) & 0xff);
        largoEnviar[3] = (byte)((largo >> 24) % 0xff);

        salida.write(largoEnviar);
        salida.write(largo);

        enviar.close();
    }
}
